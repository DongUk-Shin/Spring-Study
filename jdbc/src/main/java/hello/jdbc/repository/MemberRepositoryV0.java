package hello.jdbc.repository;

import hello.jdbc.connection.DbConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * JDBC - DriverManager 사용
 */
@Slf4j
public class MemberRepositoryV0 {
    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values (?, ?)";

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(sql);
            //타입 정보까지 넘겨줌, 파라미터 바인딩 -> SQL Injection 방지
            preparedStatement.setString(1, member.getMemberId());
            preparedStatement.setInt(2, member.getMoney());
            preparedStatement.executeUpdate(); //실행
            return member;
        } catch (SQLException e) {
            log.error("db error");
            throw e;
        } finally { //역순으로 closec
            close(con, preparedStatement, null);
        }

    }

    private void close(Connection con, Statement statement, ResultSet resultSet) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.info("error");
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.info("error");
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.info(("error"));
            }
        }
    }

    private Connection getConnection() {
        return DbConnectionUtil.getConnection();
    }
}
