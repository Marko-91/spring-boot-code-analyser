package com.automatski.ocenjivac.studenata.boot.repository;

import com.automatski.ocenjivac.studenata.boot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT file_name FROM files WHERE student_id = ?1", nativeQuery = true)
    List<String> findAllFilesByStudentId(Long studentId);

    @Query(value = "SELECT id FROM student WHERE first_name = ?1 AND last_name = ?2", nativeQuery = true)
    Long findStudentByFirstNameAndLastNameTest(String firstName, String lastName);
}
