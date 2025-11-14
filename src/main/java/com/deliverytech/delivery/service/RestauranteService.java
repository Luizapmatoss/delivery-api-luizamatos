package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.RestauranteDTO;
import com.deliverytech.delivery.models.Restaurante;
import jakarta.validation.Valid;
import java.util.List;

public interface RestauranteService {

    // Cadastrar novo restaurante
    Restaurante cadastrarRestaurante(@Valid RestauranteDTO dto);

    // Buscar por ID
    Restaurante buscarRestaurantePorId(Long id);

    // Buscar por categoria
    List<Restaurante> buscarRestaurantesPorCategoria(String categoria);

    // Atualizar restaurante
    Restaurante atualizarRestaurante(Long id, @Valid RestauranteDTO dto);

    // Calcular taxa de entrega com base no CEP
    double calcularTaxaEntrega(Long restauranteId, String cep);

    // Alterar status (ativar/desativar)
    Restaurante alterarStatus(Long id);

    // Listar com filtros opcionais (categoria, ativo)
    List<Restaurante> listarComFiltros(String categoria, Boolean ativo);

    // Buscar restaurantes próximos de um CEP
    List<Restaurante> buscarRestaurantesProximos(String cep);
    List<Restaurante> listarProximos(String cep);

    // (Opcional) Deletar restaurante
    void deletar(Long id);

	List<Restaurante> buscarRestaurantesDisponiveis();

    List<Restaurante> listarDisponiveis();
}
