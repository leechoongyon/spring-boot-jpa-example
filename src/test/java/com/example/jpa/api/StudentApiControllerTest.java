package com.example.jpa.api;

import com.example.jpa.JpaExampleApplication;
import com.example.jpa.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= JpaExampleApplication.class)
@AutoConfigureMockMvc
public class StudentApiControllerTest {

    @Autowired
    private StudentService studentService;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new StudentApiController(studentService))
                .build();
    }

    /**
     * h2 의 경우 생성전략을 Auto 해놓으면 각 vendor hibernate_sequence 를 가져와서 채번함.
     * 이 때, batch 옵션이 켜져있으면 채번을 먼저 다 한후, insert 를 하기에 속도 절감이 됨.
     * 이 예제는 jdbc batch 가 동작함.
     * @throws Exception
     */
    @Test
    public void jpaBulkInsertTest() throws Exception {
        this.mockMvc.perform(post("/api/training1/students"))
                .andExpect(status().isOk());
    }
}