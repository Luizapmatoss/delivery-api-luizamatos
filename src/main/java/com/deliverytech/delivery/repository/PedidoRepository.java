package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByClienteId(Long clienteId);
    List<Pedido> findByStatusIgnoreCase(String status);
    List<Pedido> findByDataCriacaoBetween(LocalDateTime inicio, LocalDateTime fim);
    @Query("SELECT p.cliente.id, COUNT(p) FROM Pedido p WHERE p.status = 'ENTREGUE' GROUP BY p.cliente.id")
    List<Object[]> relatorioPedidosEntreguesPorCliente();
}
