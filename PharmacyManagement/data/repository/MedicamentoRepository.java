package com.example.PharmacyManagement.data.repository;

import com.example.PharmacyManagement.data.entity.MedicamentoEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<MedicamentoEntity, Long> {

    public List<MedicamentoEntity> findAllById(@Param("id") Long id);
}
