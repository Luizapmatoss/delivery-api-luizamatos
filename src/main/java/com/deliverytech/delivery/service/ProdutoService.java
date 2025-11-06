package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ProdutoDTO;
import com.deliverytech.delivery.models.Produto;

import jakarta.validation.Valid;

import java.util.List;

public interface ProdutoService {

    Produto cadastrarProduto(ProdutoDTO dto);

    List<Produto> buscarProdutosPorRestaurante(Long restauranteId);

    Produto buscarProdutoPorId(Long id);

    Produto atualizarProduto(Long id, Produto produto);

    Produto alterarDisponibilidade(Long id, boolean disponivel);

    List<Produto> buscarProdutosPorCategoria(String categoria);

    Produto cadastrar(Long restauranteId, Produto produto);

    Object listarTodos();

    void deletar(Long id);

    Produto atualizarProduto(Long id, ProdutoDTO dto);
}
