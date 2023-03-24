package com.example.cslink.user.model.dataccess.persistence;

import com.example.cslink.user.model.datatypes.enums.Role;
import com.example.cslink.user.model.entity.UserProfile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@Transactional
public class UserProfilePersistence {
    @PersistenceContext
    private EntityManager entityManager;

    final static String sql="{CALL get_users_by_role(?)}";
    final static String cosmetologist=String.valueOf(Role.COSMETOLOGIST);

    public UserProfile getExistingUser(UserProfile userProfile) {

        DataSource dataSource=new DriverManagerDataSource(
                "jdbc:mysql://localhost:3306/database_spring_boot_project",
                "root", "password123!");
        UserProfile userProfileResult=new UserProfile();
        try (Connection connection=dataSource.getConnection();
             CallableStatement statement=connection.prepareCall(sql)) {

            statement.setString(1, "1");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()) {
                String ids=String.valueOf(resultSet.getInt("id"));
                userProfileResult.setName(resultSet.getString("name"));
                String email=resultSet.getString("email");
                // process user data...
            }
            return userProfileResult;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
