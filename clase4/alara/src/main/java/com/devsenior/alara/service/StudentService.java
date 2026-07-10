package com.devsenior.alara.service;

import org.springframework.stereotype.Service;

import com.devsenior.alara.dto.StudentRequest;
import com.devsenior.alara.dto.StudentResponse;
import com.devsenior.alara.model.Student;
import com.devsenior.alara.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository){
        this.repository = repository;
    }

    public StudentResponse save(StudentRequest request){
        // 1. crear un objeto de tipo model
        Student student = new Student();

        //2. transformamos el dto request a model (atribbuto por atributo):
        student.setId(System.currentTimeMillis());
        student.setName(request.name());
        student.setAge(request.age());
        student.setEmail(request.email());

        // 3. salvamos
        repository.save(student);

        //4. tomamos el model para pasarle los datos al dto response
        return new StudentResponse(
            student.getId(),
            student.getName(),
            student.getAge(),
            student.getEmail()
            );

    }
}
