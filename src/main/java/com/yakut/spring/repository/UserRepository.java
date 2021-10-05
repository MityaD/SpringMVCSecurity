package com.yakut.spring.repository;

import com.yakut.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> getUserBy();//todo че шо за дерьмо?

    User getByFirstName(String s);//todo че шо за дерьмо?
}
