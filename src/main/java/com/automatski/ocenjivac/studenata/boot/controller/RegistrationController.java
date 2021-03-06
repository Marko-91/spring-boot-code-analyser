package com.automatski.ocenjivac.studenata.boot.controller;

import com.automatski.ocenjivac.studenata.boot.entity.Student;
import com.automatski.ocenjivac.studenata.boot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    StudentService studentService;


    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/students")
    public List<Student> getAllStudents(Student student) {
        return studentService.fetchAllStudents();
    }

    @PostMapping("/register")
    public Student addStudent(@RequestBody Student l_student, BindingResult result) {
        return studentService.saveStudent(l_student);
    }

    @PutMapping("/student/edit/{id}")
    public ResponseEntity<Student> editStudent(@PathVariable("id") long id,
                                               @RequestBody Student student, BindingResult result) {
        return studentService.editStudent(id, student);
    }

    @DeleteMapping("/student/delete/{id}")
    public void deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudentById(id);
    }

    @GetMapping("/student/{id}")
    public Student getById(@PathVariable("id") Long id) {
        return studentService.fetchStudentById(id);
    }

    @GetMapping("/testGetFileNames/{studentId}")
    public String downloadFile(@PathVariable Long studentId) throws Exception {

        List<String> s = studentService.getFileNamesByStudentId(studentId);
        return s.toString();
    }
}
