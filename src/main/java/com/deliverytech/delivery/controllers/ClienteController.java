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
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@Valid @RequestBody ClienteDTO dto) {
        Cliente cliente = clienteService.cadastrarCliente(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(toResponse(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(toResponse(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientesAtivos() {
        List<ClienteResponseDTO> clientes = clienteService.listarClientesAtivos()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
        Cliente atualizado = clienteService.atualizarCliente(id, dto);
        return ResponseEntity.ok(toResponse(atualizado));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ClienteResponseDTO> ativarDesativarCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.ativarDesativarCliente(id);
        return ResponseEntity.ok(toResponse(cliente));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteResponseDTO> buscarPorEmail(@PathVariable String email) {
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
