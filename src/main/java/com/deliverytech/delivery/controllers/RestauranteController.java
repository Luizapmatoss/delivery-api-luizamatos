package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.dto.RestauranteDTO;
import com.deliverytech.delivery.dto.response.RestauranteResponseDTO;
import com.deliverytech.delivery.models.Restaurante;
import com.deliverytech.delivery.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @PostMapping
    public ResponseEntity<RestauranteResponseDTO> cadastrar(@Valid @RequestBody RestauranteDTO dto) {
        Restaurante restaurante = restauranteService.cadastrarRestaurante(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(restaurante.getId())
                .toUri();
        return ResponseEntity.created(uri).body(toResponse(restaurante));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> buscarPorId(@PathVariable Long id) {
        Restaurante restaurante = restauranteService.buscarRestaurantePorId(id);
        return ResponseEntity.ok(toResponse(restaurante));
    }

    @GetMapping
    public ResponseEntity<List<RestauranteResponseDTO>> listarDisponiveis() {
        List<RestauranteResponseDTO> restaurantes = restauranteService.listarDisponiveis()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<RestauranteResponseDTO>> buscarPorCategoria(@PathVariable String categoria) {
        List<RestauranteResponseDTO> restaurantes = restauranteService.buscarRestaurantesPorCategoria(categoria)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurantes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody RestauranteDTO dto) {
        Restaurante atualizado = restauranteService.atualizarRestaurante(id, dto);
        return ResponseEntity.ok(toResponse(atualizado));
    }

    @GetMapping("/{id}/taxa-entrega/{cep}")
    public ResponseEntity<Double> calcularTaxa(
            @PathVariable Long id,
            @PathVariable String cep) {
        Double taxa = restauranteService.calcularTaxaEntrega(id, cep);
        return ResponseEntity.ok(taxa);
    }

    private RestauranteResponseDTO toResponse(Restaurante restaurante) {
        return new RestauranteResponseDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getCategoria(),
                restaurante.getAvaliacao(),
                restaurante.getAtivo()
        );
    }
}

