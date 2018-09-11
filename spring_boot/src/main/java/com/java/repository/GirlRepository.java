package com.java.repository;

import com.java.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GirlRepository extends JpaRepository<Girl, Long> {
}
