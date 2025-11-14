package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ProdutoDTO;
import com.deliverytech.delivery.models.Produto;
import com.deliverytech.delivery.models.Restaurante;

import java.util.List;

public interface ProdutoService {

    // Criar produto dentro de um restaurante
    Produto cadastrarProduto(Long restauranteId, ProdutoDTO dto);

    // Listar produtos de um restaurante
    List<Produto> buscarProdutosPorRestaurante(Long restauranteId);

    // Buscar produto específico dentro do restaurante
    Produto buscarProdutoPorIdERestaurante(Long restauranteId, Long id);

    // Buscar por categoria
    List<Produto> buscarProdutosPorCategoria(String categoria);

    // Buscar por nome (contém)
    List<Produto> buscarPorNome(String nome);

    // Atualizar produto dentro de um restaurante
    Produto atualizarProduto(Long restauranteId, Long id, ProdutoDTO dto);

    // Alterar disponibilidade
    Produto alterarDisponibilidade(Long restauranteId, Long id, boolean disponivel);

    // Deletar produto
    void deletar(Long id);

    // Listar todos
    List<Produto> listarTodos();
}
