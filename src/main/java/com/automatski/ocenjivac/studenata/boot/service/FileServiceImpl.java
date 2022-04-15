package com.automatski.ocenjivac.studenata.boot.service;

import com.automatski.ocenjivac.studenata.boot.entity.Files;
import com.automatski.ocenjivac.studenata.boot.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public Files saveFile(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(fileName);
        try {
            if (fileName.contains("..")) {
                throw new Exception("File name contains invalid path sequence");
            }

            Files l_files = new Files(fileName, file.getContentType(), file.getBytes());
            System.out.println(file.getContentType());


            return fileRepository.save(l_files);
        } catch (Exception e) {
            throw new Exception("Could not save file");
        }
    }

    @Override
    public Files getFile(String fileId) throws Exception {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new Exception("File not found with id: " + fileId));
    }

    @Override
    public String getFileContentById(String fileId) {
        Files files = fileRepository.findById(fileId).get();

        return new String(files.getData(), StandardCharsets.UTF_8);
    }

    @Override
    public ResponseEntity<Files> evaluateFile(String fileId, Files files) {
        Files l_files = fileRepository.findById(fileId).get();
        l_files.setGrade(oceniRad(getFileContentById(fileId)));

        Files updatedFiles = fileRepository.save(l_files);
        return ResponseEntity.ok(updatedFiles);
    }

    private String oceniRad(String fileContentById) {
        //analiza koda
        return "5";
    }

}
