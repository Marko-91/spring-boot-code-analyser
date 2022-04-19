package com.automatski.ocenjivac.studenata.boot.service;

import com.automatski.ocenjivac.studenata.boot.entity.SourceCode;
import com.automatski.ocenjivac.studenata.boot.error.SourceCodeNotFoundException;

public interface SourceCodeService {
    String getSourceCode(Long fileId);

    SourceCode saveSourceFile(SourceCode sourceCode) throws SourceCodeNotFoundException;
}
