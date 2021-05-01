package com.example.jpa.repository.support;


import com.example.jpa.domain.IsNewSample;
import com.example.jpa.domain.SampleWithAutoIncrement;
import com.example.jpa.domain.SampleWithVersion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaMetamodelEntityInformationIntegrationTests {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void ID식별자_ISNEW_테스트() throws Exception {
        EntityInformation<IsNewSample, Long> entityInformation =
                new JpaMetamodelEntityInformation<>(IsNewSample.class, entityManager.getMetamodel());

        IsNewSample sample = new IsNewSample();
        // 아무 것도 세팅 안했으니 isNew -> true
        Assert.assertThat(entityInformation.isNew(sample), is(true));

        // @Id 에 값이 존재하니 new 가 아님.
        sample.setId(1L);
        Assert.assertThat(entityInformation.isNew(sample), is(false));

        // @Id 에 다시 null 을 넣으면 newState 상태
        sample.setId(null);
        Assert.assertThat(entityInformation.isNew(sample), is(true));
    }

    @Test
    public void VERSION_ISNEW_테스트() throws Exception {
        EntityInformation<SampleWithVersion, Long> entityInformation =
                new JpaMetamodelEntityInformation<>(SampleWithVersion.class, entityManager.getMetamodel());

        SampleWithVersion sampleWithVersion = new SampleWithVersion();

        Assert.assertThat(entityInformation.isNew(sampleWithVersion), is(true));

        // version 에 값이 들어가는 순간 NewState 가 아님.
        sampleWithVersion.setVersion(1L);
        Assert.assertThat(entityInformation.isNew(sampleWithVersion), is(false));

        // version 에 null 들어가는 순간 newState
        sampleWithVersion.setVersion(null);
        Assert.assertThat(entityInformation.isNew(sampleWithVersion), is(true));

        // Entity 에 @Version 이 들어가는 순간 식별자에 값이 들어가도 newState 상태를 판별하지 못함.
        sampleWithVersion.setId(1L);
        Assert.assertThat(entityInformation.isNew(sampleWithVersion), is(true));
    }

    @Test
    public void AUTOINCREMENT_ISNEW_테스트() throws Exception {
        EntityInformation<SampleWithAutoIncrement, Long> entityInformation =
                new JpaMetamodelEntityInformation<>(SampleWithAutoIncrement.class, entityManager.getMetamodel());

        SampleWithAutoIncrement sample = new SampleWithAutoIncrement();

        Assert.assertThat(entityInformation.isNew(sample), is(true));

        sample.setId(5L);
        Assert.assertThat(entityInformation.isNew(sample), is(false));
    }
}
