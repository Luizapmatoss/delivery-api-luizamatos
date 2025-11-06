package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.dto.PedidoDTO;
import com.deliverytech.delivery.models.Pedido;
import com.deliverytech.delivery.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // POST /api/pedidos - Criar pedido
    @PostMapping
    public ResponseEntity<Pedido> criar(@Valid @RequestBody PedidoDTO dto) {
        Pedido novo = pedidoService.criarPedido(dto);
        URI uri = URI.create("/api/pedidos/" + novo.getId());
        return ResponseEntity.created(uri).body(novo);
    }

    // GET /api/pedidos/{id} - Buscar pedido completo
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPedidoPorId(id));
    }

    // GET /api/clientes/{clienteId}/pedidos - Histórico do cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Object> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(pedidoService.listarPorCliente(clienteId));
    }

    // PATCH /api/pedidos/{id}/status - Atualizar status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        pedidoService.atualizarStatusPedido(id, status);
        return ResponseEntity.noContent().build();
    }

    // DELETE /api/pedidos/{id} - Cancelar pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        pedidoService.cancelarPedido(id);
        return ResponseEntity.noContent().build();
    }

    // POST /api/pedidos/calcular - Calcular total sem salvar
    @PostMapping("/calcular")
    public ResponseEntity<Double> calcularTotal(@Valid @RequestBody PedidoDTO dto) {
        Double total = pedidoService.calcularTotalPedido(dto);
        return ResponseEntity.ok(total);
    }
}
