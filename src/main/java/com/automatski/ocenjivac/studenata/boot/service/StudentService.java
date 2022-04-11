package com.automatski.ocenjivac.studenata.boot.service;

import com.automatski.ocenjivac.studenata.boot.entity.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentService {


    Student addStudent(Student student);

    List<Student> fetchAllStudents();

    Student editStudent(Student student, Long studentId);

    void deleteStudentById(long studentId);

    Student saveStudent(Student l_student);

    Student findById(long id);


}
