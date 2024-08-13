package com.example.customer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka 
public class KafkaConfig {

   @Value("${spring.kafka.bootstrap-servers}")
   private String bootstrapServers; // Mendapatkan alamat server Kafka dari application.properties

   @Value("${spring.kafka.producer.key-serializer}")
   private String producerKeySerializer; // Mendapatkan serializer untuk kunci dari application.properties

   @Value("${spring.kafka.producer.value-serializer}")
   private String producerValueSerializer; // Mendapatkan serializer untuk nilai dari application.properties

   @Value("${spring.kafka.consumer.key-deserializer}")
   private String consumerKeyDeserializer; // Mendapatkan deserializer untuk kunci dari application.properties

   @Value("${spring.kafka.consumer.value-deserializer}")
   private String consumerValueDeserializer; // Mendapatkan deserializer untuk nilai dari application.properties

   @Value("${spring.kafka.consumer.group-id}")
   private String groupId; // Mendapatkan ID grup untuk konsumer dari application.properties

   // Bean untuk konfigurasi producer Kafka
   @Bean
   public ProducerFactory<String, String> producerFactory() {
      Map<String, Object> configProps = new HashMap<>();
      configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); // Menetapkan alamat server Kafka
      configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, producerKeySerializer); // Menetapkan kelas serializer untuk kunci
      configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, producerValueSerializer); // Menetapkan kelas serializer untuk nilai
      return new DefaultKafkaProducerFactory<>(configProps); // Mengembalikan instance ProducerFactory
   }

   // Bean untuk template Kafka yang digunakan untuk mengirim pesan
   @Bean
   public KafkaTemplate<String, String> kafkaTemplate() {
      return new KafkaTemplate<>(producerFactory()); // Menggunakan factory untuk membuat KafkaTemplate
   }

   // Bean untuk konfigurasi container listener Kafka
   @Bean
   public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
      ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
      factory.setConsumerFactory(consumerFactory()); // Menggunakan factory untuk membuat ConsumerFactory
      return factory; // Mengembalikan instance ConcurrentKafkaListenerContainerFactory
   }

   // Bean untuk konfigurasi consumer Kafka
   @Bean
   public ConsumerFactory<String, String> consumerFactory() {
      Map<String, Object> configProps = new HashMap<>();
      configProps.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); // Menetapkan alamat server Kafka
      configProps.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, groupId); // Menetapkan ID grup konsumer
      configProps.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
            consumerKeyDeserializer); // Menetapkan kelas deserializer untuk kunci
      configProps.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
            consumerValueDeserializer); // Menetapkan kelas deserializer untuk nilai
      return new DefaultKafkaConsumerFactory<>(configProps); // Mengembalikan instance ConsumerFactory
   }
}







