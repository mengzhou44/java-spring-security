package com.mengzhou.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mengzhou.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
