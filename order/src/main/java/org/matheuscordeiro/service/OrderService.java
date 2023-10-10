package org.matheuscordeiro.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.matheuscordeiro.client.CustomerClient;
import org.matheuscordeiro.client.ProductClient;
import org.matheuscordeiro.dto.OrderDto;
import org.matheuscordeiro.entity.Order;
import org.matheuscordeiro.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderService {
    @Inject
    private OrderRepository orderRepository;

    @Inject
    @RestClient
    private CustomerClient customerClient;

    @Inject
    @RestClient
    private ProductClient productClient;

    public List<OrderDto> getAllOrders(){
        final var orderList = new ArrayList<OrderDto>();
        orderRepository.findAll().stream().forEach(item->{
            orderList.add(mapEntityToDTO(item));
        });
        return orderList;
    }

    @Transactional
    public void saveNewOrder(OrderDto orderDto){
        final var customerDto = customerClient.getCustomerById(orderDto.customerId());

        if(customerDto.name().equals(orderDto.customerName())
                && productClient.getProductById(orderDto.productId()) != null){
            orderRepository.persist(mapDTOToEntity(orderDto));
        } else {
            throw new NotFoundException();
        }

    }

    private OrderDto mapEntityToDTO(Order order) {
        return OrderDto.builder().customerId(order.getCustomerId()).customerName(order.getCustomerName())
                .productId(order.getProductId()).orderValue(order.getOrderValue()).build();
    }

    private Order mapDTOToEntity(OrderDto orderDto) {
        final var order = new Order();
        order.setCustomerId(orderDto.customerId());
        order.setCustomerName(orderDto.customerName());
        order.setProductId(orderDto.productId());
        order.setOrderValue(orderDto.orderValue());
        return order;
    }
}
