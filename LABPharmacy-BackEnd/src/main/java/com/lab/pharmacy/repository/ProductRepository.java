package com.lab.pharmacy.repository;

import com.lab.pharmacy.model.Address;
import com.lab.pharmacy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
