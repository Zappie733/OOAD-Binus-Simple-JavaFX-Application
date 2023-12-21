package view.admin;

import controller.UserController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.User;
import view.BaseView;

public class AdminViewUsers extends BaseView {

    private UserController userController;
    
    public AdminViewUsers() {
        userController = new UserController();
    }

	@SuppressWarnings("unchecked") //suggest dari java buat ilangin warning tableView
	@Override
	protected VBox content(Stage mysticGrillsApp) {
		mysticGrillsApp.setTitle("Mystic Grills - Admin View Users Page");
		VBox content = new VBox();
		content.setPadding(new Insets(30));
		content.setAlignment(Pos.CENTER);
		
		
        TableView<User> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Untuk mengatur jumlah kolom sesuai yang di set aja
        tableView.setPlaceholder(new Label("No data available")); // Menampilkan teks ketika tabel kosong
        tableView.setMinHeight(170);
        tableView.setMaxHeight(170);
        VBox.setMargin(tableView, new Insets(0, 0, 20, 0));
        
        TableColumn<User, Integer> userIdColumn = new TableColumn<>("User ID");
        TableColumn<User, String> userRoleColumn = new TableColumn<>("User Role");
        TableColumn<User, String> userNameColumn = new TableColumn<>("User Name");
        TableColumn<User, String> userEmailColumn = new TableColumn<>("User Email");
        TableColumn<User, String> userPasswordColumn = new TableColumn<>("User Password");
        
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId" ));
        userRoleColumn.setCellValueFactory(new PropertyValueFactory<>("userRole" ));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName" ));
        userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail" ));
        userPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("userPassword" ));
        
        tableView.getColumns().addAll(userIdColumn, userRoleColumn, userNameColumn, userEmailColumn, userPasswordColumn);
        
        ObservableList<User> users = userController.getAllUsers();
        tableView.setItems(users);
        
        
        Label titleLabel = new Label("Detail User Form");
        titleLabel.setStyle("-fx-font-size: 18px");
        titleLabel.setTextFill(Color.WHITE);
        
        Label pesanLabel = new Label("");
        pesanLabel.setStyle("-fx-font-size: 15px");
        pesanLabel.setTextFill(Color.YELLOW);
        
		TextField userIdTextField = new TextField();
		userIdTextField.setPromptText("Id");
		userIdTextField.setMaxWidth(400);
		userIdTextField.setStyle("-fx-font-size: 15px");
		userIdTextField.setDisable(true);
		
		TextField roleTextField = new TextField();
		roleTextField.setPromptText("Role");
		roleTextField.setMaxWidth(400);
		roleTextField.setStyle("-fx-font-size: 15px");
		
		TextField nameTextField = new TextField();
        nameTextField.setPromptText("Name");
        nameTextField.setMaxWidth(400);
        nameTextField.setStyle("-fx-font-size: 15px");
        
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");
        emailTextField.setMaxWidth(400);
        emailTextField.setStyle("-fx-font-size: 15px");
        
        TextField passwordTextField = new TextField();
        passwordTextField.setPromptText("Password");
        passwordTextField.setMaxWidth(400);
        passwordTextField.setStyle("-fx-font-size: 15px");
        
        
        HBox buttonBox = new HBox();
        
        Button updateButton = new Button("Update");
        updateButton.setStyle("-fx-font-size: 15px; -fx-padding: 5px 15px");
        updateButton.setOnAction(e -> {
            int userId = 0;
            String userRole = "";
            String userName = "";
            String userEmail = "";
            String userPassword = "";
            
            if(!userIdTextField.getText().isEmpty()){
            	userId = Integer.parseInt(userIdTextField.getText());
            }
            if(!roleTextField.getText().isEmpty()){
            	userRole = roleTextField.getText();
            }
            if(!nameTextField.getText().isEmpty()){
            	userName = nameTextField.getText();
            }
            if(!emailTextField.getText().isEmpty()){
            	userEmail = emailTextField.getText();
            }
            if(!passwordTextField.getText().isEmpty()){
            	userPassword = passwordTextField.getText();
            }
            
            String pesan = userController.updateUser(userId, userRole, userName, userEmail, userPassword);
            pesanLabel.setText(pesan);
            
            if(pesan.contains("Success")) {
            	userController.refreshTableData(tableView, userIdTextField, roleTextField, nameTextField, emailTextField, passwordTextField);
            	pesanLabel.setTextFill(Color.LIGHTGREEN);
            } else {
            	pesanLabel.setTextFill(Color.YELLOW);
            }
        });
        
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-font-size: 15px; -fx-padding: 5px 15px");
        deleteButton.setOnAction(e -> {
        	int userId = 0;
        	
            if(!userIdTextField.getText().isEmpty()){
            	userId = Integer.parseInt(userIdTextField.getText());
            }
            
            String pesan = userController.deleteUser(userId);
            pesanLabel.setText(pesan);
            
            if(pesan.contains("Success")) {
            	userController.refreshTableData(tableView, userIdTextField, roleTextField, nameTextField, emailTextField, passwordTextField);
            	pesanLabel.setTextFill(Color.LIGHTGREEN);
            } else {
            	pesanLabel.setTextFill(Color.YELLOW);
            }
        });
        
        VBox.setMargin(buttonBox, new Insets(15, 0, 0, 0));
        HBox.setMargin(updateButton, new Insets(0, 10, 0, 0));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(updateButton, deleteButton);
        
        tableView.setOnMouseClicked(event -> {
            if (!tableView.getSelectionModel().isEmpty()) {
                User selectedUser = tableView.getSelectionModel().getSelectedItem();

                userIdTextField.setText(String.valueOf(selectedUser.getUserId()));
                roleTextField.setText(selectedUser.getUserRole());
                nameTextField.setText(selectedUser.getUserName());
                emailTextField.setText(selectedUser.getUserEmail());
                passwordTextField.setText(selectedUser.getUserPassword());
                
                pesanLabel.setText("");
            }
        });
        
        content.getChildren().addAll(tableView, titleLabel, pesanLabel, userIdTextField, roleTextField, nameTextField, emailTextField, passwordTextField, buttonBox);
        
        return content;
	}

}
