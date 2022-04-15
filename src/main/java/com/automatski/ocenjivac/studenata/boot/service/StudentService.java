package com.automatski.ocenjivac.studenata.boot.service;

import com.automatski.ocenjivac.studenata.boot.entity.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {


    Student addStudent(Student student);

    List<Student> fetchAllStudents();

    ResponseEntity<Student> editStudent(Long studentId, Student student);

    void deleteStudentById(long studentId);

    Student saveStudent(Student l_student);

    Student fetchStudentById(Long id);

    List<String> getFileNamesByStudentId(Long studentId);

    //Student getStudentByFileId(String fileId);
}
