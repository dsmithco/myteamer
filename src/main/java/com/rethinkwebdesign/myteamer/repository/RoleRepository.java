package com.rethinkwebdesign.myteamer.repository;

import com.rethinkwebdesign.myteamer.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}