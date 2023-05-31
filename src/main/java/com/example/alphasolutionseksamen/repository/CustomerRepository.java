package com.example.alphasolutionseksamen.repository;


import com.example.alphasolutionseksamen.enums.customerEnum;
import com.example.alphasolutionseksamen.model.Customer;
import org.springframework.stereotype.Repository;


import java.sql.*;


@Repository
public class CustomerRepository {
    DBConnector connector;


    public String createCustomer(Customer newCustomer) {
        try (Connection con = connector.getConnection()) {
            String SQL = "INSERT INTO customers (name, email, password) values (?,?,?);";
            PreparedStatement psmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, newCustomer.getName());
            psmt.setString(2, newCustomer.getEmail());
            psmt.setString(3, newCustomer.getPassword());
            psmt.executeUpdate();
            return newCustomer.getEmail();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Customer getCustomerEmail(String checkMail) {
        Customer customer = null;
        try (Connection con = connector.getConnection()) {
            String SQL = "SELECT * FROM customers WHERE email = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, checkMail);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("customer_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                customer = new Customer(id, name, email, password);
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public customerEnum assignCustomerProject(String givenEmail, int projectId) {
        int customerId = getCustomerIdByEmail(givenEmail);
        if (customerId == -1) {
            return customerEnum.CUSTOMER_NOT_FOUND;
        }
        try (Connection con = connector.getConnection()) {
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


        try (Connection con = connector.getConnection(); PreparedStatement statement = con.prepareStatement(query)) {


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



