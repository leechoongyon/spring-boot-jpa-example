package com.example.jpa.repository;

import com.example.jpa.domain.Book;
import com.example.jpa.domain.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;

/**
 * RunWith 는 테스트를 실행시켜주는 것이고. SpringRunner 는 빈들을 올린다. SpringBootTest 와는 다르게 다 올리지는 않음.
 * DataJpaTest 는 인메모리 데이터베이스로 설정하고 엔티티 스캔.
 * 2개 Annotation 은 같이 써야 함. 스프링 공식문서에 나와있음.
 * reference : https://4whomtbts.tistory.com/128
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Book 을 저장할 때 Student 도 같이 저장하고 싶으면, Book 의 cascade 를 걸어줘야 Student 도 같이 저장 됨.
     * 반대로 Student 저장할 때, Book 도 저장하고 싶으면 Student 에 cascade 걸어주면 됨.
     * @throws Exception
     */
    @Test
    public void MANY_TO_ONE_SAVE_TEST() throws Exception {
        Student student = Student.builder()
                .name("test student")
                .age(20)
                .etc("etc1")
                .build();

        Book book = Book.builder()
                .name("test book")
                .price(20000L)
                .student(student)
                .build();

        bookRepository.save(book);

        List<Book> list = bookRepository.findAll();
        Assert.assertThat(list.get(0).getName(), is("test book"));
    }

}