package com.sagar.springSecurityExample;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class DemoController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String index() {
        return "Hello World";
    }

    @GetMapping("/get-session")
    public String getSession(HttpServletRequest request) {
        return request.getSession().getId();
    }

    @GetMapping("/csrf-token")
    public CsrfToken csrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

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
