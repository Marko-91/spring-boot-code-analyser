package com.automatski.ocenjivac.studenata.boot.controller;

import com.automatski.ocenjivac.studenata.boot.entity.Student;
import com.automatski.ocenjivac.studenata.boot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class RegistrationController {

    @Autowired
    StudentService studentService;


    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/index")
    public String showStudentsList(Model model) {
        model.addAttribute("students", studentService.fetchAllStudents());
        return "index";
    }

    @GetMapping("/signup")
    public String signUpStudent(Student student) {
        return "signup";
    }

    @PostMapping("/add-student")
    public String addStudent(Student l_student, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "signup";
        }

        String s = l_student.getMyJavaTest();
        System.out.println("My java test: " + s);


        studentService.saveStudent(l_student);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String renderUpdateForm(@PathVariable("id") long id, Model model) {
        Student l_student = studentService.findById(id);

        model.addAttribute("student", l_student);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") long id,
                              Student student, BindingResult result, Model model) {

        if (result.hasErrors()) {
            student.setId(id);
            return "edit";
        }

        studentService.saveStudent(student);

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        studentService.deleteStudentById(id);
        return "redirect:/index";
    }

    @GetMapping("/student/{id}")
    public String renderStudentDashboard(@PathVariable("id") long id, Model model) throws IOException {
        Student l_student = studentService.findById(id);

        model.addAttribute("student", l_student);
        String file = l_student.getMyJavaTest();

        model.addAttribute("javacode", file);
        return "student-dashboard";
    }


}
