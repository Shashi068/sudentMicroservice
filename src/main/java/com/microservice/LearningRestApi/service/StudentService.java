package com.microservice.LearningRestApi.service;

import com.microservice.LearningRestApi.dto.AddStudentRequestDTO;
import com.microservice.LearningRestApi.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Long id);
    StudentDTO createStudent(AddStudentRequestDTO addStudentRequestDTO);
    void deleteStudent(Long id);


}
