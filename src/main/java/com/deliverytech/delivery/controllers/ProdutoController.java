package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.dto.ProdutoDTO;
import com.deliverytech.delivery.dto.response.ProdutoResponseDTO;
import com.deliverytech.delivery.models.Produto;
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
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@Valid @RequestBody ProdutoDTO dto) {
        Produto produto = produtoService.cadastrarProduto(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(toResponse(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(toResponse(produto));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorCategoria(@PathVariable String categoria) {
        List<ProdutoResponseDTO> produtos = produtoService.buscarProdutosPorCategoria(categoria)
                .stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/restaurantes/{restauranteId}/produtos")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorRestaurante(@PathVariable Long restauranteId) {
        List<ProdutoResponseDTO> produtos = produtoService.buscarProdutosPorRestaurante(restauranteId)
                .stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto) {
        Produto atualizado = produtoService.atualizarProduto(id, dto);
        return ResponseEntity.ok(toResponse(atualizado));
    }

    @PatchMapping("/{id}/disponibilidade")
    public ResponseEntity<ProdutoResponseDTO> alterarDisponibilidade(@PathVariable Long id, @RequestParam boolean disponivel) {
        Produto produto = produtoService.alterarDisponibilidade(id, disponivel);
        return ResponseEntity.ok(toResponse(produto));
    }

    private ProdutoResponseDTO toResponse(Produto p) {
        return new ProdutoResponseDTO(
                p.getId(), p.getNome(), p.getDescricao(),
                p.getCategoria(), p.getPreco(),
                p.getDisponivel(), p.getRestaurante().getId()
        );
    }
}
