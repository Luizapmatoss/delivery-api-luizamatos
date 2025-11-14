package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ItemPedidoDTO;
import com.deliverytech.delivery.dto.PedidoDTO;
import com.deliverytech.delivery.enums.StatusPedido;
import com.deliverytech.delivery.models.Pedido;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PedidoService {

    Pedido criarPedido(Pedido pedido);
    Pedido buscarPedidoPorId(Long id);
    List<Pedido> buscarPedidosPorCliente(Long clienteId);
    Pedido atualizarStatusPedido(Long id, String status);
    double calcularTotalPedido(PedidoDTO dto);
    void cancelarPedido(Long id);
    Object listarPorCliente(Long clienteId);
    Pedido criarPedido(PedidoDTO dto);
    Pedido atualizarStatusPedido(Long id, StatusPedido novoStatus);
    double calcularTotalPedido(List<ItemPedidoDTO> itens);
    double calcularTotalPedido(Pedido pedido);
    List<Pedido> listarTodos();
    Optional<Pedido> buscarPorId(Long id);
    Pedido atualizarPedido(Long id, PedidoDTO dto);
    boolean deletarPedido(Long id);
    List<Pedido> listarComFiltros(String status, LocalDate data);
    Object listarPorRestaurante(Long restauranteId);
}
