package com.example.jpa.repository;

import com.example.jpa.domain.SampleWithAutoIncrement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SampleWithAutoIncrementRepositoryTest {


    @Autowired
    private SampleWithAutoIncrementRepository sampleWithAutoIncrementRepository;

    @Test
    @Transactional
    public void AUTOINCREMENT_PERSIST_MERGE_동작테스트() throws Exception {
        SampleWithAutoIncrement sample = new SampleWithAutoIncrement();
        sample.setSample("sample");
        sampleWithAutoIncrementRepository.save(sample);
    }
}