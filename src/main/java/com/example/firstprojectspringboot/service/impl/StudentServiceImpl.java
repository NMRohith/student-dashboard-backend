package com.example.firstprojectspringboot.service.impl;

import com.example.firstprojectspringboot.dto.StudentRequestDto;
import com.example.firstprojectspringboot.dto.StudentResponseDto;
import com.example.firstprojectspringboot.entity.Student;
import com.example.firstprojectspringboot.repository.StudentRepository;
import com.example.firstprojectspringboot.service.StudentService;
import com.example.firstprojectspringboot.exception.ResourceNotFoundException;
import com.example.firstprojectspringboot.exception.DuplicateStudentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentResponseDto createStudent(StudentRequestDto requestDto) {

        // ✅ was missing before — this is what DuplicateStudentException was built for
        if (studentRepository.existsByRollNumber(requestDto.getRollNumber())) {
            throw new DuplicateStudentException(
                    "Student already exists with roll number: " + requestDto.getRollNumber());
        }

        Student student = Student.builder()
                .rollNumber(requestDto.getRollNumber())
                .registrationNumber(requestDto.getRegistrationNumber())
                .name(requestDto.getName())
                .age(requestDto.getAge())
                .gender(requestDto.getGender())
                .email(requestDto.getEmail())
                .address(requestDto.getAddress())
                .build();

        Student savedStudent = studentRepository.save(student);
        return mapToResponseDto(savedStudent);
    }

    @Override
    public Page<StudentResponseDto> getAllStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable).map(this::mapToResponseDto);
    }

    @Override
    public StudentResponseDto getStudentByRollNumber(String rollNumber) {
        Student student = studentRepository.findByRollNumber(rollNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with roll number: " + rollNumber));

        return mapToResponseDto(student);
    }

    @Override
    public StudentResponseDto updateStudent(String rollNumber, StudentRequestDto requestDto) {
        Student student = studentRepository.findByRollNumber(rollNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with roll number: " + rollNumber));

        student.setRegistrationNumber(requestDto.getRegistrationNumber());
        student.setName(requestDto.getName());
        student.setAge(requestDto.getAge());
        student.setGender(requestDto.getGender());
        student.setEmail(requestDto.getEmail());
        student.setAddress(requestDto.getAddress());

        Student updatedStudent = studentRepository.save(student);
        return mapToResponseDto(updatedStudent);
    }

    @Override
    public void deleteStudent(String rollNumber) {
        Student student = studentRepository.findByRollNumber(rollNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with roll number: " + rollNumber));

        studentRepository.delete(student);
    }

    @Override
    public List<StudentResponseDto> searchStudents(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    private StudentResponseDto mapToResponseDto(Student student) {
        return StudentResponseDto.builder()
                .id(student.getId())
                .rollNumber(student.getRollNumber())
                .registrationNumber(student.getRegistrationNumber())
                .name(student.getName())
                .age(student.getAge())
                .gender(student.getGender())
                .email(student.getEmail())
                .address(student.getAddress())
                .build();
    }
}