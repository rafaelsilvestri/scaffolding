package com.mycompany.product.service;

import com.mycompany.product.dao.ProductDAO;
import com.mycompany.product.model.Product;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Service;

/**
 * REST Web Service
 *
 * @author Rafael Cechinel Silvestri
 */
@Service
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
    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(@Valid Product product) {
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
   /* @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        productDAO.delete(id);
        return Response.ok().build();
    }*/
    @Transactional
    @DELETE
    @Path("/{name}")
    public void delete(@PathParam("name") String name) {
        System.out.println("delete by name: " + name);
        productDAO.deleteByName(name);
    }
    
    @GET
    @Path("/{name}")
    public List<Product> findByName(@PathParam("name") String name) {
        return productDAO.findByName(name);
    }

}
