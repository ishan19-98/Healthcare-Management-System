package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.entity.Bill;

@Repository
public interface BillRepository extends MongoRepository<Bill, String>{

}
