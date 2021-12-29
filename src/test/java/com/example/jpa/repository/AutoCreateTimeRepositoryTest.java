package com.example.jpa.repository;


import com.example.jpa.domain.AutoCreateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoCreateTimeRepositoryTest {

    @Autowired
    private AutoCreateTimeRepository autoCreateTimeRepository;

    @Test
    public void 생성시간_수정시간_자동생성_확인_테스트() throws Exception {
        AutoCreateTime autoCreateTime = AutoCreateTime.builder()
                .test("test")
                .build();
        AutoCreateTime result = autoCreateTimeRepository.save(autoCreateTime);

        Assert.assertNotNull(result.getCreatedDate());
        Assert.assertNotNull(result.getModifiedDate());
    }
}
