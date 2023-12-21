package view;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Register {
	private UserController userController;
	
	public Register() {
		userController = new UserController();
	}
	
	public void display(Stage mysticGrillsApp) {
		VBox content = new VBox();
        content.setSpacing(10);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: maroon;");
        
        Label welcomeLabel = new Label("Welcome to Mystic Grills!");
		welcomeLabel.setTextFill(Color.YELLOW);
		welcomeLabel.setStyle("-fx-font-size: 30px;");
		
		Label registerLabel = new Label("Register Form");
		registerLabel.setTextFill(Color.WHITE);
		registerLabel.setStyle("-fx-font-size: 30px;");
		
		Label pesanLabel = new Label("");
		pesanLabel.setTextFill(Color.YELLOW);
		pesanLabel.setStyle("-fx-font-size: 20px;");
	
        
        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Name");
        nameTextField.setMaxWidth(400);
        nameTextField.setStyle("-fx-font-size: 15px");
        
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");
        emailTextField.setMaxWidth(400);
        emailTextField.setStyle("-fx-font-size: 15px");
        
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPromptText("Password");
        passwordTextField.setMaxWidth(400);
        passwordTextField.setStyle("-fx-font-size: 15px");
        
        PasswordField confirmPasswordTextField = new PasswordField();
        confirmPasswordTextField.setPromptText("Confirm Password");
        confirmPasswordTextField.setMaxWidth(400);
        confirmPasswordTextField.setStyle("-fx-font-size: 15px");
        
		TextField roleTextField = new TextField("Customer");
		roleTextField.setPromptText("Role");
		roleTextField.setMaxWidth(400);
		roleTextField.setStyle("-fx-font-size: 15px");
		roleTextField.setDisable(true);
        
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-font-size: 15px; -fx-padding: 5px 15px");
        registerButton.setOnAction(e -> {
            String userName = "";
            String userEmail = "";
            String userPassword = "";
            String userConfirmPassword = "";
            String userRole = "";
            
            if(!nameTextField.getText().isEmpty()){
            	userName = nameTextField.getText();
            }
            if(!roleTextField.getText().isEmpty()){
            	userEmail = emailTextField.getText();
            }
            if(!nameTextField.getText().isEmpty()){
            	userPassword = passwordTextField.getText();
            }
            if(!emailTextField.getText().isEmpty()){
            	userConfirmPassword = confirmPasswordTextField.getText();
            }
            if(!passwordTextField.getText().isEmpty()){
            	userRole = roleTextField.getText();
            }
            
            String pesan = userController.createUser(userRole, userName, userEmail, userPassword, userConfirmPassword);
            pesanLabel.setText(pesan);
            
            if(pesan.contains("Success")) {
            	pesanLabel.setTextFill(Color.LIGHTGREEN);
            } else {
            	pesanLabel.setTextFill(Color.YELLOW);
            }
        });

		HBox loginBox = new HBox();
		
        Label loginLabel = new Label("Already have an account? ");
        loginLabel.setTextFill(Color.WHITE);
        loginLabel.setStyle("-fx-font-size: 15px;");
        
        Label loginLabelDirect = new Label("Login Here");
        loginLabelDirect.setTextFill(Color.BLUE);
        loginLabelDirect.setStyle("-fx-font-size: 15px;");
        loginLabelDirect.setOnMouseClicked(e ->{
        	Login login = new Login();
        	login.display(mysticGrillsApp);
        });
        
        loginBox.getChildren().addAll(loginLabel, loginLabelDirect);
        loginBox.setAlignment(Pos.CENTER);
        
        content.getChildren().addAll(welcomeLabel, registerLabel, pesanLabel, nameTextField, emailTextField, passwordTextField, confirmPasswordTextField, roleTextField, registerButton, loginBox);
        Scene scene = new Scene(content);
        mysticGrillsApp.setScene(scene);
        
        mysticGrillsApp.setTitle("Mystic Grills - Register Page");
        mysticGrillsApp.show();
	}
}
