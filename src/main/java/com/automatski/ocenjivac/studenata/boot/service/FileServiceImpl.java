package com.automatski.ocenjivac.studenata.boot.service;

import com.automatski.ocenjivac.studenata.boot.entity.Files;
import com.automatski.ocenjivac.studenata.boot.repository.FileRepository;
import com.automatski.ocenjivac.studenata.boot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    StudentRepository studentRepository;


    @Override
    public Files saveFile(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(fileName);
        try {
            if (fileName.contains("..")) {
                throw new Exception("File firstName contains invalid path sequence");
            }

            Files l_files = new Files(fileName, file.getContentType(), file.getBytes());
            System.out.println(file.getContentType());


            return fileRepository.save(l_files);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Could not save file");
        }
    }

    @Override
    public Files saveFile(MultipartFile file, Long studentId) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(fileName);
        try {
            if (fileName.contains("..")) {
                throw new Exception("File firstName contains invalid path sequence");
            }

            Files l_files = new Files(fileName, file.getContentType(), "1", file.getBytes(), studentRepository.getById(studentId));
            System.out.println(file.getContentType());


            return fileRepository.save(l_files);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Could not save file");
        }
    }

    @Override
    public Files getFile(Long fileId) throws Exception {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new Exception("File not found with id: " + fileId));
    }

    @Override
    public String getFileContentById(Long fileId) {
        Files files = fileRepository.findById(fileId).get();

        return new String(files.getData(), StandardCharsets.UTF_8);
    }

    private String oceniRad(String fileContentById) {
        //analiza koda
        return "5";
    }

}
