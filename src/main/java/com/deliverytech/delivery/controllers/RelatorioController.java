package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.dto.RelatorioClientesAtivosDTO;
import com.deliverytech.delivery.dto.RelatorioPedidosPeriodoDTO;
import com.deliverytech.delivery.dto.RelatorioProdutosMaisVendidosDTO;
import com.deliverytech.delivery.dto.RelatorioVendasPorRestauranteDTO;
import com.deliverytech.delivery.service.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/relatorios")
@Tag(name = "Relatórios", description = "Endpoints para geração de relatórios do sistema")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    // --------------------------------------------------------------------
    @Operation(
            summary = "Relatório de vendas por restaurante",
            description = "Retorna o total de vendas agrupado por restaurante.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Relatório retornado com sucesso",
                            content = @Content(schema = @Schema(implementation = RelatorioVendasPorRestauranteDTO.class))
                    )
            }
    )
    @GetMapping("/vendas-por-restaurante")
    public ResponseEntity<RelatorioVendasPorRestauranteDTO> vendasPorRestaurante() {
        return ResponseEntity.ok(relatorioService.vendasPorRestaurante());
    }

    // --------------------------------------------------------------------
    @Operation(
            summary = "Produtos mais vendidos",
            description = "Retorna uma lista com os produtos mais vendidos do sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Relatório retornado com sucesso",
                            content = @Content(schema = @Schema(implementation = RelatorioProdutosMaisVendidosDTO.class))
                    )
            }
    )
    @GetMapping("/produtos-mais-vendidos")
    public ResponseEntity<RelatorioProdutosMaisVendidosDTO> produtosMaisVendidos() {
        return ResponseEntity.ok(relatorioService.produtosMaisVendidos());
    }

    // --------------------------------------------------------------------
    @Operation(
            summary = "Clientes mais ativos",
            description = "Retorna um ranking dos clientes que mais realizaram pedidos.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Relatório retornado com sucesso",
                            content = @Content(schema = @Schema(implementation = RelatorioClientesAtivosDTO.class))
                    )
            }
    )
    @GetMapping("/clientes-ativos")
    public ResponseEntity<RelatorioClientesAtivosDTO> clientesAtivos() {
        return ResponseEntity.ok(relatorioService.clientesAtivos());
    }

    // --------------------------------------------------------------------
    @Operation(
            summary = "Pedidos por período",
            description = "Retorna a quantidade de pedidos entre duas datas informadas.",
            parameters = {
                    @Parameter(name = "inicio", description = "Data inicial do período", required = true),
                    @Parameter(name = "fim", description = "Data final do período", required = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Relatório retornado com sucesso",
                            content = @Content(schema = @Schema(implementation = RelatorioPedidosPeriodoDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datas inválidas ou fora do formato esperado"
                    )
            }
    )
    @GetMapping("/pedidos-por-periodo")
    public ResponseEntity<RelatorioPedidosPeriodoDTO> pedidosPorPeriodo(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim
    ) {
        return ResponseEntity.ok(relatorioService.pedidosPorPeriodo(inicio, fim));
    }
}
