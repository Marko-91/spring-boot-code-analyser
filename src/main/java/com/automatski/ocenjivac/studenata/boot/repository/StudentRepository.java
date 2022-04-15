package com.automatski.ocenjivac.studenata.boot.repository;

import com.automatski.ocenjivac.studenata.boot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    @Query
//    Student getStudentByFileId(String fileId);
}
