package com.mycompany.product.service;

import com.mycompany.product.dao.ProductDAO;
import com.mycompany.product.model.Product;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Rafael Cechinel Silvestri
 */
@Path("/v1/products")
@Produces(MediaType.APPLICATION_JSON) //utf-8
public class ProductResourceV1 {

    @Inject
    HelloSpeaker helloService;
    @Inject
    ProductDAO productDAO;

    /**
     * Retrieves all products;
     *
     * @return list of all products
     */
    @GET
    public List<Product> getAll() {

        return (List<Product>) productDAO.findAll();
    }

    /**
     * Create a new product resource.
     *
     * @param product
     * @return response ok.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Product product) {
        productDAO.save(product);
        return Response.ok().build();
    }

    /**
     * update
     *
     * @param product
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(Product product) {
        productDAO.save(product);
        return Response.ok().build();
    }

    /**
     * delete
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        productDAO.delete(id);
        return Response.ok().build();
    }

}
