package com.mindtree.shoppingCartApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingCartApplication.entity.Apparal;

@Repository
public interface ApparalRepository extends JpaRepository<Apparal, Integer> {

}
