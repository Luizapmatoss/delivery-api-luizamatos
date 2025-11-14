package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.RestauranteDTO;
import com.deliverytech.delivery.models.Restaurante;
import com.deliverytech.delivery.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        // Exemplo: taxa simulada conforme prefixo do CEP
        int regiao = Integer.parseInt(cep.substring(0, 2));
        return switch (regiao) {
            case 30 -> 5.0;  // Belo Horizonte
            case 40 -> 8.0;  // Salvador
            default -> 10.0; // Outras regiões
        };
    }

    // Novo método: alternar status (ativa/desativa)
    @Override
    public Restaurante alterarStatus(Long id) {
        Restaurante restaurante = buscarRestaurantePorId(id);
        restaurante.setAtivo(!restaurante.getAtivo()); // inverte o status atual
        return restauranteRepository.save(restaurante);
    }

    // Novo método: listar com filtros
    @Override
    public List<Restaurante> listarComFiltros(String categoria, Boolean ativo) {
        List<Restaurante> todos = restauranteRepository.findAll();

        return todos.stream()
                .filter(r -> categoria == null || r.getCategoria().equalsIgnoreCase(categoria))
                .filter(r -> ativo == null || r.getAtivo().equals(ativo))
                .collect(Collectors.toList());
    }

    // Novo método: buscar restaurantes próximos (simulação)
    @Override
    public List<Restaurante> buscarRestaurantesProximos(String cep) {
        // Exemplo básico: retorna todos os ativos se o CEP começar com "30"
        if (cep.startsWith("30")) {
            return restauranteRepository.findByAtivoTrue();
        }
        // senão retorna só os com avaliação alta
        return restauranteRepository.findByAtivoTrue()
                .stream()
                .filter(r -> r.getAvaliacao() >= 4.0)
                .collect(Collectors.toList());
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

    @Override
    public List<Restaurante> buscarRestaurantesDisponiveis() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarRestaurantesDisponiveis'");
    }

    @Override
    public List<Restaurante> listarProximos(String cep) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarProximos'");
    }
}
