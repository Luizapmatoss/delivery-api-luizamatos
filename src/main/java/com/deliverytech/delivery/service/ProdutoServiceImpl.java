package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ProdutoDTO;
import com.deliverytech.delivery.models.Produto;
import com.deliverytech.delivery.models.Restaurante;
import com.deliverytech.delivery.repository.ProdutoRepository;
import com.deliverytech.delivery.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public Produto cadastrarProduto(Long restauranteId, ProdutoDTO dto) {

        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setCategoria(dto.getCategoria());
        produto.setPreco(dto.getPreco());
        produto.setDisponivel(true);
        produto.setRestaurante(restaurante);

        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> buscarProdutosPorRestaurante(Long restauranteId) {
        return produtoRepository.findByRestauranteId(restauranteId);
    }

    @Override
    public Produto buscarProdutoPorIdERestaurante(Long restauranteId, Long id) {
        return produtoRepository.findByIdAndRestauranteId(id, restauranteId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado para este restaurante"));
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(String categoria) {
        return produtoRepository.findByCategoriaIgnoreCase(categoria);
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public Produto atualizarProduto(Long restauranteId, Long id, ProdutoDTO dto) {
        Produto produto = buscarProdutoPorIdERestaurante(restauranteId, id);

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setCategoria(dto.getCategoria());
        produto.setPreco(dto.getPreco());

        return produtoRepository.save(produto);
    }

    @Override
    public Produto alterarDisponibilidade(Long restauranteId, Long id, boolean disponivel) {
        Produto produto = buscarProdutoPorIdERestaurante(restauranteId, id);

        produto.setDisponivel(disponivel);
        return produtoRepository.save(produto);
    }

    @Override
    public void deletar(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoRepository.delete(produto);
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
}
