package com.mycompany.customer.service;

import com.mycompany.customer.dao.CustomerDAO;
import com.mycompany.customer.model.Customer;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Rafael Cechinel Silvestri
 */
//@Service
@Transactional(Transactional.TxType.NOT_SUPPORTED)
@Path("/v1/customers")
@Produces(MediaType.APPLICATION_JSON) //utf-8
public class CustomerResourceV1 {

    @Inject
    CustomerDAO customerDAO;

    @GET
    public List<Customer> getAll() {
        return (List<Customer>) customerDAO.findAll();
    }
    
    @GET
    @Path("/{id}")
    public Customer findbyId(@PathParam("id") UUID id) {
        return customerDAO.findOne(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    //@Transactional
    public Response create(@Valid Customer customer) {
        customerDAO.save(customer);
        return Response.ok().build();
    }

    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    //@Transactional
    public Response update(@Valid Customer customer) {
        customerDAO.save(customer);
        return Response.ok().build();
    }
    
    
    @DELETE
    @Path("/{id}")
    //@Transactional
    public void delete(@PathParam("id") UUID id) {
        customerDAO.delete(id);
    }
    

}
