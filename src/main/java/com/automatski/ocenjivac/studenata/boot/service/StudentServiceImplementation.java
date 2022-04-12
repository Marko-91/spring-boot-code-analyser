package com.automatski.ocenjivac.studenata.boot.service;

import com.automatski.ocenjivac.studenata.boot.entity.Student;
import com.automatski.ocenjivac.studenata.boot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Student> editStudent(Long studentId, Student student) {
        Student l_student = studentRepository.findById(studentId).get();
        l_student.setEmail(student.getEmail());
        l_student.setName(student.getName());

        Student updatedStudent = studentRepository.save(l_student);
        return ResponseEntity.ok(updatedStudent);
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
    public Student fetchStudentById(Long id) {
        return studentRepository.findById(id).get();
    }


}
