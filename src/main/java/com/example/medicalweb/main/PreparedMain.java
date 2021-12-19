package com.example.medicalweb.main;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Properties;

public class PreparedMain {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/medical_centre";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "Rfnzrfnz7830448-");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");

      /*  try (
                Connection connection = DriverManager.getConnection(url, prop)){
            String sql = "INSERT INTO services (service_id, service_name, service_price) values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, 16);
            preparedStatement.setString(2, "third service");
            preparedStatement.setBigDecimal(3, new BigDecimal(21));
            int rowsUpdate = preparedStatement.executeUpdate();
            System.out.println(rowsUpdate);
        }catch (SQLException e){
            e.printStackTrace();
        }*/

        try (
                Connection connection = DriverManager.getConnection(url, prop)) {
            String sql = "INSERT INTO services (service_name, service_price) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "fifth service");
            preparedStatement.setBigDecimal(2, new BigDecimal(21));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                long key = resultSet.getLong(1);
                System.out.println(key);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
