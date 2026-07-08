package com.example.firstprojectspringboot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDto {

    private Long id;

    private String rollNumber;

    private String registrationNumber;

    private String name;

    private Integer age;

    private String gender;

    private String email;

    private String address;
}