package com.example.firstprojectspringboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String rollNumber;
    @Column(unique = true, nullable = false)
    private String registrationNumber;
    private String name;

    private Integer age;

    private String gender;

    private String email;

    private String address;
}