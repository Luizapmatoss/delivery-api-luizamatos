package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.models.Produto;
import com.deliverytech.delivery.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/restaurante/{restauranteId}")
    public ResponseEntity<Produto> cadastrar(@PathVariable Long restauranteId, @RequestBody Produto produto) {
        return ResponseEntity.status(201).body(produtoService.cadastrar(restauranteId, produto));
    }

    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<Produto>> listarPorRestaurante(@PathVariable Long restauranteId) {
        return ResponseEntity.ok(produtoService.listarPorRestaurante(restauranteId));
    }

    @PutMapping("/{id}/disponibilidade")
    public ResponseEntity<Void> alterarDisponibilidade(@PathVariable Long id, @RequestParam boolean disponivel) {
        produtoService.alterarDisponibilidade(id, disponivel);
        return ResponseEntity.noContent().build();
    }
}
