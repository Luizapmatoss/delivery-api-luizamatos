package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.RestauranteDTO;
import com.deliverytech.delivery.models.Restaurante;
import jakarta.validation.Valid;
import java.util.List;

public interface RestauranteService {

    Restaurante cadastrarRestaurante(@Valid RestauranteDTO dto);

    Restaurante buscarRestaurantePorId(Long id);

    List<Restaurante> buscarRestaurantesPorCategoria(String categoria);

    List<Restaurante> buscarRestaurantesDisponiveis();

    Restaurante atualizarRestaurante(Long id, @Valid RestauranteDTO dto);

    double calcularTaxaEntrega(Long restauranteId, String cep);

    Restaurante cadastrar(@Valid RestauranteDTO dto);

    List<Restaurante> listarTodos();

    Restaurante atualizar(Long id, @Valid RestauranteDTO dto);

    Restaurante alterarStatus(Long id, boolean ativo);

    void deletar(Long id);

    List<Restaurante> listarDisponiveis();
}
