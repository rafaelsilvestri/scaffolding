package com.mycompany.product.service;

import com.mycompany.product.dao.ProductDAO;
import com.mycompany.product.model.Product;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Rafael Cechinel Silvestri
 */
//@Service
@Path("/v1/products")
@Produces(MediaType.APPLICATION_JSON) //utf-8
public class ProductResourceV1 {

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
    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Product post(Product product) {
        product = productDAO.save(product);
        return productDAO.findOne(product.getId());
    }

    /**
     * update
     *
     * @param product
     * @return
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Product put(Product product) {
        product = productDAO.save(product);
        return productDAO.findOne(product.getId());
    }

    /**
     * delete
     *
     * @param name
     * @param id
     * @return
     */
   /* @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        productDAO.delete(id);
        return Response.ok().build();
    }*/
    @Transactional
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        productDAO.delete(id);
        return Response.ok().build();
    }
    
    @GET
    @Path("/{id}")
    public Product findbyId(@PathParam("id") Long id) {
        return productDAO.findOne(id);
    }

}
