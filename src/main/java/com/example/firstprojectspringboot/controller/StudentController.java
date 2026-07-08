package com.example.firstprojectspringboot.controller;
import org.springframework.security.core.Authentication;
import com.example.firstprojectspringboot.dto.StudentRequestDto;
import com.example.firstprojectspringboot.dto.StudentResponseDto;
import com.example.firstprojectspringboot.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public StudentResponseDto createStudent(@Valid @RequestBody StudentRequestDto requestDto) {
        return studentService.createStudent(requestDto);
    }
    @GetMapping("/whoami")
    public String whoAmI(Authentication authentication) {
        return authentication.getName() + " -> " + authentication.getAuthorities();
    }
    @GetMapping
    public Page<StudentResponseDto> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return studentService.getAllStudents(page, size);
    }

    // ✅ was Long id — the frontend sends the roll number, not the database id
    @GetMapping("/{rollNumber}")
    public StudentResponseDto getStudentByRollNumber(@PathVariable String rollNumber) {
        return studentService.getStudentByRollNumber(rollNumber);
    }

    @PutMapping("/{rollNumber}")
    public StudentResponseDto updateStudent(
            @PathVariable String rollNumber,
            @Valid @RequestBody StudentRequestDto requestDto) {

        return studentService.updateStudent(rollNumber, requestDto);
    }
    @GetMapping("/test")
    public String test() {
        return "Security Passed";
    }
    @DeleteMapping("/{rollNumber}")
    public String deleteStudent(@PathVariable String rollNumber) {
        studentService.deleteStudent(rollNumber);
        return "Student deleted successfully";
    }

    @GetMapping("/search")
    public List<StudentResponseDto> searchStudents(@RequestParam String name) {
        return studentService.searchStudents(name);
    }
}