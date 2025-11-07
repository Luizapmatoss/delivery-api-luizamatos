package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ProdutoDTO;
import com.deliverytech.delivery.models.Produto;
import com.deliverytech.delivery.models.Restaurante;

import java.util.List;

public interface ProdutoService {

    // Cadastrar produto em um restaurante específico
    Produto cadastrarProduto(Long restauranteId, ProdutoDTO dto);

    // Buscar produtos de um restaurante
    List<Produto> buscarProdutosPorRestaurante(Long restauranteId);

    // Buscar um produto específico dentro de um restaurante
    Restaurante buscarProdutoPorIdERestaurante(Long restauranteId, Long id);

    // Buscar por categoria 
    List<Produto> buscarProdutosPorCategoria(String categoria);

    // Atualizar informações de um produto específico
    Produto atualizarProduto(Long restauranteId, Long id, ProdutoDTO dto);

    // Alterar disponibilidade 
    Produto alterarDisponibilidade(Long restauranteId, Long id, boolean disponivel);

    // Deletar um produto
    void deletar(Long id);

    Produto buscarProdutoPorId(Long id);

    Produto atualizarProduto(Long id, ProdutoDTO dto);

    Produto alterarDisponibilidade(Long id, boolean disponivel);

    Produto cadastrarProduto(ProdutoDTO dto);

    Produto cadastrar(Long restauranteId, Produto produto);

    Object listarTodos();

    Produto atualizarProduto(Long id, Produto produto);
}
