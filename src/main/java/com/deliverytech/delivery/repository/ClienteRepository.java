package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.models.Cliente;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);
    List<Cliente> findByAtivoTrue();
    List<Cliente> findByAtivo(Boolean ativo);
    List<Cliente> findByNomeContainingIgnoreCase(String nomeParte);

    boolean existsByEmail(String email);
}
