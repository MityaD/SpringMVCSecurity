package com.yakut.spring.repository;

import com.yakut.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByFirstName(String s);
}
