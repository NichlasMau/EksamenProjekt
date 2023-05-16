package com.example.alphasolutionseksamen.repository;
import com.example.alphasolutionseksamen.model.Subproject;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Repository
public class SubprojectRepository {

    DBConnector connector;

    public List<Subproject> getSubproject() {
        List<Subproject> subprojects = new ArrayList<>();
        try (Connection con = connector.getConnection()) {
            String SQL = "SELECT * FROM subprojects;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int id = rs.getInt("subproject_id");
                int project_id = rs.getInt("project_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                LocalDateTime startDate = rs.getTimestamp("start_date").toLocalDateTime();
                LocalDateTime endDate = rs.getTimestamp("end_date").toLocalDateTime();
                String status = rs.getString("status");
                Double budget = rs.getDouble("budget");
                subprojects.add(new Subproject(id, project_id, name, description, status, budget, startDate, endDate));
            }
            return subprojects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Subproject> getUserSubprojects(int givenUserId, int givenProjectId) {
        List<Subproject> subprojects = new ArrayList<>();
        try (Connection con = connector.getConnection()) {
            String SQL = "SELECT * FROM subprojects sp JOIN projects p ON sp.project_id = p.project_id JOIN project_members pm ON p.project_id = pm.project_id WHERE pm.user_id = ? AND sp.project_id = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, givenUserId);
            pstmt.setInt(2, givenProjectId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("subproject_id");
                int project_id = rs.getInt("project_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                LocalDateTime startDate = rs.getTimestamp("start_date").toLocalDateTime();
                LocalDateTime endDate = rs.getTimestamp("end_date").toLocalDateTime();
                String status = rs.getString("status");
                Double budget = rs.getDouble("budget");
                subprojects.add(new Subproject(id, project_id, name, description, status, budget, startDate, endDate));
            }
            return subprojects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createSubproject(Subproject subprojects) {
        try (Connection con = connector.getConnection()) {
            String SQL = "INSERT INTO `subprojects` (`project_id`, `name`, `description`, `status`,`budget`,`start_date`,`end_date` ) VALUES (?, ?, ?, ?,?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, subprojects.getProject_id());
            pstmt.setString(2, subprojects.getName());
            pstmt.setString(3, subprojects.getDescription());
            pstmt.setString(4, subprojects.getStatus());
            pstmt.setDouble(5, subprojects.getBudget());
            pstmt.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
            pstmt.setTimestamp(7, Timestamp.valueOf(subprojects.getEndDate()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSubproject(Subproject subproject) {
        try (Connection con = connector.getConnection()) {
            String SQL = "UPDATE subprojects SET name = ?, description = ?, status = ?, budget = ?, start_date = ?, end_date = ? WHERE subproject_id = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, subproject.getName());
            pstmt.setString(2, subproject.getDescription());
            pstmt.setString(3, subproject.getStatus());
            pstmt.setDouble(4, subproject.getBudget());
            pstmt.setTimestamp(5, Timestamp.valueOf(subproject.getStartDate()));
            pstmt.setTimestamp(6, Timestamp.valueOf(subproject.getEndDate()));
            pstmt.setInt(7, subproject.getSubproject_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSubproject(int subprojectID) {
        try (Connection con = connector.getConnection()) {
            String SQL = "DELETE FROM subprojects WHERE subproject_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, subprojectID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
