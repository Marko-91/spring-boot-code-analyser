package com.automatski.ocenjivac.studenata.boot.repository;

import com.automatski.ocenjivac.studenata.boot.entity.SourceCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SourceCodeRepository extends JpaRepository<SourceCode, UUID> {
}
