package com.example.jpa.repository;

import com.example.jpa.domain.NoGenerativeStrategy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.is;

/**
 * RunWith 는 테스트를 실행시켜주는 것이고. SpringRunner 는 빈들을 올린다. SpringBootTest 와는 다르게 다 올리지는 않음.
 * DataJpaTest 는 인메모리 데이터베이스로 설정하고 엔티티 스캔.
 * 2개 Annotation 은 같이 써야 함. 스프링 공식문서에 나와있음.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class NoGenerativeStrategyRepositoryTest {

    @Autowired
    private NoGenerativeStrategyRepository noGenerativeStrategyRepository;

    @Test
    public void ID_생성전략이_없을떄_동작방식_테스트() throws Exception {
        // given
        NoGenerativeStrategy noGenerativeStrategy =
                NoGenerativeStrategy.builder()
                        .id(2L)
                        .name("test")
                        .build();

        // when
        noGenerativeStrategyRepository.save(noGenerativeStrategy);
        Optional<NoGenerativeStrategy> result = noGenerativeStrategyRepository.findById(2L);

        // then
        Assert.assertThat(result.get().getName(), is("test"));
    }
}
