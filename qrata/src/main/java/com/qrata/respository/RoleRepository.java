package com.qrata.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qrata.models.Role;

public interface RoleRepository extends JpaRepository<Role,Short>{

	 public abstract Role findByName(String name);
}
