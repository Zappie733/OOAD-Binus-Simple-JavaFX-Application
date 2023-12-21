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
import main.SessionManager;
import model.User;
import view.admin.AdminHome;
import view.cashier.CashierHome;
import view.chef.ChefHome;
import view.customer.CustomerHome;
import view.waiter.WaiterHome;

public class Login {
	private UserController userController;
	
	public Login() {
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
		
		Label loginLabel = new Label("Login Form");
		loginLabel.setTextFill(Color.WHITE);
		loginLabel.setStyle("-fx-font-size: 30px;");
		
		Label pesanLabel = new Label("");
		pesanLabel.setTextFill(Color.YELLOW);
		pesanLabel.setStyle("-fx-font-size: 20px;");
		
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");
        emailTextField.setMaxWidth(400);
        emailTextField.setStyle("-fx-font-size: 15px");
        
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPromptText("Password");
        passwordTextField.setMaxWidth(400);
        passwordTextField.setStyle("-fx-font-size: 15px");
        
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-font-size: 15px; -fx-padding: 5px 15px");
        loginButton.setOnAction(e -> {
        	String userEmail = "";
        	String userPassword = "";
        	
        	if(!emailTextField.getText().isEmpty()) {
        		userEmail = emailTextField.getText();
        	} 
        	if(!passwordTextField.getText().isEmpty()) {
        		userPassword = passwordTextField.getText();
        	}
           
    		String pesan = userController.authenticateUser(userEmail, userPassword);
    		pesanLabel.setText(pesan);
    		
    		User user = SessionManager.getCurrentUser();
    		
    		BaseView home;
    		
    		if(user.getUserRole() != null) {
    			if(user.getUserRole().equals("Admin")) {
        			home = new AdminHome();
        			home.display(mysticGrillsApp);
        		} else if(user.getUserRole().equals("Customer")) {
        			home = new CustomerHome();
        			home.display(mysticGrillsApp);
        		} else if(user.getUserRole().equals("Chef")) {
        			home = new ChefHome();
        			home.display(mysticGrillsApp);
        		} else if(user.getUserRole().equals("Waiter")) {
        			home = new WaiterHome();
        			home.display(mysticGrillsApp);
        		} else if(user.getUserRole().equals("Cashier")) {
        			home = new CashierHome();
        			home.display(mysticGrillsApp);
        		}
    		}  
        });
		
        HBox registerBox = new HBox();
        
        Label registerLabel = new Label("Do not have an account? ");
        registerLabel.setTextFill(Color.WHITE);
        registerLabel.setStyle("-fx-font-size: 15px;");
        
        Label registerLabelDirect = new Label("Register Here");
        registerLabelDirect.setTextFill(Color.BLUE);
        registerLabelDirect.setStyle("-fx-font-size: 15px;");
        registerLabelDirect.setOnMouseClicked(e ->{
        	Register register = new Register();
        	register.display(mysticGrillsApp);
        });
        
        registerBox.getChildren().addAll(registerLabel, registerLabelDirect);
        registerBox.setAlignment(Pos.CENTER);
        
        content.getChildren().addAll(welcomeLabel, loginLabel, pesanLabel, emailTextField, passwordTextField, loginButton, registerBox);
        
        Scene scene = new Scene(content);
        mysticGrillsApp.setScene(scene);
        
        mysticGrillsApp.setTitle("Mystic Grills - Login Page");
        mysticGrillsApp.show();
	}
}
