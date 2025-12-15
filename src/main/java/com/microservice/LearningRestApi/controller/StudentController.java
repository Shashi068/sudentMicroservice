package com.microservice.LearningRestApi.controller;

import com.microservice.LearningRestApi.dto.AddStudentRequestDTO;
import com.microservice.LearningRestApi.dto.StudentDTO;
import com.microservice.LearningRestApi.entity.Student;
import com.microservice.LearningRestApi.repository.StudentRepository;
import com.microservice.LearningRestApi.service.StudentService;
import com.microservice.LearningRestApi.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
    }
    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getAllStudent(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }
    @PostMapping("/createStudent")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody AddStudentRequestDTO addStudentDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(addStudentDTO));
    }
    @DeleteMapping("/deleteStudent/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
    @PutMapping("/updateStudent")
    public ResponseEntity<StudentDTO> updateStudentById(@RequestBody AddStudentRequestDTO addStudentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(addStudentRequestDTO));
    }
}
