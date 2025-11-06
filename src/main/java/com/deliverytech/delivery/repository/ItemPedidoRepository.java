package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.models.ItemPedido;
import com.deliverytech.delivery.models.Produto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    Optional<List<Produto>> findById(Optional<ItemPedido> id);
}
