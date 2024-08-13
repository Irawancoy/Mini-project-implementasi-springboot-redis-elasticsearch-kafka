package com.example.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.dto.response.ResponseBody;
import com.example.customer.service.DetailCustomerService;
import com.example.customer.dto.request.TambahCustomer;

@RestController // Anotasi untuk mendeklarasikan kelas ini sebagai controller yang akan menangani permintaan HTTP
@RequestMapping("/api/v1") // Base URL untuk semua endpoint dalam controller ini
public class DetailCustomerController {

   @Autowired // Injeksi dependensi untuk DetailCustomerService
   private DetailCustomerService detailCustomerService;

   // Endpoint untuk menambahkan data customer ke database dan Redis
   @PostMapping("/detail-customer")
   public ResponseEntity<ResponseBody> postDetailCustomer(TambahCustomer tambahCustomer) {
      // Mengarahkan permintaan ke service yang terkait
      return detailCustomerService.postDetailCustomer(tambahCustomer);
   }

   // Endpoint untuk mengambil data customer dari database PostgreSQL
   @GetMapping("/detail-customer-pg")
   public ResponseEntity<ResponseBody> getDetailCustomerPG() {
      // Mengarahkan permintaan ke service yang terkait
      return detailCustomerService.getDetailCustomerPG();
   }

   // Endpoint untuk mengambil data customer dari Redis
   @GetMapping("/detail-customer-redis")
   public ResponseEntity<ResponseBody> getDetailCustomerRedis() {
      // Mengarahkan permintaan ke service yang terkait
      return detailCustomerService.getDetailCustomerRedis();
   }

   //Endpoint untuk menambahkan data customer ke Elasticsearc
   @PostMapping("detail-customer-es")
   public ResponseEntity<ResponseBody> postDetailCustomerES(TambahCustomer tambahCustomer) {
      // Mengarahkan permintaan ke service yang terkait
      return detailCustomerService.postDetailCustomerElasticsearch(tambahCustomer);

   }

   //Endpoint untuk mengambil data customer dari Elasticsearch
   @GetMapping("detail-customer-es")
   public ResponseEntity<ResponseBody> getDetailCustomerES() {
      // Mengarahkan permintaan ke service yang terkait
      return detailCustomerService.getDetailCustomerElasticsearch();
   }



   

}




