package com.automatski.ocenjivac.studenata.boot.service;

import com.automatski.ocenjivac.studenata.boot.entity.Student;
import com.automatski.ocenjivac.studenata.boot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> fetchAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student editStudent(Student student, Long studentId) {
        Student l_student = studentRepository.findById(studentId).get();


        return studentRepository.save(l_student);
    }

    @Override
    public void deleteStudentById(long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public Student saveStudent(Student l_student) {
        return studentRepository.save(l_student);
    }

    @Override
    public Student findById(long id) {
        return studentRepository.findById(id).get();
    }



}
