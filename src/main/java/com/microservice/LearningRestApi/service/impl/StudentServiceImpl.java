package com.microservice.LearningRestApi.service.impl;

import com.microservice.LearningRestApi.dto.AddStudentRequestDTO;
import com.microservice.LearningRestApi.dto.StudentDTO;
import com.microservice.LearningRestApi.entity.Student;
import com.microservice.LearningRestApi.repository.StudentRepository;
import com.microservice.LearningRestApi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    public List<StudentDTO> getAllStudents(){
        List<Student> students=studentRepository.findAll();
       List<StudentDTO> studentDTOList= students.stream().map(student -> new StudentDTO(student.getId(),student.getName(),student.getEmail())).toList();
       return studentDTOList;
    }
    public StudentDTO getStudentById(Long id) {
    Student std = studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not find with Id: "+id));
    StudentDTO studentDTO=modelMapper.map(std,StudentDTO.class);
    return  studentDTO;
    }

    @Override
    public StudentDTO createStudent(AddStudentRequestDTO addStudentRequestDTO) {
        Student newStudent=modelMapper.map(addStudentRequestDTO,Student.class);
        Student std=studentRepository.save(newStudent);
        return modelMapper.map(std,StudentDTO.class);
    }

    @Override
    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student does not exist with id: "+id);
        }
        studentRepository.deleteById(id);
    }

}
