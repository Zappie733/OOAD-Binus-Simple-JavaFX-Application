package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.User;

public class UserModel {
	DatabaseConnector dbConnector  = DatabaseConnector.getConnection();

    public User authenticateUser(String userEmail, String userPassword) {
    	User user = new User();
    	
    	try {
	        PreparedStatement preparedStatement = dbConnector.prepare("SELECT * FROM Users WHERE userEmail = ? AND userPassword = ?");
	        preparedStatement.setString(1, userEmail);
	        preparedStatement.setString(2, userPassword);
	        
	        ResultSet resultSet = dbConnector.executePreparedQueryS(preparedStatement);
	
	       while (resultSet.next()) {
	           user.setUserId(resultSet.getInt("userId"));
	           user.setUserRole(resultSet.getString("userRole"));
	           user.setUserName(resultSet.getString("userName"));
	           user.setUserEmail(resultSet.getString("userEmail"));
	           user.setUserPassword(resultSet.getString("userPassword"));
	       }
	       
	       return user;
       } catch (SQLException e) {
           e.printStackTrace();
       }
    	
    	return null;
    }
    
    public Boolean createUser(String userRole, String userName, String userEmail, String userPassword) {
    	Boolean isSuccess = false;
    	try {
	        PreparedStatement preparedStatement = dbConnector.prepare("INSERT INTO users (userRole, userName, userEmail, userPassword) VALUES (?, ?, ?, ?)");
	        preparedStatement.setString(1, userRole);
	        preparedStatement.setString(2, userName);
	        preparedStatement.setString(3, userEmail);
	        preparedStatement.setString(4, userPassword);
	        
	        dbConnector.executePreparedQueryIUD(preparedStatement);
	        isSuccess = true;
       } catch (SQLException e) {
           e.printStackTrace();
       }
    	
    	return isSuccess;
    }
    
    public ArrayList<User> getAllUsers() {
    	ArrayList<User> users = new ArrayList<>();

        try {
             PreparedStatement preparedStatement = dbConnector.prepare("SELECT * FROM users");
             ResultSet resultSet = dbConnector.executePreparedQueryS(preparedStatement);

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUserRole(resultSet.getString("userRole"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserEmail(resultSet.getString("userEmail"));
                user.setUserPassword(resultSet.getString("userPassword"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return users;
    }
    
    public User getUserById(int userId) {
    	User user = new User();
    	
    	try {
            PreparedStatement preparedStatement = dbConnector.prepare("SELECT * FROM Users WHERE userId = ?");
            preparedStatement.setInt(1, userId);
            
            ResultSet resultSet = dbConnector.executePreparedQueryS(preparedStatement);
            
            if (resultSet.next()) {
                user.setUserId(resultSet.getInt("userId"));
                user.setUserRole(resultSet.getString("userRole"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserEmail(resultSet.getString("userEmail"));
                user.setUserPassword(resultSet.getString("userPassword"));
            }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       
       return user;
    }

    public Boolean updateUser(int userId, String userRole, String userName, String userEmail, String userPassword) {
    	Boolean isSuccess = false;
    	try {
    		
	        PreparedStatement preparedStatement = dbConnector.prepare("UPDATE users SET userRole = ?, userName = ?, userEmail = ?, userPassword = ? WHERE userId = ?");
	        preparedStatement.setString(1, userRole);
	        preparedStatement.setString(2, userName);
	        preparedStatement.setString(3, userEmail);	        
	        preparedStatement.setString(4, userPassword);
	        preparedStatement.setInt(5, userId);
	        
	        dbConnector.executePreparedQueryIUD(preparedStatement);
	        isSuccess = true;
       } catch (SQLException e) {
           e.printStackTrace();
       }
    	
    	return isSuccess;
    }
    
    public Boolean deleteUser(int userId) {
    	Boolean isSuccess = false;
    	try {
	        PreparedStatement preparedStatement = dbConnector.prepare("DELETE FROM users WHERE userId = ?");
	        preparedStatement.setInt(1, userId);
	        
	        dbConnector.executePreparedQueryIUD(preparedStatement);
	        isSuccess = true;
       } catch (SQLException e) {
           e.printStackTrace();
       }
    	
    	return isSuccess;
    }
    
}
