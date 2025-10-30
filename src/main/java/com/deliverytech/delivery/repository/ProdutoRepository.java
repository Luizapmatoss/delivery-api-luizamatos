package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.models.Produto;
import com.deliverytech.delivery.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByRestaurante(Restaurante restaurante);
    List<Produto> findByCategoriaIgnoreCase(String categoria);
    List<Produto> findByDisponivelTrue();
    List<Produto> findByRestauranteAndCategoriaIgnoreCase(Restaurante restaurante, String categoria);
}
