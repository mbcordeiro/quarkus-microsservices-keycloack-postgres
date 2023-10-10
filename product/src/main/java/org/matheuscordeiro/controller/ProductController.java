package org.matheuscordeiro.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.matheuscordeiro.dto.ProductDto;
import org.matheuscordeiro.service.ProductService;

import java.util.List;

@Path("/api/products")
public class ProductController {
    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDto> findAllProducts(){
        return productService.getAllProducts();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDto findProductById(@PathParam("id") Long id){
        return productService.getProductById(id);
    }


    @POST
    @Transactional
    public Response saveProduct(ProductDto product){
        try {
            productService.createNewProduct(product);
            return Response.ok().build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response changeProduct(@PathParam("id") Long id, ProductDto product){
        try {
            productService.changeProduct(id, product);
            return Response.ok().build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response removeProduct(@PathParam("id") Long id){
        try {
            productService.deleteProduct(id);
            return Response.ok().build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
