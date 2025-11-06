package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByNomeContainingIgnoreCase(String nome);
    List<Restaurante> findByCategoriaIgnoreCase(String categoria);
    List<Restaurante> findByAtivoTrue();
    List<Restaurante> findByTaxaEntregaLessThanEqual(BigDecimal taxa);
    List<Restaurante> findTop5ByOrderByNomeAsc();

    @Query("SELECT r FROM Restaurante r WHERE r.categoria = :categoria AND r.ativo = true ORDER BY r.avaliacao DESC")
    List<Restaurante> buscarAtivosPorCategoriaOrdenadosPorAvaliacao(String categoria);
    List<Restaurante> findByCategoriaAndAtivoTrue(String categoria);
}
