package com.sagar.springSecurityExample.repo;

import com.sagar.springSecurityExample.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {

    // custom method
    Users findByUsername(String username);
}
