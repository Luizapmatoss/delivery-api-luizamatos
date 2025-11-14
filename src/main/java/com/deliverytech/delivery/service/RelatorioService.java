package com.deliverytech.delivery.service;

import java.time.LocalDate;
import com.deliverytech.delivery.dto.RelatorioClientesAtivosDTO;
import com.deliverytech.delivery.dto.RelatorioPedidosPeriodoDTO;
import com.deliverytech.delivery.dto.RelatorioProdutosMaisVendidosDTO;
import com.deliverytech.delivery.dto.RelatorioVendasPorRestauranteDTO;

public interface RelatorioService {

    RelatorioVendasPorRestauranteDTO vendasPorRestaurante();

    RelatorioProdutosMaisVendidosDTO produtosMaisVendidos();

    RelatorioClientesAtivosDTO clientesAtivos();

    RelatorioPedidosPeriodoDTO pedidosPorPeriodo(LocalDate inicio, LocalDate fim);
}
