package com.mycompany.product.dao;

import com.mycompany.product.model.Product;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rafael Cechinel Silvestri
 */
@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {
    // basic crud methods
    
    public List<Product> findByName(String name);
    
    //@Transactional use Transactional on Service level
    public void deleteByName(String name);
}
