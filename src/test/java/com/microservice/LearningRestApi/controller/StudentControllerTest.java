package com.microservice.LearningRestApi.controller;

import com.microservice.LearningRestApi.dto.StudentDTO;
import com.microservice.LearningRestApi.service.StudentService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



    @Nested
    @ExtendWith(MockitoExtension.class)
    class StudentControllerTest {

        @Mock
        private StudentService studentService;

        @InjectMocks
        private StudentController studentController;

        @Test
        void getStudent_ReturnsStudentDTO_WhenIdExists() {
            Long studentId = 1L;
            StudentDTO studentDTO = new StudentDTO(studentId, "John Doe", "john.doe@example.com");
            Mockito.when(studentService.getStudentById(studentId)).thenReturn(studentDTO);

            ResponseEntity<StudentDTO> response = studentController.getStudent(studentId);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(studentDTO, response.getBody());
        }

        @Test
        void getStudent_ThrowsException_WhenIdDoesNotExist() {
            Long studentId = 999L;
            Mockito.when(studentService.getStudentById(studentId)).thenThrow(new RuntimeException("Student not found"));

            RuntimeException exception = assertThrows(RuntimeException.class, () -> studentController.getStudent(studentId));

            assertEquals("Student not found", exception.getMessage());
        }
    }

