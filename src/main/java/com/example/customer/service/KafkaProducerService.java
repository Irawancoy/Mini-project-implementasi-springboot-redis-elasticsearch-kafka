package com.example.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

   // Mengambil nama topik Kafka dari konfigurasi aplikasi
   @Value("${spring.kafka.topic}")
   private String topic;

   // Menginnjeksikan KafkaTemplate untuk mengirim pesan ke Kafka
   @Autowired
   private KafkaTemplate<String, String> kafkaTemplate;

   // Metode untuk mengirim pesan ke topik Kafka yang telah ditentukan
   public void sendMessage(String message) {
      kafkaTemplate.send(topic, message); // Mengirim pesan ke Kafka
   }
   
}







