package com.tek.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tek.models.Register;

@Repository
public interface UserRepository extends JpaRepository<Register, Long> {
	Register findByUserName(String username);
}
