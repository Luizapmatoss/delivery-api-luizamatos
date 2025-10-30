package com.deliverytech.delivery.service;

import com.deliverytech.delivery.models.Restaurante;
import com.deliverytech.delivery.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    // Cadastrar restaurante
    public Restaurante cadastrar(Restaurante restaurante) {
        restaurante.setAtivo(true);
        restaurante.setAvaliacao(0.0);
        return restauranteRepository.save(restaurante);
    }

    // Listar todos os restaurantes
    public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll();
    }

    // Buscar por ID
    public Restaurante buscarPorId(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado!"));
    }

    // Atualizar restaurante
    public Restaurante atualizar(Long id, Restaurante atualizado) {
        Restaurante restaurante = buscarPorId(id);
        restaurante.setNome(atualizado.getNome());
        restaurante.setCategoria(atualizado.getCategoria());
        return restauranteRepository.save(restaurante);
    }

    // Ativar/Inativar restaurante
    public void alterarStatus(Long id, boolean ativo) {
        Restaurante restaurante = buscarPorId(id);
        restaurante.setAtivo(ativo);
        restauranteRepository.save(restaurante);
    }
}
