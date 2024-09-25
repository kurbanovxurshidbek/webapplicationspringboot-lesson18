package com.example.springsecuritydemo3.repository;

import com.example.springsecuritydemo3.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
