package com.example.jpa.repository;

import com.example.jpa.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentJdbcBatchRepositoryTest {

    @Autowired
    private StudentJdbcBatchRepository studentJdbcBatchRepository;

    @Test
    public void STUDENT_JDBC_BATCH_TEST() {
        List<Student> students = new ArrayList<Student>() {
            {
                for (int i = 0 ; i < 200000 ; i++) {
                    add(Student.builder()
                            .id(Long.valueOf(i) + 1)
                            .name("test" + i)
                            .age(20)
                            .address("test" + i)
                            .etc("test" + i)
                            .build());
                }
            }
        };

        studentJdbcBatchRepository.batchInsert(students);
    }
}
