package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.dto.RelatorioClientesAtivosDTO;
import com.deliverytech.delivery.dto.RelatorioPedidosPeriodoDTO;
import com.deliverytech.delivery.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import javax.management.relation.RelationService;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController<RelatorioVendasPorRestauranteDTO, RelatorioProdutosMaisVendidosDTO> {

    @Autowired
    private RelatorioService relatorioService;

    // Vendas por restaurante
    @GetMapping("/vendas-por-restaurante")
    public ResponseEntity<com.deliverytech.delivery.dto.RelatorioVendasPorRestauranteDTO> vendasPorRestaurante() {
        return ResponseEntity.ok(relatorioService.vendasPorRestaurante());
    }

    // Produtos mais vendidos
    @GetMapping("/produtos-mais-vendidos")
    public ResponseEntity<RelatorioProdutosMaisVendidosDTO> produtosMaisVendidos() {
        return (ResponseEntity<RelatorioProdutosMaisVendidosDTO>) ResponseEntity
                .ok(relatorioService.produtosMaisVendidos());
    }

    // Clientes mais ativos
    @GetMapping("/clientes-ativos")
    public ResponseEntity<RelatorioClientesAtivosDTO> clientesAtivos() {
        return ResponseEntity.ok(relatorioService.clientesAtivos());
    }

    // Pedidos por período
    @GetMapping("/pedidos-por-periodo")
    public ResponseEntity<RelatorioPedidosPeriodoDTO> pedidosPorPeriodo(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {
        return ResponseEntity.ok(relatorioService.pedidosPorPeriodo(inicio, fim));
    }
}
