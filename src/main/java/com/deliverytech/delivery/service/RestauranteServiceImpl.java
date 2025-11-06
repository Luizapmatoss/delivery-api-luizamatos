package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.RestauranteDTO;
import com.deliverytech.delivery.models.Restaurante;
import com.deliverytech.delivery.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public Restaurante cadastrarRestaurante(RestauranteDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do restaurante é obrigatório!");
        }
        if (dto.getCategoria() == null || dto.getCategoria().isBlank()) {
            throw new IllegalArgumentException("A categoria é obrigatória!");
        }

        Restaurante restaurante = new Restaurante();
        restaurante.setNome(dto.getNome());
        restaurante.setCategoria(dto.getCategoria());
        restaurante.setAtivo(true);
        restaurante.setAvaliacao(0.0);

        return restauranteRepository.save(restaurante);
    }

    @Override
    public Restaurante buscarRestaurantePorId(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado!"));
    }

    @Override
    public List<Restaurante> buscarRestaurantesPorCategoria(String categoria) {
        return restauranteRepository.findByCategoriaAndAtivoTrue(categoria);
    }

    @Override
    public List<Restaurante> buscarRestaurantesDisponiveis() {
        return restauranteRepository.findByAtivoTrue();
    }

    @Override
    public Restaurante atualizarRestaurante(Long id, RestauranteDTO dto) {
        Restaurante restaurante = buscarRestaurantePorId(id);

        restaurante.setNome(dto.getNome());
        restaurante.setCategoria(dto.getCategoria());
        restaurante.setAvaliacao(dto.getAvaliacao());

        return restauranteRepository.save(restaurante);
    }

    @Override
    public double calcularTaxaEntrega(Long restauranteId, String cep) {
        Restaurante restaurante = buscarRestaurantePorId(restauranteId);

        if (!restaurante.getAtivo()) {
            throw new IllegalArgumentException("Restaurante inativo não pode calcular taxa de entrega!");
        }

        int regiao = Integer.parseInt(cep.substring(0, 2));
        return switch (regiao) {
            case 30 -> 5.0;
            case 40 -> 8.0;
            default -> 10.0;
        };
    }

    @Override
    public Restaurante cadastrar(RestauranteDTO dto) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(dto.getNome());
        restaurante.setCategoria(dto.getCategoria());
        restaurante.setAvaliacao(dto.getAvaliacao() != null ? dto.getAvaliacao() : 0.0);
        restaurante.setAtivo(true);
        return restauranteRepository.save(restaurante);
    }

    @Override
    public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll();
    }

    @Override
    public Restaurante atualizar(Long id, RestauranteDTO dto) {
        Restaurante existente = buscarRestaurantePorId(id);
        existente.setNome(dto.getNome());
        existente.setCategoria(dto.getCategoria());
        existente.setAvaliacao(dto.getAvaliacao());
        return restauranteRepository.save(existente);
    }

    @Override
    public Restaurante alterarStatus(Long id, boolean ativo) {
        Restaurante restaurante = buscarRestaurantePorId(id);
        restaurante.setAtivo(ativo);
        return restauranteRepository.save(restaurante);
    }

    @Override
    public void deletar(Long id) {
        Restaurante restaurante = buscarRestaurantePorId(id);
        restauranteRepository.delete(restaurante);
    }

    @Override
    public List<Restaurante> listarDisponiveis() {
        return restauranteRepository.findByAtivoTrue();
    }
}
