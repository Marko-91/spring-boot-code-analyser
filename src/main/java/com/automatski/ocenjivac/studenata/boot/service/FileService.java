package com.automatski.ocenjivac.studenata.boot.service;

import com.automatski.ocenjivac.studenata.boot.entity.File;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    File saveFile(MultipartFile file) throws Exception;

    File getFile(String fileId) throws Exception;

    String getFileContentById(String fileId);

    ResponseEntity<File> evaluateFile(String fileId, File file);
}
