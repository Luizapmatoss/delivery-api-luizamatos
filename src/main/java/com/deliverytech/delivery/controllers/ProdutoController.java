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
@RequestMapping("/api")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // PRODUTOS DO RESTAURANTE
    @PostMapping("/restaurantes/{restauranteId}/produtos")
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

    @GetMapping("/restaurantes/{restauranteId}/produtos")
    public ResponseEntity<List<ProdutoResponseDTO>> listarPorRestaurante(@PathVariable Long restauranteId) {
        List<ProdutoResponseDTO> produtos = produtoService.buscarProdutosPorRestaurante(restauranteId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/restaurantes/{restauranteId}/produtos/{id}")
    public ResponseEntity<Object> buscarPorId(
            @PathVariable Long restauranteId,
            @PathVariable Long id) {

        Produto produto = produtoService.buscarProdutoPorIdERestaurante(restauranteId, id);
        return ResponseEntity.ok(toResponse(produto));
    }

    private Object toResponse(Restaurante produto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toResponse'");
    }

    @PutMapping("/restaurantes/{restauranteId}/produtos/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable Long restauranteId,
            @PathVariable Long id,
            @Valid @RequestBody ProdutoDTO dto) {

        Produto atualizado = produtoService.atualizarProduto(restauranteId, id, dto);
        return ResponseEntity.ok(toResponse(atualizado));
    }

    @PatchMapping("/restaurantes/{restauranteId}/produtos/{id}/disponibilidade")
    public ResponseEntity<ProdutoResponseDTO> alterarDisponibilidade(
            @PathVariable Long restauranteId,
            @PathVariable Long id,
            @RequestParam boolean disponivel) {

        Produto produto = produtoService.alterarDisponibilidade(restauranteId, id, disponivel);
        return ResponseEntity.ok(toResponse(produto));
    }

    // DELETE /api/produtos/{id}
    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/produtos/categoria/{categoria}
    @GetMapping("/produtos/categoria/{categoria}")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorCategoria(@PathVariable String categoria) {
        List<ProdutoResponseDTO> produtos = produtoService.buscarProdutosPorCategoria(categoria)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }

    // GET /api/produtos/buscar?nome={nome}
    @GetMapping("/produtos/buscar")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorNome(@RequestParam String nome) {
        List<Produto> produtos = produtoService.buscarPorNome(nome);

        List<ProdutoResponseDTO> response = produtos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // Conversor para DTO
    private ProdutoResponseDTO toResponse(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getCategoria(),
                produto.getPreco(),
                produto.getDisponivel(),
                produto.getRestaurante().getId()
        );
    }
}
