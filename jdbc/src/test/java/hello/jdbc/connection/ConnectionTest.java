package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    /**
     * DataSource 안 쓴 기존 방식
     */
    @Test
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection1={}", con1);
        log.info("connection2={}", con2); //서로 다른 커넥션
    }

    /**
     * DataSource "인터페이스" 를 통해서 커넥션을 가져옴 위와 거의 비슷
     * 객체를 생성하는 시점에 파라미터를 넣는 것에서 차이가 있다 -> "설정과 사용의 분리"
     */
    @Test
    void dataSourceDriverManager() throws SQLException {
        // DriverManagerdataSource
        DataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        //커넥션 풀링
        HikariDataSource dataSource = new HikariDataSource(); //jdbc를 쓰면 import
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10); // 풀 사이즈 설정
        dataSource.setPoolName("MyPool");

        useDataSource(dataSource);
        Thread.sleep(1000);
        //커넥션을 생성하는 것은 다른 Thread 에서 생성

    }


    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        log.info("connection1={}", con1);
        log.info("connection2={}", con2);
    }
}
