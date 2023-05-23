package com.lab.pharmacy.repository;

import com.lab.pharmacy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value="select * from user_tb where email like %?1%", nativeQuery = true)
    User getUserByEmail(String email);
}
