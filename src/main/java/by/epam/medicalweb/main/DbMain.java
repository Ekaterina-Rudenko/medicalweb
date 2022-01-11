package by.epam.medicalweb.main;

import by.epam.medicalweb.model.entity.MedicalService;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbMain {
/*    public static void main(String[] args) {
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

        try (
            Connection connection = DriverManager.getConnection(url, prop);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            String sql = "SELECT service_id, service_name, service_price FROM services";
            ResultSet resultSet = statement.executeQuery(sql);
            List<MedicalService> services = new ArrayList<>();

            //insert
            resultSet.moveToInsertRow();
            resultSet.updateLong(1, 15);
            resultSet.updateString(2, "new service");
            resultSet.updateBigDecimal(3, new BigDecimal(22));
            resultSet.insertRow();
            resultSet.moveToCurrentRow();

            while(resultSet.next()){

                //update row
                long id = resultSet.getLong(1);
                if (id == 1){
                    resultSet.updateBigDecimal(3, new BigDecimal(56));
                    resultSet.updateRow();
                }
                String name = resultSet.getString(2);
                BigDecimal price = resultSet.getBigDecimal(3);
                services.add(new MedicalService(id, name,price));

            }
            if(!services.isEmpty()) {
                System.out.println(services);
            } else {
                System.out.println("Data was not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
