package com.example.alphasolutionseksamen.repository;


import com.example.alphasolutionseksamen.DTO.Project_members;
import com.example.alphasolutionseksamen.enums.customerEnum;
import com.example.alphasolutionseksamen.model.Customer;
import org.springframework.stereotype.Repository;


import java.sql.*;




@Repository
public class CustomerRepository {
    DBConnector connector;


    public customerEnum assignCustomerProject(String givenEmail, int projectId) {
        int customerId = getCustomerIdByEmail(givenEmail);
        if (customerId == -1) {
            return customerEnum.CUSTOMER_NOT_FOUND;
        }
        try (Connection con = connector.getConnection()){
            String SQl = "INSERT INTO project_customers (customer_id, project_id) VALUES (?, ?)";
            PreparedStatement psmt = con.prepareStatement(SQl, Statement.RETURN_GENERATED_KEYS);
            psmt.setInt(1, customerId);
            psmt.setInt(2, projectId);
            psmt.executeUpdate();
            return customerEnum.SUCCESS;
        } catch (SQLException e) {
            return customerEnum.ERROR;
        }
    }


    private int getCustomerIdByEmail(String email) {
        String query = "SELECT customer_id FROM customers WHERE email = ?";
        int customerId = -1;


        try (Connection con = connector.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {


            statement.setString(1, email);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    customerId = resultSet.getInt("customer_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return customerId;
    }
}



