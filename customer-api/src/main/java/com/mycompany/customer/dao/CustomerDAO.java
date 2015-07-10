package com.mycompany.customer.dao;

import com.mycompany.customer.model.Customer;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Rafael Cechinel Silvestri
 */
public interface CustomerDAO extends JpaRepository<Customer, UUID> {

}
