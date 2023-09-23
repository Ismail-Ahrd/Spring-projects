package com.example.test.repository;

import com.example.test.entity.AppRole;
import com.example.test.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);
}
