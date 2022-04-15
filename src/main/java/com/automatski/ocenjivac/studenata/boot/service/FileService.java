package com.automatski.ocenjivac.studenata.boot.service;

import com.automatski.ocenjivac.studenata.boot.entity.Files;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

public interface FileService {
    Files saveFile(MultipartFile file) throws Exception;

    Files getFile(Long fileId) throws Exception;

    String getFileContentById(Long fileId);



}
