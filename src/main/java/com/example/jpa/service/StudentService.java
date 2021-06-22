package com.example.jpa.service;

import com.example.jpa.domain.Student;
import com.example.jpa.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void saveStudents() {
        List<Student> list = new ArrayList<Student>() {
            {
                for (int i = 0 ; i < 1000 ; i++) {
                    add(Student.builder().name("test" + i)
                            .age(20)
                            .address("test" + i)
                            .etc("test" + i)
                            .build());
                }
            }
        };
        studentRepository.saveAll(list);
    }
}
