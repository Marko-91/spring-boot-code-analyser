package com.automatski.ocenjivac.studenata.boot.controller;

import com.automatski.ocenjivac.studenata.boot.ResponseData;
import com.automatski.ocenjivac.studenata.boot.StudentResponseData;
import com.automatski.ocenjivac.studenata.boot.entity.Files;
import com.automatski.ocenjivac.studenata.boot.entity.Student;
import com.automatski.ocenjivac.studenata.boot.service.FileService;
import org.hibernate.mapping.Collection;
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

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping(path = "/upload", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseData uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        Files l_files = null;
        l_files = fileService.saveFile(file);
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
    public String getFileContentById(@PathVariable("id") String fileId) {
        String soruceCode = fileService.getFileContentById(fileId);
        return soruceCode;
    }

    @PutMapping("/file/{id}")
    public ResponseEntity<Files> evaluateFileById(@PathVariable("id") String fileId,
                                                  @RequestBody Files files, BindingResult result) {
        return fileService.evaluateFile(fileId, files);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Files files = null;
        files = fileService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(files.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + files.getFileName() +"\"")
                .body(new ByteArrayResource(files.getData()));
    }
}
