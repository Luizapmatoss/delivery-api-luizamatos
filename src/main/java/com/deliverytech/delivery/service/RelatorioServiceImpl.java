package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.RelatorioClientesAtivosDTO;
import com.deliverytech.delivery.dto.RelatorioPedidosPeriodoDTO;
import com.deliverytech.delivery.dto.RelatorioProdutosMaisVendidosDTO;
import com.deliverytech.delivery.dto.RelatorioVendasPorRestauranteDTO;
import com.deliverytech.delivery.repository.ClienteRepository;
import com.deliverytech.delivery.repository.PedidoRepository;
import com.deliverytech.delivery.repository.ProdutoRepository;
import com.deliverytech.delivery.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RelatorioServiceImpl implements RelatorioService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public RelatorioVendasPorRestauranteDTO vendasPorRestaurante() {
        // Aqui você pode fazer queries no repository
        return new RelatorioVendasPorRestauranteDTO(null, null);
    }

    @Override
    public RelatorioProdutosMaisVendidosDTO produtosMaisVendidos() {
        return new RelatorioProdutosMaisVendidosDTO(null, null);
    }

    @Override
    public RelatorioClientesAtivosDTO clientesAtivos() {
        return new RelatorioClientesAtivosDTO(null, null);
    }

    @Override
    public RelatorioPedidosPeriodoDTO pedidosPorPeriodo(LocalDate inicio, LocalDate fim) {
        return new RelatorioPedidosPeriodoDTO(fim, fim, null);
    }
}
