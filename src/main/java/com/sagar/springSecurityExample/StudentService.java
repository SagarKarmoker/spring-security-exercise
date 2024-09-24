package com.sagar.springSecurityExample;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Sagar", 18),
            new Student(2, "Sagar Karmoker", 23)
    ));


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(Student student) {
        students.add(student);
    }
}
