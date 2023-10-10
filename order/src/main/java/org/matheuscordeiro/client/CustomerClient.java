package org.matheuscordeiro.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.matheuscordeiro.dto.CustomerDto;

@Path("/customers")
@RegisterRestClient
@ApplicationScoped
public interface CustomerClient {
    @GET
    @Path("/{id}")
    CustomerDto getCustomerById(@PathParam("id") Long id);
}
