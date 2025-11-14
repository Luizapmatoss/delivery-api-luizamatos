package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.dto.RestauranteDTO;
import com.deliverytech.delivery.dto.response.RestauranteResponseDTO;
import com.deliverytech.delivery.models.Restaurante;
import com.deliverytech.delivery.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurantes")
@Tag(name = "Restaurantes", description = "Gerenciamento de restaurantes e funcionalidades relacionadas")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    // ------------------------------------------------------------------------

    @Operation(summary = "Cadastrar restaurante", description = "Cria um novo restaurante no sistema.", responses = {
            @ApiResponse(responseCode = "201", description = "Restaurante cadastrado com sucesso", content = @Content(schema = @Schema(implementation = RestauranteResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição")
    })
    @PostMapping
    public ResponseEntity<RestauranteResponseDTO> cadastrar(
            @Valid @RequestBody RestauranteDTO dto) {

        Restaurante restaurante = restauranteService.cadastrarRestaurante(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(restaurante.getId())
                .toUri();

        return ResponseEntity.created(uri).body(toResponse(restaurante));
    }

    // ------------------------------------------------------------------------

    @Operation(summary = "Buscar restaurante por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Restaurante encontrado", content = @Content(schema = @Schema(implementation = RestauranteResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> buscarPorId(
            @Parameter(description = "ID do restaurante") @PathVariable final Long id) {

        Restaurante restaurante = restauranteService.buscarRestaurantePorId(id);
        return ResponseEntity.ok(toResponse(restaurante));
    }

    // ------------------------------------------------------------------------

    @Operation(summary = "Listar restaurantes com filtros opcionais", description = "Permite filtrar restaurantes por categoria e/ou status (ativo ou inativo).", responses = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<RestauranteResponseDTO>> listarComFiltros(
            @Parameter(description = "Categoria do restaurante") @RequestParam(required = false) String categoria,
            @Parameter(description = "Status do restaurante (true = ativo, false = inativo)") @RequestParam(required = false) Boolean ativo) {

        List<Restaurante> restaurantes = restauranteService.listarComFiltros(categoria, ativo);

        List<RestauranteResponseDTO> response = restaurantes.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // ------------------------------------------------------------------------

    @Operation(summary = "Buscar restaurantes por categoria", responses = {
            @ApiResponse(responseCode = "200", description = "Lista retornada", content = @Content(schema = @Schema(implementation = RestauranteResponseDTO.class)))
    })
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<RestauranteResponseDTO>> buscarPorCategoria(
            @Parameter(description = "Categoria desejada") @PathVariable String categoria) {

        List<RestauranteResponseDTO> restaurantes = restauranteService.buscarRestaurantesPorCategoria(categoria)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(restaurantes);
    }

    // ------------------------------------------------------------------------

    @Operation(summary = "Atualizar restaurante", description = "Atualiza dados gerais do restaurante.", responses = {
            @ApiResponse(responseCode = "200", description = "Restaurante atualizado com sucesso", content = @Content(schema = @Schema(implementation = RestauranteResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> atualizar(
            @Parameter(description = "ID do restaurante") @PathVariable Long id,
            @Valid @RequestBody RestauranteDTO dto) {

        Restaurante atualizado = restauranteService.atualizarRestaurante(id, dto);
        return ResponseEntity.ok(toResponse(atualizado));
    }

    // ------------------------------------------------------------------------

    @Operation(summary = "Alterar status do restaurante", description = "Ativa ou desativa o restaurante.", responses = {
            @ApiResponse(responseCode = "200", description = "Status alterado", content = @Content(schema = @Schema(implementation = RestauranteResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<RestauranteResponseDTO> alterarStatus(
            @Parameter(description = "ID do restaurante") @PathVariable Long id) {

        Restaurante atualizado = restauranteService.alterarStatus(id);
        return ResponseEntity.ok(toResponse(atualizado));
    }

    // ------------------------------------------------------------------------

    @Operation(summary = "Calcular taxa de entrega", description = "Calcula a taxa de entrega para um CEP específico.", parameters = {
            @Parameter(name = "cep", description = "CEP do cliente", required = true)
    }, responses = {
            @ApiResponse(responseCode = "200", description = "Taxa calculada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    @GetMapping("/{id}/taxa-entrega/{cep}")
    public ResponseEntity<Double> calcularTaxa(
            @Parameter(description = "ID do restaurante") @PathVariable Long id,
            @Parameter(description = "CEP de destino") @PathVariable String cep) {

        Double taxa = restauranteService.calcularTaxaEntrega(id, cep);
        return ResponseEntity.ok(taxa);
    }

    // ------------------------------------------------------------------------

    @Operation(summary = "Listar restaurantes próximos", description = "Retorna restaurantes próximos ao CEP informado.", parameters = {
            @Parameter(name = "cep", description = "CEP de referência", required = true)
    }, responses = {
            @ApiResponse(responseCode = "200", description = "Lista de restaurantes próxima retornada")
    })
    @GetMapping("/proximos/{cep}")
    public ResponseEntity<List<RestauranteResponseDTO>> listarProximos(
            @Parameter(description = "CEP do usuário") @PathVariable String cep) {

        List<Restaurante> proximos = restauranteService.listarProximos(cep);

        List<RestauranteResponseDTO> response = proximos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // ------------------------------------------------------------------------

    private RestauranteResponseDTO toResponse(Restaurante restaurante) {
        return new RestauranteResponseDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getCategoria(),
                restaurante.getAvaliacao(),
                restaurante.getAtivo());
    }

     // ------------------------------------------------------------------------

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RestauranteResponseDTO>> buscarPorId(@PathVariable Long id) {
        Restaurante restaurante = restauranteService.buscarRestaurantePorId(id);
        return ResponseEntity.ok(ApiResponse.ok(toResponse(restaurante)));
    }

}
