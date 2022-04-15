package com.automatski.ocenjivac.studenata.boot.repository;

import com.automatski.ocenjivac.studenata.boot.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<Files, String> {

//    @Query("", nativeQuery = true)

}
