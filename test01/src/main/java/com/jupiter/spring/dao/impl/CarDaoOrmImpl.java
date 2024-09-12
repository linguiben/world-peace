package com.jupiter.spring.dao.impl;

import com.jupiter.spring.dao.CarDao;
import com.jupiter.domain.Car;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-06-18 13:14
 */
@Slf4j
public class CarDaoOrmImpl implements CarDao {
    private final static String url = "jdbc:postgresql://localhost:25432/postgres";
    private final static String username = "postgres";
    private final static String passwd = "";
    private static Connection conn = null;
    private PreparedStatement stmt = null;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.error("cannot load jdbc driver", e);
        }
        try {
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException e) {
            log.error("get db connect error", e);
        }
    }

    @Override
    public int save(Car car) {
        String sql = "insert into t_car(cname,color,price) values(?, ?, ?)";
        stmt = null;
        int rows = 0;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, car.getCname());
            stmt.setString(2, car.getColor());
            stmt.setDouble(3, car.getPrice());
            rows = stmt.executeUpdate();
            log.info("insert rows:{}",rows);
        } catch (SQLException e) {
            log.error("Exec SQL Error", e);
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return rows;
    }

    @Override
    public int delete(Car car) {
        String sql = "delete from t_car where cname = ? and color = ?";
        stmt = null;
        int rows = 0;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, car.getCname());
            stmt.setString(2, car.getColor());
            rows = stmt.executeUpdate();
            log.info("delete rows:{}",rows);
        } catch (SQLException e) {
            log.error("Exec SQL Error", e);
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return rows;
    }
}
