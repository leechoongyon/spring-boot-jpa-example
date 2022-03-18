package com.example.jpa.repository;


import com.example.jpa.domain.AutoCreateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoCreateTimeRepositoryTest {

    @Autowired
    private AutoCreateTimeRepository autoCreateTimeRepository;

    /**
     * ex)
     * - @CreatedDate 사용 시, 2202-02-14T20:00:00.234 이런식으로 출력
     * - @CreationTimeStamp 사용 시, 2022-02-14T20:00:05.293+09:00[Asia/Seoul] 이런식으로 출력
     * @throws Exception
     */
    @Test
    public void 생성시간_수정시간_자동생성_확인_테스트() throws Exception {
        AutoCreateTime autoCreateTime = AutoCreateTime.builder()
                .test("test")
                .build();
        AutoCreateTime result = autoCreateTimeRepository.save(autoCreateTime);

        System.out.println(result.getCreatedAt());
        System.out.println(result.getUpdatedAt());

        Assert.assertNotNull(result.getCreatedAt());
        Assert.assertNotNull(result.getUpdatedAt());
    }
}
