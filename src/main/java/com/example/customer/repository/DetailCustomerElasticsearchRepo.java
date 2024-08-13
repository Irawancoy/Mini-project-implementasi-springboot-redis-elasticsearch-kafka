package com.example.customer.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.example.customer.model.DetailCustomerElasticsearchModel;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailCustomerElasticsearchRepo extends ElasticsearchRepository<DetailCustomerElasticsearchModel, String> {
}



