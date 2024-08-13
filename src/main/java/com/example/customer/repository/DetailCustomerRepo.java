package com.example.customer.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.customer.model.DetailCustomerModel;

@Repository
public interface DetailCustomerRepo extends JpaRepository<DetailCustomerModel, Integer> {

   
} 



