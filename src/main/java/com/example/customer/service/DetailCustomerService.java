package com.example.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.customer.repository.DetailCustomerElasticsearchRepo;
import com.example.customer.repository.DetailCustomerRepo;
import com.example.customer.dto.response.ResponseBody;
import com.example.customer.model.DetailCustomerModel;
import com.example.customer.dto.request.TambahCustomer;
import com.example.customer.model.DetailCustomerElasticsearchModel;

@Service // Menandakan bahwa kelas ini adalah service yang dikelola oleh Spring
public class DetailCustomerService {

   @Autowired
   private DetailCustomerRepo detailCustomerRepo; // Injeksi repository untuk akses ke database
   @Autowired
   RedisTemplate<String, Object> redisTemplate; // Injeksi RedisTemplate untuk operasi Redis

   @Autowired
   private KafkaProducerService kafkaProducerService; // Injeksi service untuk mengirim pesan ke Kafka

   @Autowired
   private DetailCustomerElasticsearchRepo detailCustomerElasticsearchRepo; // Injeksi repository untuk akses ke Elasticsearch

   // Metode untuk menambahkan data ke database , Redis, dan Kafka
   public ResponseEntity<ResponseBody> postDetailCustomer(TambahCustomer tambahCustomer) {
      try {
         // Buat objek DetailCustomerModel dari request
         DetailCustomerModel detailCustomerModel = new DetailCustomerModel();
         detailCustomerModel.setNama(tambahCustomer.getNama());
         detailCustomerModel.setEmail(tambahCustomer.getEmail());
         detailCustomerModel.setAlamat(tambahCustomer.getAlamat());
         detailCustomerModel.setTelepon(tambahCustomer.getTelepon());

         // Simpan data ke database melalui repository
         detailCustomerRepo.save(detailCustomerModel);

         redisTemplate.opsForValue().set("detailCustomer", detailCustomerModel); // Simpan data ke Redis

         // Kirim pesan ke Kafka
         kafkaProducerService.sendMessage(detailCustomerModel.getNama());

         // Buat response body yang berisi data yang baru ditambahkan
         ResponseBody responseBody = new ResponseBody();
         responseBody.setTotal(detailCustomerRepo.count()); // Menghitung total data di database
         responseBody.setData(detailCustomerModel); // Menyertakan data yang ditambahkan
         responseBody.setMessage("Data berhasil ditambahkan"); // Pesan sukses
         return ResponseEntity.ok(responseBody); // Kembalikan response dengan status 200 OK

      } catch (Exception e) {
         // Log error dan kembalikan response dengan status 400 Bad Request
         ResponseBody responseBody = new ResponseBody();
         responseBody.setMessage("Data gagal ditambahkan");
         return ResponseEntity.badRequest().body(responseBody);
      }
   }

   // Metode untuk mengambil data dari database PostgreSQL
   public ResponseEntity<ResponseBody> getDetailCustomerPG() {
      try {
         ResponseBody responseBody = new ResponseBody();
         responseBody.setData(detailCustomerRepo.findAll()); // Ambil semua data dari database
         responseBody.setTotal(detailCustomerRepo.count()); // Hitung total data di database
         responseBody.setMessage("Data berhasil diambil"); // Pesan sukses
         return ResponseEntity.ok(responseBody); // Kembalikan response dengan status 200 OK

      } catch (Exception e) {
         // Log error dan kembalikan response dengan status 400 Bad Request
         ResponseBody responseBody = new ResponseBody();
         responseBody.setMessage("Data gagal diambil");
         return ResponseEntity.badRequest().body(responseBody);
      }
   }

   // Metode untuk mengambil data dari Redis
   public ResponseEntity<ResponseBody> getDetailCustomerRedis() {
      try {
         ResponseBody responseBody = new ResponseBody();
         responseBody.setTotal(detailCustomerRepo.count()); // Hitung total data di database
         responseBody.setData(redisTemplate.opsForValue().get("detailCustomer")); // Ambil data dari Redis
         responseBody.setMessage("Data berhasil diambil"); // Pesan sukses
         return ResponseEntity.ok(responseBody); // Kembalikan response dengan status 200 OK

      } catch (Exception e) {
         // Log error dan kembalikan response dengan status 400 Bad Request
         ResponseBody responseBody = new ResponseBody();
         responseBody.setMessage("Data gagal diambil");
         return ResponseEntity.badRequest().body(responseBody);
      }
   }

   // Simpan data ke Elasticsearch
   public ResponseEntity<ResponseBody> postDetailCustomerElasticsearch(TambahCustomer tambahCustomer) {
      try{
         // Buat objek DetailCustomerElasticsearchModel dari request
         DetailCustomerElasticsearchModel detailCustomerElasticsearchModel = new DetailCustomerElasticsearchModel();
         detailCustomerElasticsearchModel.setNama(tambahCustomer.getNama());
         detailCustomerElasticsearchModel.setEmail(tambahCustomer.getEmail());
         detailCustomerElasticsearchModel.setAlamat(tambahCustomer.getAlamat());
         detailCustomerElasticsearchModel.setTelepon(tambahCustomer.getTelepon());

         // Simpan data ke Elasticsearch melalui repository
         detailCustomerElasticsearchRepo.save(detailCustomerElasticsearchModel);

         // Buat response body yang berisi data yang baru ditambahkan
         ResponseBody responseBody = new ResponseBody();
         responseBody.setTotal(detailCustomerElasticsearchRepo.count());
         responseBody.setData(detailCustomerElasticsearchModel); // Menyertakan data yang ditambahkan
         responseBody.setMessage("Data berhasil ditambahkan"); // Pesan sukses
         return ResponseEntity.ok(responseBody); // Kembalikan response dengan status 200 OK

      } catch (Exception e) {
         // Log error dan kembalikan response dengan status 400 Bad Request
         ResponseBody responseBody = new ResponseBody();
         responseBody.setMessage("Data gagal ditambahkan");
         return ResponseEntity.badRequest().body(responseBody);

      }
   }

   //Ambil data dari elastic search
   public ResponseEntity<ResponseBody> getDetailCustomerElasticsearch() {
      try {
         ResponseBody responseBody = new ResponseBody();
         responseBody.setData(detailCustomerElasticsearchRepo.findAll()); // Ambil semua data dari Elasticsearch
         responseBody.setTotal(detailCustomerElasticsearchRepo.count()); // Hitung total data di Elasticsearch
         responseBody.setMessage("Data berhasil diambil"); // Pesan sukses
         return ResponseEntity.ok(responseBody); // Kembalikan response dengan status 200 OK

      } catch (Exception e) {
         // Log error dan kembalikan response dengan status 400 Bad Request
         ResponseBody responseBody = new ResponseBody();
         responseBody.setMessage("Data gagal diambil");
         return ResponseEntity.badRequest().body(responseBody);
      }
   }




}













