package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.dto.ProdutoDTO;
import com.deliverytech.delivery.dto.response.ProdutoResponseDTO;
import com.deliverytech.delivery.models.Produto;
import com.deliverytech.delivery.models.Restaurante;
import com.deliverytech.delivery.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurantes/{restauranteId}/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(
            @PathVariable Long restauranteId,
            @Valid @RequestBody ProdutoDTO dto) {

        Produto produto = produtoService.cadastrarProduto(restauranteId, dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(toResponse(produto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarPorRestaurante(@PathVariable Long restauranteId) {
        List<ProdutoResponseDTO> produtos = produtoService.buscarProdutosPorRestaurante(restauranteId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(
            @PathVariable Long restauranteId,
            @PathVariable Long id) {

        Restaurante produto = produtoService.buscarProdutoPorIdERestaurante(restauranteId, id);
        return ResponseEntity.ok(toResponse(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable Long restauranteId,
            @PathVariable Long id,
            @Valid @RequestBody ProdutoDTO dto) {

        Produto atualizado = produtoService.atualizarProduto(restauranteId, id, dto);
        return ResponseEntity.ok(toResponse(atualizado));
    }

    @PatchMapping("/{id}/disponibilidade")
    public ResponseEntity<Object> alterarDisponibilidade(
            @PathVariable Long restauranteId,
            @PathVariable Long id,
            @RequestParam boolean disponivel) {

        Produto produto = produtoService.alterarDisponibilidade(restauranteId, id, disponivel);
        return ResponseEntity.ok(toResponse(produto));
    }

    private Object toResponse(Restaurante produto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toResponse'");
    }

    private ProdutoResponseDTO toResponse(Produto atualizado) {
        return new ProdutoResponseDTO(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getDescricao(),
                atualizado.getCategoria(),
                atualizado.getPreco(),
                atualizado.getDisponivel(),
                atualizado.getRestaurante().getId()
        );
    }
}
