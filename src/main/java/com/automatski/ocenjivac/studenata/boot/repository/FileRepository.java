package com.automatski.ocenjivac.studenata.boot.repository;

import com.automatski.ocenjivac.studenata.boot.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<Files, Long> {

    @Query(value = "SELECT file_name FROM files WHERE student_id = ?1", nativeQuery = true)
    List<String> findAllFilesByStudentId(Long studentId);

}
