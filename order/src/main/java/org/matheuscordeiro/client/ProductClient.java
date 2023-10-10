package org.matheuscordeiro.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.matheuscordeiro.dto.ProductDto;

@Path("/products")
@RegisterRestClient
@ApplicationScoped
public interface ProductClient {
    @GET
    @Path("/{id}")
    ProductDto getProductById(@PathParam("id") Long id);
}
