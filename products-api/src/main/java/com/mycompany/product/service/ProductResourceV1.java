package com.mycompany.product.service;

import com.mycompany.product.dao.ProductDAO;
import com.mycompany.product.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

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
        List<Product> products = new ArrayList<>();
        products.add(new Product(4L, "Pen " + helloService.sayHello()));
        products.add(new Product(5L, "Computer"));
        products.add(new Product(6L, "Phone"));
        
        productDAO.save(products);

        return (List<Product>) productDAO.findAll();
    }
}
