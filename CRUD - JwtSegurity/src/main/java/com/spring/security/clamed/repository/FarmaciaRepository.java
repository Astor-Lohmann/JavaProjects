package com.spring.security.clamed.repository;



import com.spring.security.clamed.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {

    @Query("select u from Farmacia u where u.nome like %?1%")
    List<Farmacia> findFarByName(String nome);


    public List<Farmacia> findAllById(@Param("id") Long id);
}
