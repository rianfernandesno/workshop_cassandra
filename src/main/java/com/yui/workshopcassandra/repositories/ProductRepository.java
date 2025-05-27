package com.yui.workshopcassandra.repositories;

import com.yui.workshopcassandra.model.entities.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ProductRepository extends CassandraRepository<Product, UUID> {
}
