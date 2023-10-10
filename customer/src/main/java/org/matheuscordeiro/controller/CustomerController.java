package org.matheuscordeiro.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.matheuscordeiro.dto.CustomerDto;
import org.matheuscordeiro.service.CustomerService;

import java.util.List;

@Path("/api/customers")
public class CustomerController {
    @Inject
    CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDto> findAllCustomers(){
        return customerService.findAllCustomers();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDto findCustomerById(@PathParam("id") Long id){
        return customerService.findCustomerById(id);
    }

    @POST
    public Response saveCustomer(CustomerDto customerDTO){
        try {
            customerService.createNewCustomer(customerDTO);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return  Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response changeCustomer(@PathParam("id") Long id, CustomerDto customerDTO){
        try {
            customerService.changeCustomer(id,customerDTO);
            return Response.accepted().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") Long id){
        try {
            customerService.deleteCustomer(id);
            return Response.accepted().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
