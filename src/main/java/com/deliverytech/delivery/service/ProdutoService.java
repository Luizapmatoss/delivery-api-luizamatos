package com.deliverytech.delivery.service;

import com.deliverytech.delivery.models.Produto;
import com.deliverytech.delivery.models.Restaurante;
import com.deliverytech.delivery.repository.ProdutoRepository;
import com.deliverytech.delivery.repository.RestauranteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final RestauranteRepository restauranteRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          RestauranteRepository restauranteRepository) {
        this.produtoRepository = produtoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    // Criar produto
    public Produto cadastrar(Long restauranteId, Produto produto) {
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado!"));

        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero!");
        }

        produto.setRestaurante(restaurante);
        produto.setDisponivel(true);
        return produtoRepository.save(produto);
    }

    // Atualizar produto
    public Produto atualizar(Long produtoId, Produto dadosAtualizados) {
        Produto produto = buscarPorId(produtoId);

        if (dadosAtualizados.getNome() != null) produto.setNome(dadosAtualizados.getNome());
        if (dadosAtualizados.getDescricao() != null) produto.setDescricao(dadosAtualizados.getDescricao());
        if (dadosAtualizados.getCategoria() != null) produto.setCategoria(dadosAtualizados.getCategoria());
        if (dadosAtualizados.getPreco() > 0) produto.setPreco(dadosAtualizados.getPreco());

        return produtoRepository.save(produto);
    }

    // Buscar todos
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Buscar por id
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado!"));
    }

    // Alterar disponibilidade
    public void alterarDisponibilidade(Long id, boolean disponivel) {
        Produto produto = buscarPorId(id);
        produto.setDisponivel(disponivel);
        produtoRepository.save(produto);
    }

    // Listar por restaurante
    public List<Produto> listarPorRestaurante(Long restauranteId) {
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado!"));
        return produtoRepository.findByRestaurante(restaurante);
    }

    // Listar por categoria
    public List<Produto> listarPorCategoria(String categoria) {
        return produtoRepository.findByCategoriaIgnoreCase(categoria);
    }

    // Listar disponíveis
    public List<Produto> listarDisponiveis() {
        return produtoRepository.findByDisponivelTrue();
    }

    // Deletar produto
    public void deletar(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }
}
