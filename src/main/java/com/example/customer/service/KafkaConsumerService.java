package com.example.customer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class KafkaConsumerService {
   
   // Menggunakan anotasi @KafkaListener untuk mendengarkan pesan dari topik Kafka.
   // `topics` menentukan nama topik yang akan didengarkan, diambil dari application.properties
   // `groupId` menentukan ID grup konsumen untuk pengelompokan konsumen, diambil dari application.properties
   @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
   public void listen(String message) {
      // Menggunakan log.info untuk mencatat pesan yang diterima dari Kafka
      log.info("User {} telah ditambahkan", message);
   }
}




