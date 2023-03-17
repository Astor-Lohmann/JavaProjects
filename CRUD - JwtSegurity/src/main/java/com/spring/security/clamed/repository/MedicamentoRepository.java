package com.spring.security.clamed.repository;

import com.spring.security.clamed.model.Medicamento;
import com.spring.security.clamed.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    @Query("select u from Medicamento u where u.nome like %?1%")
    List<Medicamento> findMedByName(String nome);

    public List<Medicamento> findAllById(@Param("id") Long id);
}
