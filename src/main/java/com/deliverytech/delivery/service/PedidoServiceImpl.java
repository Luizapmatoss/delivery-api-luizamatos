package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ItemPedidoDTO;
import com.deliverytech.delivery.dto.PedidoDTO;
import com.deliverytech.delivery.enums.StatusPedido;
import com.deliverytech.delivery.models.*;
import com.deliverytech.delivery.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final RestauranteRepository restauranteRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoServiceImpl(
            PedidoRepository pedidoRepository,
            ProdutoRepository produtoRepository,
            ClienteRepository clienteRepository,
            RestauranteRepository restauranteRepository,
            ItemPedidoRepository itemPedidoRepository) {

        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.restauranteRepository = restauranteRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Override
    @Transactional
    public Pedido criarPedido(PedidoDTO dto) {

        // Buscar cliente
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));

        // Buscar restaurante
        Restaurante restaurante = restauranteRepository.findById(dto.getRestauranteId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado!"));

        if (!cliente.getAtivo()) {
            throw new IllegalArgumentException("Cliente inativo não pode fazer pedidos!");
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setStatus(StatusPedido.PENDENTE.name());
        pedido.setDataCriacao(LocalDateTime.now());

        List<ItemPedido> itens = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedidoDTO itemDTO : dto.getItens()) {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado!"));

            if (!produto.getDisponivel()) {
                throw new IllegalArgumentException("Produto indisponível: " + produto.getNome());
            }

            // Cria item e calcula subtotal
            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());
            item.setSubtotal(produto.getPreco().multiply(BigDecimal.valueOf(itemDTO.getQuantidade())));

            itens.add(item);
            total = total.add(item.getSubtotal());
        }

        pedido.setItem(itens);
        pedido.setValorTotal(total);

        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        throw new UnsupportedOperationException("Unimplemented method 'criarPedido'");
    }

    @Override
    public Pedido buscarPedidoPorId(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'buscarPedidoPorId'");
    }

    @Override
    public List<Pedido> buscarPedidosPorCliente(Long clienteId) {
        throw new UnsupportedOperationException("Unimplemented method 'buscarPedidosPorCliente'");
    }

    @Override
    public Pedido atualizarStatusPedido(Long id, String status) {
        throw new UnsupportedOperationException("Unimplemented method 'atualizarStatusPedido'");
    }

    @Override
    public double calcularTotalPedido(PedidoDTO dto) {
        throw new UnsupportedOperationException("Unimplemented method 'calcularTotalPedido'");
    }

    @Override
    public void cancelarPedido(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'cancelarPedido'");
    }

    @Override
    public Object listarPorCliente(Long clienteId) {
        throw new UnsupportedOperationException("Unimplemented method 'listarPorCliente'");
    }

    @Override
    public Pedido atualizarStatusPedido(Long id, StatusPedido novoStatus) {
        throw new UnsupportedOperationException("Unimplemented method 'atualizarStatusPedido'");
    }

    @Override
    public double calcularTotalPedido(List<ItemPedidoDTO> itens) {
        throw new UnsupportedOperationException("Unimplemented method 'calcularTotalPedido'");
    }

    @Override
    public double calcularTotalPedido(Pedido pedido) {
        throw new UnsupportedOperationException("Unimplemented method 'calcularTotalPedido'");
    }

    @Override
    public List<Pedido> listarTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodos'");
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public Pedido atualizarPedido(Long id, PedidoDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarPedido'");
    }

    @Override
    public boolean deletarPedido(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletarPedido'");
    }

    @Override
    public List<Pedido> listarComFiltros(String status, LocalDate data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarComFiltros'");
    }

    @Override
    public Object listarPorRestaurante(Long restauranteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarPorRestaurante'");
    }
}
