package org.matheuscordeiro.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.matheuscordeiro.dto.OrderDto;
import org.matheuscordeiro.service.OrderService;

import java.util.List;

@Path("/api/orders")
public class OrderController {
    @Inject
    private OrderService orderService;

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    @POST
    public Response saveNewOrder(OrderDto order){
        try {
            orderService.saveNewOrder(order);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return  Response.serverError().build();
        }
    }

}
