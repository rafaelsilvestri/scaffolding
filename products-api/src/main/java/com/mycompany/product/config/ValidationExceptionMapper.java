package com.mycompany.product.config;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Rafael Cechinel Silvestri
 */
@Provider
public class ValidationExceptionMapper extends org.glassfish.jersey.server.validation.internal.ValidationExceptionMapper {

    // filtro padrão do jersey para tratamento do beans validation
    
    @Override
    public Response toResponse(ValidationException e) {
        return super.toResponse(e);
    }
        
}
