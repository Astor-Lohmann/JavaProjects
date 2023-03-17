package com.spring.security.clamed.repository;



import com.spring.security.clamed.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

    public List<Endereco> findAllById(@Param("id") Long id);
}
