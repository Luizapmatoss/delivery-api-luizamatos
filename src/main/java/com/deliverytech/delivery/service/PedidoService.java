package com.deliverytech.delivery.service;

import com.deliverytech.delivery.models.Pedido;
import com.deliverytech.delivery.models.Produto;
import com.deliverytech.delivery.models.Cliente;
import com.deliverytech.delivery.repository.PedidoRepository;
import com.deliverytech.delivery.repository.ProdutoRepository;
import com.deliverytech.delivery.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    private static final List<String> STATUS_VALIDOS = List.of(
            "PENDENTE", "EM_ANDAMENTO", "ENTREGUE", "CANCELADO"
    );

    public PedidoService(PedidoRepository pedidoRepository,
                         ProdutoRepository produtoRepository,
                         ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Pedido criarPedido(Pedido pedido) {
        // valida cliente presente no payload
        if (pedido.getCliente() == null || pedido.getCliente().getId() == null) {
            throw new IllegalArgumentException("Pedido deve conter cliente com id.");
        }

        Long clienteId = pedido.getCliente().getId();
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: id=" + clienteId));

        // valida itens
        List<Produto> itens = pedido.getItens();
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Pedido deve conter pelo menos um produto.");
        }

        // extrai ids dos itens enviados e valida se todos tem id
        List<Long> idsEnviados = itens.stream()
                .map(Produto::getId)
                .collect(Collectors.toList());

        if (idsEnviados.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Todos os itens do pedido devem possuir o campo id.");
        }

        // busca em lote os produtos persistidos
        List<Produto> produtosPersistidos = produtoRepository.findAllById(idsEnviados);

        // identifica ids faltantes
        Set<Long> idsEncontrados = produtosPersistidos.stream()
                .map(Produto::getId).collect(Collectors.toSet());

        List<Long> idsFaltando = idsEnviados.stream()
                .filter(id -> !idsEncontrados.contains(id))
                .distinct()
                .collect(Collectors.toList());

        if (!idsFaltando.isEmpty()) {
            throw new IllegalArgumentException("Produtos não encontrados: ids=" + idsFaltando);
        }

        // map id->produto para manter a ordem original do payload
        Map<Long, Produto> mapaProdutos = produtosPersistidos.stream()
                .collect(Collectors.toMap(Produto::getId, p -> p));

        BigDecimal total = BigDecimal.ZERO; // inicializa corretamente
        List<Produto> itensPersistidos = new ArrayList<>();

        for (Long id : idsEnviados) {
            Produto produto = mapaProdutos.get(id);
            if (produto == null) {
                throw new IllegalArgumentException("Produto não encontrado durante processamento: id=" + id);
            }
            if (produto.getDisponivel() == null || !produto.getDisponivel()) {
                throw new IllegalArgumentException("Produto indisponível: id=" + produto.getId() + " nome=" + produto.getNome());
            }
            total = total.add(produto.getPreco()); // soma BigDecimal
            itensPersistidos.add(produto);
        }

        // monta e salva pedido com itens persistidos
        pedido.setCliente(cliente);
        pedido.setItens(itensPersistidos);
        pedido.setValorTotal(total);
        pedido.setStatus("PENDENTE");
        pedido.setDataCriacao(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void atualizarStatus(Long id, String novoStatus) {
        if (novoStatus == null || novoStatus.isBlank()) {
            throw new IllegalArgumentException("O status não pode estar vazio.");
        }

        String statusTratado = novoStatus.trim().toUpperCase();
        if (!STATUS_VALIDOS.contains(statusTratado)) {
            throw new IllegalArgumentException("Status inválido. Valores permitidos: " + STATUS_VALIDOS);
        }

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado!"));

        pedido.setStatus(statusTratado);
        pedidoRepository.save(pedido);
    }

    @Transactional(readOnly = true)
    public List<Pedido> listarPorCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }

        List<Pedido> pedidos = pedidoRepository.findByClienteId(clienteId);
        pedidos.forEach(p -> {
            if (p.getItens() != null) {
                p.getItens().size();
            }
        });

        return pedidos;
    }

    @Transactional(readOnly = true)
    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        pedidos.forEach(p -> {
            if (p.getItens() != null) {
                p.getItens().size();
            }
        });
        return pedidos;
    }

}