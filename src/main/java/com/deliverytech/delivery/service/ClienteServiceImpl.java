package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ClienteDTO;
import com.deliverytech.delivery.models.Cliente;
import com.deliverytech.delivery.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Cadastrar cliente (com DTO)
    @Override
    public Cliente cadastrarCliente(ClienteDTO dto) {
        // Valida email único
        if (clienteRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado: " + dto.getEmail());
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setAtivo(true);

        return clienteRepository.save(cliente);
    }

    // Buscar cliente por ID
    @Override
    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));
    }

    // Buscar cliente por email (para login)
    @Override
    public Cliente buscarClientePorEmail(String email) {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com email: " + email));
    }

    // Atualizar cliente
    @Override
    public Cliente atualizarCliente(Long id, ClienteDTO dto) {
        Cliente cliente = buscarClientePorId(id);

        // Verifica se o email novo já pertence a outro cliente
        clienteRepository.findByEmail(dto.getEmail())
                .filter(c -> !c.getId().equals(id))
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Email já está em uso por outro cliente");
                });

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());

        return clienteRepository.save(cliente);
    }

    // Ativar / Desativar cliente
    @Override
    public Cliente ativarDesativarCliente(Long id) {
        Cliente cliente = buscarClientePorId(id);
        cliente.setAtivo(!cliente.getAtivo());
        return clienteRepository.save(cliente);
    }

    // Listar apenas clientes ativos
    @Override
    public List<Cliente> listarClientesAtivos() {
        return clienteRepository.findByAtivoTrue();
    }

    // Listar todos os clientes
    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // (Métodos antigos - não necessários)
    public Cliente cadastrarCliente(Cliente cliente) {
        throw new UnsupportedOperationException("Use cadastrarCliente(ClienteDTO dto)");
    }

    public Cliente atualizarCliente(Long id, Cliente cliente) {
        throw new UnsupportedOperationException("Use atualizarCliente(Long id, ClienteDTO dto)");
    }
}
