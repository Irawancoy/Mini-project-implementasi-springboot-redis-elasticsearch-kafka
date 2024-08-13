package com.example.customer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "detail_customer") //Nama index pada Elasticsearch
public class DetailCustomerElasticsearchModel {

   @Id
   private String id;

   private String nama;
   private String email;
   private String alamat;
   private String telepon;

}







