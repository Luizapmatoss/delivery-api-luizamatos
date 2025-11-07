package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ProdutoDTO;
import com.deliverytech.delivery.models.Produto;
import com.deliverytech.delivery.models.Restaurante;
import com.deliverytech.delivery.repository.ProdutoRepository;
import com.deliverytech.delivery.repository.RestauranteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final RestauranteRepository restauranteRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, RestauranteRepository restauranteRepository) {
        this.produtoRepository = produtoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    @Transactional
    public Produto cadastrarProduto(Long restauranteId, ProdutoDTO dto) {
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado!"));

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setCategoria(dto.getCategoria());
        produto.setPreco(dto.getPreco());
        produto.setDisponivel(dto.getDisponivel());
        produto.setRestaurante(restaurante);

        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> buscarProdutosPorRestaurante(Long restauranteId) {
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado!"));
        return produtoRepository.findByRestaurante(restaurante);
    }

    @Override
    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado!"));
    }

    @Override
    @Transactional
    public Produto atualizarProduto(Long id, ProdutoDTO dto) {
        Produto produto = buscarProdutoPorId(id);
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setCategoria(dto.getCategoria());
        produto.setPreco(dto.getPreco());
        produto.setDisponivel(dto.getDisponivel());
        return produtoRepository.save(produto);
    }

    @Override
    @Transactional
    public Produto alterarDisponibilidade(Long id, boolean disponivel) {
        Produto produto = buscarProdutoPorId(id);
        produto.setDisponivel(disponivel);
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(String categoria) {
        return produtoRepository.findByCategoriaIgnoreCase(categoria);
    }

    // Métodos abaixo não são usados, mas estão aqui para cumprir a interface
    @Override
    public Produto cadastrarProduto(ProdutoDTO dto) {
        throw new UnsupportedOperationException("Use cadastrarProduto(Long restauranteId, ProdutoDTO dto)");
    }

    @Override
    public Produto atualizarProduto(Long id, Long id2, ProdutoDTO dto) {
        throw new UnsupportedOperationException("Não utilizado neste projeto");
    }

    @Override
    public Produto cadastrar(Long restauranteId, Produto produto) {
        throw new UnsupportedOperationException("Não utilizado neste projeto");
    }

    @Override
    public Produto alterarDisponibilidade(Long id, Long id2, boolean disponivel) {
        throw new UnsupportedOperationException("Não utilizado neste projeto");
    }

    @Override
    public Restaurante buscarProdutoPorIdERestaurante(Long restauranteId, Long id) {
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado!"));
        return produtoRepository.findByIdAndRestaurante(id, restaurante)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado para este restaurante!"));
    }

    @Override
    public Object listarTodos() {
        return produtoRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public Produto atualizarProduto(Long id, Produto produto) {
        throw new UnsupportedOperationException("Não utilizado neste projeto");
    }
}
