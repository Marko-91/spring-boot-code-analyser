package com.automatski.ocenjivac.studenata.boot.controller;

import com.automatski.ocenjivac.studenata.boot.responseModel.ResponseData;
import com.automatski.ocenjivac.studenata.boot.entity.Files;
import com.automatski.ocenjivac.studenata.boot.service.FileService;
import groovy.lang.GroovyClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class FileController {
    @Autowired
    private FileService fileService;

    private final GroovyClassLoader loader = new GroovyClassLoader();

    @PostMapping(path = "/student/{id}/upload", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseData uploadFile(@PathVariable("id") Long studentId,
                                   @RequestParam("file") MultipartFile file) throws Exception {
        Files l_files = null;

        l_files = fileService.saveFile(file, studentId);

        String downloadUrl = "";
        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(l_files.getId()))
                .toUriString();

        return new ResponseData(file.getName(),
                downloadUrl,
                file.getContentType(),
                file.getSize());
    }

    @GetMapping("/file/{id}")
    public String getFileContentById(@PathVariable("id") Long fileId) {
        String soruceCode = fileService.getFileContentById(fileId);
        return soruceCode;
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) throws Exception {
        Files files = null;
        files = fileService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(files.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + files.getFileName() +"\"")
                .body(new ByteArrayResource(files.getData()));
    }
}
