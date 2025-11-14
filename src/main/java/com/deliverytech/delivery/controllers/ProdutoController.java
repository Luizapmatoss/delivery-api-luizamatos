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

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Tag(name = "Produtos", description = "Gerenciamento de produtos cadastrados pelos restaurantes")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // ============================================
    // CADASTRAR PRODUTO EM RESTAURANTE
    // ============================================
    @PostMapping("/restaurantes/{restauranteId}/produtos")
    @Operation(
            summary = "Cadastrar produto",
            description = "Cadastra um produto dentro de um restaurante específico.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Produto cadastrado",
                            content = @Content(schema = @Schema(implementation = ProdutoResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            }
    )
    public ResponseEntity<ProdutoResponseDTO> cadastrar(
            @Parameter(description = "ID do restaurante") @PathVariable Long restauranteId,
            @Valid @RequestBody
            @Parameter(description = "Dados do produto") ProdutoDTO dto) {

        Produto produto = produtoService.cadastrarProduto(restauranteId, dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(toResponse(produto));
    }

    // ============================================
    // LISTAR PRODUTOS DE RESTAURANTE
    // ============================================
    @GetMapping("/restaurantes/{restauranteId}/produtos")
    @Operation(
            summary = "Listar produtos do restaurante",
            description = "Retorna todos os produtos associados a um restaurante."
    )
    public ResponseEntity<List<ProdutoResponseDTO>> listarPorRestaurante(
            @Parameter(description = "ID do restaurante") @PathVariable Long restauranteId) {

        List<ProdutoResponseDTO> produtos = produtoService.buscarProdutosPorRestaurante(restauranteId)
                .stream().map(this::toResponse).collect(Collectors.toList());

        return ResponseEntity.ok(produtos);
    }

    // ============================================
    // BUSCAR PRODUTO ESPECÍFICO EM UM RESTAURANTE
    // ============================================
    @GetMapping("/restaurantes/{restauranteId}/produtos/{id}")
    @Operation(
            summary = "Buscar produto por ID dentro do restaurante",
            description = "Retorna um produto garantindo que pertence ao restaurante informado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produto encontrado"),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
            }
    )
    public ResponseEntity<Object> buscarPorId(
            @Parameter(description = "ID do restaurante") @PathVariable Long restauranteId,
            @Parameter(description = "ID do produto") @PathVariable Long id) {

        Produto produto = produtoService.buscarProdutoPorIdERestaurante(restauranteId, id);
        return ResponseEntity.ok(toResponse(produto));
    }

    // ============================================
    // ATUALIZAR PRODUTO
    // ============================================
    @PutMapping("/restaurantes/{restauranteId}/produtos/{id}")
    @Operation(
            summary = "Atualizar produto",
            description = "Atualiza os dados de um produto dentro de um restaurante."
    )
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @Parameter(description = "ID do restaurante") @PathVariable Long restauranteId,
            @Parameter(description = "ID do produto") @PathVariable Long id,
            @Valid @RequestBody ProdutoDTO dto) {

        Produto atualizado = produtoService.atualizarProduto(restauranteId, id, dto);
        return ResponseEntity.ok(toResponse(atualizado));
    }

    // ============================================
    // ALTERAR DISPONIBILIDADE
    // ============================================
    @PatchMapping("/restaurantes/{restauranteId}/produtos/{id}/disponibilidade")
    @Operation(
            summary = "Alterar disponibilidade do produto",
            description = "Ativa ou desativa um produto no cardápio."
    )
    public ResponseEntity<ProdutoResponseDTO> alterarDisponibilidade(
            @PathVariable Long restauranteId,
            @PathVariable Long id,
            @Parameter(description = "true = disponível, false = indisponível") @RequestParam boolean disponivel) {

        Produto produto = produtoService.alterarDisponibilidade(restauranteId, id, disponivel);
        return ResponseEntity.ok(toResponse(produto));
    }

    // ============================================
    // DELETE
    // ============================================
    @DeleteMapping("/produtos/{id}")
    @Operation(
            summary = "Excluir produto",
            description = "Remove um produto da base de dados."
    )
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do produto") @PathVariable Long id) {

        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // ============================================
    // BUSCAR POR CATEGORIA
    // ============================================
    @GetMapping("/produtos/categoria/{categoria}")
    @Operation(
            summary = "Buscar produtos por categoria",
            description = "Lista produtos filtrando pela categoria."
    )
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorCategoria(
            @Parameter(description = "Categoria do produto", example = "bebidas") @PathVariable String categoria) {

        List<ProdutoResponseDTO> produtos = produtoService.buscarProdutosPorCategoria(categoria)
                .stream().map(this::toResponse).collect(Collectors.toList());

        return ResponseEntity.ok(produtos);
    }

    // ============================================
    // BUSCAR POR NOME (LIKE)
    // ============================================
    @GetMapping("/produtos/buscar")
    @Operation(
            summary = "Buscar produtos por nome",
            description = "Busca produtos contendo parte do nome informado."
    )
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorNome(
            @Parameter(description = "Termo de busca", example = "pizza") @RequestParam String nome) {

        List<Produto> produtos = produtoService.buscarPorNome(nome);

        List<ProdutoResponseDTO> response = produtos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // ============================================
    // CONVERSOR
    // ============================================
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
