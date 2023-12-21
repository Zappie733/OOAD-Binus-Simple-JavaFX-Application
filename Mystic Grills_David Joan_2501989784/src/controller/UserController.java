package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.SessionManager;
import model.User;
import model.dao.UserModel;

public class UserController {
    private UserModel userModel;

    public UserController() {
        userModel = new UserModel();
    }

    public String authenticateUser(String userEmail, String userPassword) {
    	if(userEmail.isEmpty()) {
			return "Email can not be empty";
		}
    	
    	if(userPassword.isEmpty()) {
			return  "Password can not be empty";
		}
    	
    	User user = userModel.authenticateUser(userEmail, userPassword);
    	if(user.getUserEmail() == null) {
    		return  "User not found";
    	}
    	
    	SessionManager.setCurrentUser(user);
    	return "";
    }

    public String createUser(String userRole, String userName, String userEmail, String userPassword, String userConfirmPassword) {
    	Boolean isEmailUnique = isEmailUnique(userEmail);
    	
    	if(userName.isEmpty()) {
			return "Name can not be empty";
		}
    	
    	if(userEmail.isEmpty()) {
			return  "Email can not be empty";
		}
    	
    	if (!isEmailUnique) {
			return "Email has already been used";
		}
    	
    	if (userPassword.isEmpty()) {
			return "Password can not be empty";
		}
    	
    	if (userPassword.length() < 6) {
			return "Password must be at least 6 characters long";
		}
    	
    	if (userConfirmPassword.isEmpty()) {
			return "Confirm Password can not be empty";
		}
    	
    	if (!userConfirmPassword.equals(userPassword)) {
			return "Confirm Password is not match with password";
		}
    	
    	Boolean isSuccess = userModel.createUser(userRole, userName, userEmail, userPassword);
    	if(isSuccess) {
    		return "Register User Success";
    	}
    	
    	return "Register User Failed";
    }
    
    public boolean isEmailUnique(String email) { 
        ArrayList<User> allUsers = userModel.getAllUsers();

        for (User user : allUsers) {
        	
            if (user.getUserEmail().equalsIgnoreCase(email)) {
                return false; //sudah ada
            }
        }
        return true; //unik
    }
    
    public ObservableList<User> getAllUsers() {
        return FXCollections.observableArrayList(userModel.getAllUsers());
    }
    
    public User getUserById(int userId){
    	return userModel.getUserById(userId);
    }
    
    public String updateUser(int userId, String userRole, String userName, String userEmail, String userPassword) {
    	User user = getUserById(userId);
    	if(userId != 0) {
    		if(userName.isEmpty()){
    			return "Name can not be empty";
    		}
    		
    		if(userEmail.isEmpty()) {
    			return "Email can not be empty";
    		}
    		
    		if(!userEmail.equalsIgnoreCase(user.getUserEmail())) {
    			if(!isEmailUnique(userEmail)) {
        			return "Email has already been used";
    			}
    		}
    		
    		if (userPassword.isEmpty()) {
    			return "Password can not be empty";
    		}
    		
    		if (userPassword.length() < 6) {
    			return "Password must be at least 6 characters long";
    		}
    		
    		if (userRole.isEmpty()) {
    			return "Role can not be emtpy";
    		}
    		
    		if (!userRole.equals("Admin") && !userRole.equals("Customer") && !userRole.equals("Chef") && !userRole.equals("Waiter") && !userRole.equals("Cashier")) {
    			return "Role Invalid (only accept 'Admin', 'Customer', 'Chef', 'Waiter', 'Cashier')";
    		}
    		
    		Boolean isSuccess = userModel.updateUser(userId, userRole, userName, userEmail, userPassword);
        	if(isSuccess) {
        		return "Update User Success";
        	}
        	
        	return "Update User Failed";	
    	}
   
    	return "Nothing to update";
    }    
    
    public String deleteUser (int userId) {
    	if(userId != 0) {
        	Boolean isSuccess = userModel.deleteUser(userId);
        	if(isSuccess) {
        		return "Delete User Success";
        	}
        	return "Delete User Failed";	
    	}
    	return "Nothing to delete";
    }
    

	public void refreshTableData(TableView<User> tableView, TextField userIdTextField, TextField roleTextField, TextField nameTextField, TextField emailTextField, TextField passwordTextField) {
	    ObservableList<User> updatedUsers = getAllUsers();
	    tableView.setItems(updatedUsers);
	    
	    userIdTextField.setText("");
	    roleTextField.setText("");
	    nameTextField.setText("");
	    emailTextField.setText("");
	    passwordTextField.setText("");
	}



}
