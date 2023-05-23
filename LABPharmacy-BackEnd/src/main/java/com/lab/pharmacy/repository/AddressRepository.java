package com.lab.pharmacy.repository;

import com.lab.pharmacy.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
