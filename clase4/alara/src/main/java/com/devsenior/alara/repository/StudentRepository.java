package com.devsenior.alara.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.devsenior.alara.model.Student;

@Repository
public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public Student save(Student student){
        students.add(student);
        return student;
    }
    
}
