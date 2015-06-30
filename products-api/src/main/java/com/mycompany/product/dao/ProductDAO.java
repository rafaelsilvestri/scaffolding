package com.mycompany.product.dao;

import com.mycompany.product.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rafael Cechinel Silvestri
 */
@Repository
public interface ProductDAO extends PagingAndSortingRepository<Product, Long> {
    // basic crud methods
}
