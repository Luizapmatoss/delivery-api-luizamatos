package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ProdutoDTO;
import com.deliverytech.delivery.models.Produto;
import com.deliverytech.delivery.models.Restaurante;
import com.deliverytech.delivery.repository.ProdutoRepository;
import com.deliverytech.delivery.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public Produto cadastrarProduto(ProdutoDTO dto) {
        Restaurante restaurante = restauranteRepository.findById(dto.getRestauranteId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado!"));

        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório!");
        }
        if (dto.getPreco() == null || dto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero!");
        }

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
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado!"));

        return produtoRepository.findByRestaurante(restaurante);
    }

    @Override
    public Produto buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado!"));

        if (!produto.getDisponivel()) {
            throw new IllegalArgumentException("Produto indisponível no momento!");
        }

        return produto;
    }

    @Override
    public Produto atualizarProduto(Long id, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado!"));

        if (dto.getNome() != null && !dto.getNome().isBlank()) {
            produto.setNome(dto.getNome());
        }
        if (dto.getDescricao() != null) {
            produto.setDescricao(dto.getDescricao());
        }
        if (dto.getCategoria() != null) {
            produto.setCategoria(dto.getCategoria());
        }
        if (dto.getPreco() != null && dto.getPreco().compareTo(BigDecimal.ZERO) > 0) {
            produto.setPreco(dto.getPreco());
        }

        return produtoRepository.save(produto);
    }

    @Override
    public Produto alterarDisponibilidade(Long id, boolean disponivel) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado!"));

        produto.setDisponivel(disponivel);
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(String categoria) {
        return produtoRepository.findByCategoriaIgnoreCase(categoria);
    }

    @Override
    public Produto atualizarProduto(Long id, Produto produto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarProduto'");
    }

    @Override
    public Produto cadastrar(Long restauranteId, Produto produto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastrar'");
    }

    @Override
    public Object listarTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodos'");
    }

    @Override
    public void deletar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }
}
