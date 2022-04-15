package com.automatski.ocenjivac.studenata.boot.service;

import com.automatski.ocenjivac.studenata.boot.entity.Files;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Files saveFile(MultipartFile file) throws Exception;

    Files getFile(String fileId) throws Exception;

    String getFileContentById(String fileId);

    ResponseEntity<Files> evaluateFile(String fileId, Files files);
}
