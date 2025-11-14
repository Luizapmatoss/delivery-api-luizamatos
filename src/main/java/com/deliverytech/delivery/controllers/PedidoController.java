package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.dto.PedidoDTO;
import com.deliverytech.delivery.models.Pedido;
import com.deliverytech.delivery.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(
    name = "Pedidos",
    description = "Endpoints para criação, consulta, atualização e cancelamento de pedidos"
)
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // ============================================
    // POST - Criar Pedido
    // ============================================
    @PostMapping
    @Operation(
            summary = "Criar um novo pedido",
            description = "Recebe um PedidoDTO e cria um novo pedido no sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso",
                            content = @Content(schema = @Schema(implementation = Pedido.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
            }
    )
    public ResponseEntity<Pedido> criar(
            @Valid @RequestBody
            @Parameter(description = "Dados necessários para criar um pedido") PedidoDTO dto) {

        Pedido novo = pedidoService.criarPedido(dto);
        URI uri = URI.create("/api/pedidos/" + novo.getId());
        return ResponseEntity.created(uri).body(novo);
    }

    // ============================================
    // GET - Buscar por ID
    // ============================================
    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar pedido por ID",
            description = "Retorna todas as informações de um pedido específico.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
            }
    )
    public ResponseEntity<Pedido> buscarPorId(
            @Parameter(description = "ID do pedido", example = "1")
            @PathVariable Long id) {

        return ResponseEntity.ok(pedidoService.buscarPedidoPorId(id));
    }

    // ============================================
    // GET - Listar com filtros
    // ============================================
    @GetMapping
    @Operation(
            summary = "Listar pedidos com filtros opcionais",
            description = "Permite filtrar pedidos por status e data.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
            }
    )
    public ResponseEntity<List<Pedido>> listarComFiltros(
            @Parameter(description = "Status do pedido", example = "PENDENTE")
            @RequestParam(required = false) String status,

            @Parameter(description = "Data do pedido no formato YYYY-MM-DD", example = "2025-01-10")
            @RequestParam(required = false) LocalDate data) {

        List<Pedido> pedidos = pedidoService.listarComFiltros(status, data);
        return ResponseEntity.ok(pedidos);
    }

    // ============================================
    // GET - Histórico do cliente
    // ============================================
    @GetMapping("/cliente/{clienteId}")
    @Operation(
            summary = "Listar pedidos de um cliente",
            description = "Retorna o histórico de pedidos feitos por um cliente específico.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedidos encontrados"),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
            }
    )
    public ResponseEntity<Object> listarPorCliente(
            @Parameter(description = "ID do cliente", example = "10")
            @PathVariable Long clienteId) {

        return ResponseEntity.ok(pedidoService.listarPorCliente(clienteId));
    }

    // ============================================
    // GET - Pedidos de restaurante
    // ============================================
    @GetMapping("/restaurante/{restauranteId}")
    @Operation(
            summary = "Listar pedidos de um restaurante",
            description = "Retorna pedidos associados a um restaurante específico.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedidos encontrados"),
                    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
            }
    )
    public ResponseEntity<Object> listarPorRestaurante(
            @Parameter(description = "ID do restaurante", example = "5")
            @PathVariable Long restauranteId) {

        return ResponseEntity.ok(pedidoService.listarPorRestaurante(restauranteId));
    }

    // ============================================
    // PATCH - Atualizar status do pedido
    // ============================================
    @PatchMapping("/{id}/status")
    @Operation(
            summary = "Atualizar status de um pedido",
            description = "Atualiza apenas o status do pedido. Exemplo: PENDENTE → ENTREGUE.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Status atualizado"),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
            }
    )
    public ResponseEntity<Void> atualizarStatus(
            @Parameter(description = "ID do pedido", example = "1")
            @PathVariable Long id,

            @Parameter(description = "Novo status do pedido", example = "ENTREGUE")
            @RequestParam String status) {

        pedidoService.atualizarStatusPedido(id, status);
        return ResponseEntity.noContent().build();
    }

    // ============================================
    // DELETE - Cancelar pedido
    // ============================================
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Cancelar pedido",
            description = "Remove um pedido do sistema.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Pedido cancelado"),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
            }
    )
    public ResponseEntity<Void> cancelar(
            @Parameter(description = "ID do pedido a ser cancelado", example = "1")
            @PathVariable Long id) {

        pedidoService.cancelarPedido(id);
        return ResponseEntity.noContent().build();
    }

    // ============================================
    // POST - Calcular total sem salvar
    // ============================================
    @PostMapping("/calcular")
    @Operation(
            summary = "Calcular valor total do pedido (sem salvar)",
            description = "Recebe itens, quantidades e preços e retorna o valor total estimado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cálculo realizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            }
    )
    public ResponseEntity<Double> calcularTotal(
            @Valid @RequestBody
            @Parameter(description = "Dados de pedido para cálculo, sem persistência")
            PedidoDTO dto) {

        Double total = pedidoService.calcularTotalPedido(dto);
        return ResponseEntity.ok(total);
    }
}
