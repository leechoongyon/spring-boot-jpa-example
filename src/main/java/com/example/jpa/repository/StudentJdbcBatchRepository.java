package com.example.jpa.repository;

import com.example.jpa.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class StudentJdbcBatchRepository {

    private final int BATCH_SIZE = 1000;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void batchInsert(List<Student> students) {
        StopWatch timer = new StopWatch();
        String sql = "INSERT INTO `STUDENT` (student_id, name, age, address)"
                + " VALUES(?,?,?,?)";
        timer.start();
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Student student = students.get(i);
                ps.setLong(1, student.getId());
                ps.setString(2, student.getName());
                ps.setInt(3, student.getAge());
                ps.setString(4, student.getAddress());
            }
            @Override
            public int getBatchSize() {
                return BATCH_SIZE;
            }
        });
        timer.stop();
        log.info("elapsedTime : {}", timer.getTotalTimeSeconds());
    }
}
