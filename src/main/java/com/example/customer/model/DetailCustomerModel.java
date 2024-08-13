package com.example.customer.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detail_customer")
public class DetailCustomerModel {

   // Anotasi untuk menandai field sebagai Primary Key
   @Id
   // Mengatur strategi auto increment untuk ID
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private int id;  // ID unik untuk setiap record, auto increment.

   // Menandai field 'nama' untuk di-mapping ke kolom 'nama' dalam tabel database
   @Column(name = "nama")
   private String nama;  // Nama customer.

   // Menandai field 'email' untuk di-mapping ke kolom 'email' dalam tabel database
   @Column(name = "email")
   private String email;  // Email customer.

   // Menandai field 'alamat' untuk di-mapping ke kolom 'alamat' dalam tabel database
   @Column(name = "alamat")
   private String alamat;  // Alamat customer.

   // Menandai field 'telepon' untuk di-mapping ke kolom 'telepon' dalam tabel database
   @Column(name = "telepon")
   private String telepon;  // Nomor telepon customer.
   
}



