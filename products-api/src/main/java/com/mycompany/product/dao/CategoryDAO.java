package com.mycompany.product.dao;

import com.mycompany.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Guilherme de Souza
 */
@Repository
public interface CategoryDAO extends JpaRepository<Category, Long> {

}
