package com.automatski.ocenjivac.studenata.boot.service;

import com.automatski.ocenjivac.studenata.boot.entity.SourceCode;
import com.automatski.ocenjivac.studenata.boot.repository.SourceCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceCodeServiceImplementation implements SourceCodeService {

    @Autowired
    private SourceCodeRepository sourceCodeRepository;

    @Override
    public String getSourceCode(Long fileId) {
        return null;
    }

    @Override
    public SourceCode saveSourceFile(SourceCode l_sourceCode) {
        return sourceCodeRepository.save(l_sourceCode);
    }
}
