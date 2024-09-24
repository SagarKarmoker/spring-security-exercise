package com.sagar.springSecurityExample.controller;

import com.sagar.springSecurityExample.model.Student;
import com.sagar.springSecurityExample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> students() {
        return studentService.getStudents();
    }

    @PostMapping("/students/create")
    public Student createStudent(@RequestBody Student student) {
        studentService.setStudents(student);
        return student;
    }

}
