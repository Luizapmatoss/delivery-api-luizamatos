package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.dto.ClienteDTO;
import com.deliverytech.delivery.dto.response.ClienteResponseDTO;
import com.deliverytech.delivery.models.Cliente;
import com.deliverytech.delivery.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Gerenciamento de clientes do sistema")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Operation(
            summary = "Cadastrar um novo cliente",
            description = "Cria um novo cliente no sistema com nome, e-mail e status ativo.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso",
                            content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados"),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
            }
    )
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(
            @Valid @RequestBody
            @Parameter(description = "Dados do cliente a ser criado")
            ClienteDTO dto) {

        Cliente cliente = clienteService.cadastrarCliente(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(toResponse(cliente));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar cliente por ID",
            description = "Retorna as informações de um cliente específico.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                            content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
            }
    )
    public ResponseEntity<ClienteResponseDTO> buscarPorId(
            @Parameter(description = "ID do cliente", example = "1")
            @PathVariable Long id) {

        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(toResponse(cliente));
    }

    @GetMapping
    @Operation(
            summary = "Listar clientes ativos",
            description = "Retorna todos os clientes cadastrados com status ativo = true.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
            }
    )
    public ResponseEntity<List<ClienteResponseDTO>> listarClientesAtivos() {

        List<ClienteResponseDTO> clientes = clienteService.listarClientesAtivos()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar um cliente",
            description = "Atualiza nome, e-mail ou outros dados de um cliente específico.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
            }
    )
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(
            @Parameter(description = "ID do cliente a ser atualizado")
            @PathVariable Long id,

            @Parameter(description = "Novos dados do cliente")
            @Valid @RequestBody ClienteDTO dto) {

        Cliente atualizado = clienteService.atualizarCliente(id, dto);
        return ResponseEntity.ok(toResponse(atualizado));
    }

    @PatchMapping("/{id}/status")
    @Operation(
            summary = "Ativar/Desativar cliente",
            description = "Altera o status de ativo/inativo do cliente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Status atualizado"),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
            }
    )
    public ResponseEntity<ClienteResponseDTO> ativarDesativarCliente(
            @Parameter(description = "ID do cliente para alterar status")
            @PathVariable Long id) {

        Cliente cliente = clienteService.ativarDesativarCliente(id);
        return ResponseEntity.ok(toResponse(cliente));
    }

    @GetMapping("/email/{email}")
    @Operation(
            summary = "Buscar cliente por e-mail",
            description = "Busca um cliente pelo e-mail cadastrado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
            }
    )
    public ResponseEntity<ClienteResponseDTO> buscarPorEmail(
            @Parameter(description = "E-mail do cliente", example = "cliente@email.com")
            @PathVariable String email) {

        Cliente cliente = clienteService.buscarClientePorEmail(email);
        return ResponseEntity.ok(toResponse(cliente));
    }

    // Conversão Entity -> ResponseDTO
    private ClienteResponseDTO toResponse(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getAtivo()
        );
    }
}
