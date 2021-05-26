package com.example.jpa.api;

import com.example.jpa.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StudentApiController {

    private final StudentService studentService;

    /**
     * bulk insert test
     */
    @PostMapping("/api/training1/students")
    public void saveStudents() {
        long start = System.currentTimeMillis();
        studentService.saveStudents();
        log.info("elapsed time : {}", System.currentTimeMillis() - start);
    }
}
