package com.automatski.ocenjivac.studenata.boot.controller;

import com.automatski.ocenjivac.studenata.boot.ResponseData;
import com.automatski.ocenjivac.studenata.boot.entity.File;
import com.automatski.ocenjivac.studenata.boot.service.FileService;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestController
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        File l_file = null;
        l_file = fileService.saveFile(file);
        String downloadUrl = "";
        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download")
                .path(l_file.getId())
                .toUriString();

        return new ResponseData(file.getName(),
                downloadUrl,
                file.getContentType(),
                file.getSize());
    }

    @GetMapping("/file/{id}")
    public String getFileContentById(@PathVariable("id") String fileId) {
        String soruceCode = fileService.getFileContentById(fileId);
        return soruceCode;
    }

    @PutMapping("/file/{id}")
    public ResponseEntity<File> evaluateFileById(@PathVariable("id") String fileId,
                                                 @RequestBody File file, BindingResult result) {
        return fileService.evaluateFile(fileId, file);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        File file = null;
        file = fileService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() +"\"")
                .body(new ByteArrayResource(file.getData()));
    }
}
