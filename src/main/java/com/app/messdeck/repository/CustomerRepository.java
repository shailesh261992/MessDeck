package com.app.messdeck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.messdeck.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
