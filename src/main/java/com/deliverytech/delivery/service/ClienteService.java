package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ClienteDTO;
import com.deliverytech.delivery.models.Cliente;
import java.util.List;

public interface ClienteService {

    // Cadastra um novo cliente com validação de e-mail único
    Cliente cadastrarCliente(ClienteDTO dto);

    // Busca cliente por ID com exceção se não existir
    Cliente buscarClientePorId(Long id);

    // Busca cliente por e-mail (para login/autenticação)
    Cliente buscarClientePorEmail(String email);

    // Atualiza dados de cliente com validação de existência e e-mail
    Cliente atualizarCliente(Long id, ClienteDTO dto);

    // Ativa ou desativa o cliente (toggle)
    Cliente ativarDesativarCliente(Long id);

    // Lista apenas clientes ativos
    List<Cliente> listarClientesAtivos();

    // Lista todos os clientes (independente do status)
    List<Cliente> listarTodos();
}
