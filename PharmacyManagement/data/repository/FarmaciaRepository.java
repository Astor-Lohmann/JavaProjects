package com.example.PharmacyManagement.data.repository;


import com.example.PharmacyManagement.data.entity.FarmaciaEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmaciaRepository extends JpaRepository<FarmaciaEntity, Long> {


    public List<FarmaciaEntity> findAllById(@Param("id") Long id);
}
