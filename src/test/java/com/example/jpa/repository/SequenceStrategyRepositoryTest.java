package com.example.jpa.repository;

import com.example.jpa.domain.SequenceStrategy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;

/**
 * RunWith 는 테스트를 실행시켜주는 것이고. SpringRunner 는 빈들을 올린다. SpringBootTest 와는 다르게 다 올리지는 않음.
 * DataJpaTest 는 인메모리 데이터베이스로 설정하고 엔티티 스캔.
 * 2개 Annotation 은 같이 써야 함. 스프링 공식문서에 나와있음.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class SequenceStrategyRepositoryTest {

    @Autowired
    private SequenceStrategyRepository sequenceStrategyRepository;

    @Test
    public void 시퀀스_전략생성_방식_테스트() throws Exception {
        // given
        SequenceStrategy sequenceStrategy = SequenceStrategy.builder()
                .name("test")
                .build();

        SequenceStrategy sequenceStrategy2 = SequenceStrategy.builder()
                .name("test22")
                .build();

        // when
        SequenceStrategy result1 = sequenceStrategyRepository.save(sequenceStrategy);
        SequenceStrategy result2 = sequenceStrategyRepository.save(sequenceStrategy2);

        // then
        Assert.assertThat(result1.getId(), is(1L));
        Assert.assertThat(result2.getId(), is(2L));
    }
}