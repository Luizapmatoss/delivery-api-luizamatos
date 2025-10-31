package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.models.Restaurante;
import com.deliverytech.delivery.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    // Criar restaurante
    @PostMapping
    public ResponseEntity<Restaurante> cadastrar(@Valid @RequestBody Restaurante restaurante) {
        Restaurante novoRestaurante = restauranteService.cadastrar(restaurante);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoRestaurante.getId())
                .toUri();

        return ResponseEntity.created(uri).body(novoRestaurante);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Restaurante>> listarTodos() {
        return ResponseEntity.ok(restauranteService.listarTodos());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long id) {
        Restaurante restaurante = restauranteService.buscarPorId(id);
        if (restaurante == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurante);
    }

    // Atualizar dados do restaurante
    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long id, @Valid @RequestBody Restaurante restaurante) {
        Restaurante atualizado = restauranteService.atualizar(id, restaurante);
        return ResponseEntity.ok(atualizado);
    }

    // Alterar status (ativo/inativo)
    @PatchMapping("/{id}/status")

    public ResponseEntity<Restaurante> alterarStatus(@PathVariable Long id, @RequestParam boolean ativo) {
        Restaurante restauranteAtualizado = restauranteService.alterarStatus(id, ativo);
        if (restauranteAtualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restauranteAtualizado);
    }

    // Deletar restaurante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        restauranteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por categoria
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Restaurante>> buscarPorCategoria(@PathVariable String categoria) {
        List<Restaurante> restaurantes = restauranteService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(restaurantes);
    }
}
