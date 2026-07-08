package com.example.firstprojectspringboot.service;

import com.example.firstprojectspringboot.dto.StudentRequestDto;
import com.example.firstprojectspringboot.dto.StudentResponseDto;
import org.springframework.data.domain.Page;
import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto requestDto);

    Page<StudentResponseDto> getAllStudents(int page, int size);

    StudentResponseDto getStudentByRollNumber(String rollNumber);

    StudentResponseDto updateStudent(String rollNumber, StudentRequestDto requestDto);

    List<StudentResponseDto> searchStudents(String name);

    void deleteStudent(String rollNumber);
}