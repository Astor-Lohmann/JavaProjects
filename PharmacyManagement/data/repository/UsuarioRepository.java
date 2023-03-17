package com.example.PharmacyManagement.data.repository;

import com.example.PharmacyManagement.data.entity.UsuarioEntity;
import feign.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long {

    public List<UsuarioEntity> findAllById(@Param("id") Long id);
}
