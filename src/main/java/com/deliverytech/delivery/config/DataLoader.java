package com.deliverytech.delivery.config;

import com.deliverytech.delivery.models.*;
import com.deliverytech.delivery.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final RestauranteRepository restauranteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    public DataLoader(ClienteRepository clienteRepository,
                      RestauranteRepository restauranteRepository,
                      ProdutoRepository produtoRepository,
                      PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.restauranteRepository = restauranteRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {

        System.out.println("\n🚀 Iniciando o carregamento de dados...");

        // ===== CLIENTES =====
        Cliente c1 = new Cliente("Luiza Matos", "luiza@gmail.com", true);
        Cliente c2 = new Cliente("Carlos Silva", "carlos@gmail.com", true);
        Cliente c3 = new Cliente("Marina Costa", "marina@gmail.com", true);
        clienteRepository.saveAll(List.of(c1, c2, c3));

        // ===== RESTAURANTES =====
        Restaurante r1 = new Restaurante("BurgerMax", "Lanches", true, 4.5, new BigDecimal("7.50"));
        Restaurante r2 = new Restaurante("SushiHouse", "Japonesa", true, 4.8, new BigDecimal("10.00"));
        restauranteRepository.saveAll(List.of(r1, r2));

        // ===== PRODUTOS =====
        Produto p1 = new Produto("X-Burger", "Lanche", new BigDecimal("25.00"), true, "Hambúrguer clássico", r1);
        Produto p2 = new Produto("Batata Frita", "Acompanhamento", new BigDecimal("12.00"), true, "Batata crocante", r1);
        Produto p3 = new Produto("Combo Sushi 12 peças", "Sushi", new BigDecimal("45.00"), true, "Variado com sashimi e nigiri", r2);
        Produto p4 = new Produto("Temaki Salmão", "Sushi", new BigDecimal("22.00"), true, "Temaki fresquinho", r2);
        Produto p5 = new Produto("Coca-Cola 350ml", "Bebida", new BigDecimal("6.00"), true, "Refrigerante gelado", r1);
        produtoRepository.saveAll(List.of(p1, p2, p3, p4, p5));

        // ===== PEDIDOS =====

        Pedido ped1 = new Pedido();
        ped1.setCliente(c1);
        ped1.setRestaurante(r1);
        ped1.setValorTotal(new BigDecimal("43.00"));
        ped1.setStatus("ENTREGUE");
        ped1.setDataCriacao(LocalDateTime.now().minusDays(2));

        Pedido ped2 = new Pedido();
        ped2.setCliente(c2);
        ped2.setRestaurante(r2);
        ped2.setValorTotal(new BigDecimal("67.00"));
        ped2.setStatus("EM ANDAMENTO");
        ped2.setDataCriacao(LocalDateTime.now());

pedidoRepository.saveAll(List.of(ped1, ped2));

System.out.println("✅ Dados inseridos com sucesso!");


        pedidoRepository.saveAll(List.of(ped1, ped2));

        System.out.println("✅ Dados inseridos com sucesso!");

        // ====================== CENÁRIOS DE TESTE ======================

        // Cenário 1: Busca de Cliente por Email
        System.out.println("\n🔎 Cenário 1: Busca de Cliente por Email");
        final Cliente clienteEncontrado = clienteRepository.findByEmail("luiza@gmail.com").get();
        System.out.println("Resultado esperado → Cliente encontrado com dados corretos:");
        System.out.println(clienteEncontrado);

        // Cenário 2: Produtos por Restaurante
        System.out.println("\n🍔 Cenário 2: Produtos por Restaurante");
        List<Produto> produtos = produtoRepository.findByRestauranteId(r1.getId());
        System.out.println("Resultado esperado → Lista de produtos do restaurante específico:");
        produtos.forEach(System.out::println);

        // Cenário 3: Pedidos Recentes
        System.out.println("\n📅 Cenário 3: Pedidos Recentes");
        List<Pedido> pedidosRecentes = pedidoRepository.findTop10ByOrderByDataCriacaoDesc();
        System.out.println("Resultado esperado → 10 pedidos mais recentes ordenados por data:");
        pedidosRecentes.forEach(System.out::println);

        // Cenário 4: Restaurantes por Taxa
        System.out.println("\n💰 Cenário 4: Restaurantes com taxa até R$5,00");
        List<Restaurante> restaurantesTaxaBaixa =
                restauranteRepository.findByTaxaEntregaLessThanEqual(new BigDecimal("5.00"));
        System.out.println("Resultado esperado → Restaurantes com taxa até R$5,00:");
        restaurantesTaxaBaixa.forEach(System.out::println);

        System.out.println("\n🎉 Testes finalizados com sucesso!");
    }
}
