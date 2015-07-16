package com.mycompany.product.service;

import com.mycompany.product.dao.CategoryDAO;
import com.mycompany.product.model.Category;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Guilherme de Souza
 */
@Path("/v1/categories")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResourceV1 {
    
    @Inject
    CategoryDAO categoryDAO;
    
    @GET
    public List<Category> getAll(){
        return (List<Category>) categoryDAO.findAll();
    }
}
