package com.deliverytech.delivery.service;

import com.deliverytech.delivery.models.Pedido;
import com.deliverytech.delivery.models.Produto;
import com.deliverytech.delivery.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criarPedido(Pedido pedido) {
        double total = 0.0;
        for (Produto produto : pedido.getItens()) {
            double preco = produto.getPreco();
            total += preco;
        }

        pedido.setValorTotal(total);
        pedido.setStatus("PENDENTE");
        pedido.setDataCriacao(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }

    public void atualizarStatus(Long id, String novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado!"));
        pedido.setStatus(novoStatus);
        pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }
}
