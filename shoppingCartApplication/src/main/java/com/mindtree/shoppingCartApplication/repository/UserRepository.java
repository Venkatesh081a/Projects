package com.mindtree.shoppingCartApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingCartApplication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
