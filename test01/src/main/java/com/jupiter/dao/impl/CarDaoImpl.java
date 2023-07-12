package com.jupiter.dao.impl;

import com.jupiter.dao.CarDao;
import com.jupiter.domain.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-06-18 12:05
 */
public class CarDaoImpl implements CarDao {
    @Override
    public int save(Car car) {
        // jdbc 六部曲
        // 1.导入驱动, 通过mvn
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        int execute;
        try {
            // 2.加载驱动
            Class.forName("org.postgresql.Driver");

            // 3.获取连接
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:25432/postgres"
                    , "postgres", "");

            // 4.创建数据集
            String sql = "insert into t_car(cname,color,price) values(?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, car.getCname());
            preparedStatement.setString(2, car.getColor());
            preparedStatement.setDouble(3, car.getPrice());

            // 5.执行SQL
            execute = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 6.关闭连接
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return execute;
    }

    /**
     * @desc delete a car
     * @param car
     * @return
     */
    @Override
    public int delete(Car car) {
        String sql = "delete from t_car where cname = ? and color = ?";
        String url = "jdbc:postgresql://localhost:25432/postgres";
        String userName = "postgres";
        String passwd = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        // jdbc六部曲

        // 1.导入驱动jar

        // 2.加载驱动
        try {
            Class.forName("org.postgresql.Driver");

        // 3.获取连接
        conn = DriverManager.getConnection(url, userName, passwd);

        // 4.创建stmt
        stmt = conn.prepareStatement(sql);
        stmt.setString(1,car.getCname());
        stmt.setString(2,car.getColor());

        // 5.执行SQL
        int i = stmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
        // 6.关闭连接
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return 0;
    }
}
