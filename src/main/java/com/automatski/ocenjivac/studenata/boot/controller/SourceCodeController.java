package com.automatski.ocenjivac.studenata.boot.controller;

import com.automatski.ocenjivac.studenata.boot.entity.Files;
import com.automatski.ocenjivac.studenata.boot.entity.SourceCode;
import com.automatski.ocenjivac.studenata.boot.responseModel.ResponseData;
import com.automatski.ocenjivac.studenata.boot.service.FileService;
import com.automatski.ocenjivac.studenata.boot.service.SourceCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class SourceCodeController {

    @Autowired
    FileService fileService;

    @Autowired
    SourceCodeService service;

    public SourceCode postSourceFile(Long fileId) throws Exception {
        SourceCode l_sourceCodeFile = new SourceCode(fileService.getFileContentById(fileId), fileService.getFile(fileId));
        return service.saveSourceFile(l_sourceCodeFile);
    }
}
