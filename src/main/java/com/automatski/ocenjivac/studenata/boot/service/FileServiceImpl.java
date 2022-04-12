package com.automatski.ocenjivac.studenata.boot.service;

import com.automatski.ocenjivac.studenata.boot.entity.File;
import com.automatski.ocenjivac.studenata.boot.entity.Student;
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
    public File saveFile(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new Exception("File name contains invalid path sequence");
            }

            File l_file = new File(fileName, file.getContentType(), file.getBytes());

            return fileRepository.save(l_file);
        } catch (Exception e) {
            throw new Exception("Could not save file");
        }
    }

    @Override
    public File getFile(String fileId) throws Exception {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new Exception("File not found with id: " + fileId));
    }

    @Override
    public String getFileContentById(String fileId) {
        File file = fileRepository.findById(fileId).get();

        return new String(file.getData(), StandardCharsets.UTF_8);
    }

    @Override
    public ResponseEntity<File> evaluateFile(String fileId, File file) {
        File l_file = fileRepository.findById(fileId).get();
        l_file.setGrade(oceniRad(getFileContentById(fileId)));

        File updatedFile = fileRepository.save(l_file);
        return ResponseEntity.ok(updatedFile);
    }

    private String oceniRad(String fileContentById) {
        //analiza koda
        return null;
    }

}
