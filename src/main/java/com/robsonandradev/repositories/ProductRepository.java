package com.robsonandradev.repositories;

import com.robsonandradev.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
