package com.example.PharmacyManagement.data.repository;


import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PharmacyManagement.data.entity.EnderecoEntity;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long>{

    public List<EnderecoEntity> findAllById(@Param("id") Long id);
}
