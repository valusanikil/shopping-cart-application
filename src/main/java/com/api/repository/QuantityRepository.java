package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.model.Quantity;

public interface QuantityRepository extends JpaRepository<Quantity, Long> {

}
