package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.models.*;
import com.deliverytech.delivery.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Buscar pedidos por cliente
    List<Pedido> findByClienteId(Long clienteId);

    // Total de vendas por restaurante
    @Query("SELECT p.restaurante.nome AS restaurante, SUM(p.valorTotal) AS totalVendas " +
           "FROM Pedido p " +
           "GROUP BY p.restaurante.nome " +
           "ORDER BY totalVendas DESC")
    List<TotalVendasPorRestauranteDTO> calcularTotalVendasPorRestaurante();

    // Pedidos com valor acima de X
    @Query("SELECT p FROM Pedido p WHERE p.valorTotal > :valorMinimo")
    List<Pedido> buscarPedidosComValorMaiorQue(@Param("valorMinimo") BigDecimal valorMinimo);

    // Relatório por período e status
    @Query("SELECT p FROM Pedido p " +
           "WHERE p.dataCriacao BETWEEN :inicio AND :fim " +
           "AND (:status IS NULL OR p.status = :status)")
    List<Pedido> buscarPorPeriodoEStatus(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim,
            @Param("status") String status
    );

    // Produtos mais vendidos (consulta nativa)
    @Query(value = """
        SELECT pr.nome AS produto, COUNT(pp.pedido_id) AS quantidadeVendida
        FROM pedido_produto pp
        JOIN produto pr ON pr.id = pp.produto_id
        GROUP BY pr.nome
        ORDER BY quantidadeVendida DESC
        LIMIT 5
        """, nativeQuery = true)
    List<ProdutoMaisVendidoDTO> listarProdutosMaisVendidos();

    // Ranking de clientes por número de pedidos (consulta nativa)
    @Query(value = """
        SELECT c.nome AS cliente, COUNT(p.id) AS totalPedidos
        FROM pedido p
        JOIN cliente c ON c.id = p.cliente_id
        GROUP BY c.nome
        ORDER BY totalPedidos DESC
        """, nativeQuery = true)
    List<RankingClienteDTO> rankingClientes();

    // Faturamento por categoria (consulta nativa)
    @Query(value = """
        SELECT r.categoria AS categoria, SUM(p.valor_total) AS faturamento
        FROM pedido p
        JOIN restaurante r ON r.id = p.restaurante_id
        GROUP BY r.categoria
        ORDER BY faturamento DESC
        """, nativeQuery = true)
    List<FaturamentoPorCategoriaDTO> faturamentoPorCategoria();

    // Relatório de pedidos entregues por cliente (JPQL)
    @Query("SELECT p.cliente.id, COUNT(p) FROM Pedido p WHERE p.status = 'ENTREGUE' GROUP BY p.cliente.id")
    List<Object[]> relatorioPedidosEntreguesPorCliente();

    List<Pedido> findTop10ByOrderByDataCriacaoDesc();
}
