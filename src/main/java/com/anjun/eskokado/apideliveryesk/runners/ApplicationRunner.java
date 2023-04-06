package com.anjun.eskokado.apideliveryesk.runners;

import com.anjun.eskokado.apideliveryesk.domain.models.*;
import com.anjun.eskokado.apideliveryesk.domain.repositories.*;
import io.quarkus.runtime.Startup;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.Arrays;

@Singleton
@Startup
public class ApplicationRunner {
    private ProductRepository produtoRepository;
    private UserTransaction transaction;
    private AddressRepository addressRepository;
    private SupplierRepository supplierRepository;
    private ClientRepository clientRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;

    @Inject
    public ApplicationRunner(
            ProductRepository produtoRepository,
            AddressRepository addressRepository,
            SupplierRepository supplierRepository,
            ClientRepository clientRepository,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            UserTransaction transaction
    ) {
        this.produtoRepository = produtoRepository;
        this.addressRepository = addressRepository;
        this.supplierRepository = supplierRepository;
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.transaction = transaction;
    }

    @PostConstruct
    @Transactional(Transactional.TxType.REQUIRED)
    public void init() {
        try {
            transaction.begin();

            Product p1 = new Product("Notebook", 1500.00);
            Product p2 = new Product("Impressora", 800.00);
            Product p3 = new Product("Mouse", 80.00);

            Client cli1 = new Client("Maria Silva", "maria@gmail.com");
            Client cli2 = new Client("Paulo Silva", "paulo@gmail.com");
            Supplier sup = new Supplier("João Souza", "joao@gmail.com");
            Address add1 = new Address( "300", "Apto 203", "38220834", cli1) ;
            Address add2 = new Address( "600", "Apto 603", "38220834", cli2) ;

            Order order1 = new Order(add1, sup);
            Order order2 = new Order(add2, sup);

            OrderItem oi1 = new OrderItem(order1, p1, 0.0, 1, 2000.00);
            OrderItem oi2 = new OrderItem(order1, p3, 0.0, 1, 2000.00);
            OrderItem oi3 = new OrderItem(order1, p2, 100.00, 1, 800.00);

            produtoRepository.persist(Arrays.asList(p1, p2, p3));
            addressRepository.persist(Arrays.asList(add1, add2));
            clientRepository.persist(Arrays.asList(cli1, cli2));
            supplierRepository.persist(sup);
            orderRepository.persist(Arrays.asList(order1, order2));
            orderItemRepository.persist(Arrays.asList(oi1, oi2, oi3));

            transaction.commit();
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {
                throw new RuntimeException("Erro ao reverter a transação.", ex);
            }
            throw new RuntimeException("Erro ao executar a transação.", e);
        }
    }
}