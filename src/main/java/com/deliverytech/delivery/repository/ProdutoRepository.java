package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.models.Produto;
import com.deliverytech.delivery.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByRestauranteId(Long restauranteId);

    List<Produto> findByCategoriaIgnoreCase(String categoria);

    List<Produto> findByDisponivelTrue();

    List<Produto> findByRestauranteAndCategoriaIgnoreCase(Restaurante restaurante, String categoria);

    List<Produto> findByPrecoLessThanEqual(BigDecimal preco);

    List<Produto> findByRestaurante(Restaurante restaurante);

    List<Produto> findByNomeContainingIgnoreCase(String nome);

    Optional<Produto> findByIdAndRestauranteId(Long id, Long restauranteId);
}
