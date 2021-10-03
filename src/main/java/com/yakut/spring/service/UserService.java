package com.yakut.spring.service;

import com.yakut.spring.exception.NoUserTableException;
import com.yakut.spring.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getUserBy();

    User save(User user);

    void deleteUserById(Long id);

    List<User> findAllUser();

    User findUserById(Long id) throws NoUserTableException;

    void deleteAll();
}
