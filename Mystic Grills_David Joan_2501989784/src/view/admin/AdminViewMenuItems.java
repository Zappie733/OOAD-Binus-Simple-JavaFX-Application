package view.admin;

import controller.MenuItemController;
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
import model.MenuItem;
import view.BaseView;

public class AdminViewMenuItems extends BaseView{

    private MenuItemController menuItemController;
    
    public AdminViewMenuItems() {
    	menuItemController = new MenuItemController();
    }
    
	@SuppressWarnings("unchecked")
	@Override
	protected VBox content(Stage mysticGrillsApp) {
		mysticGrillsApp.setTitle("Mystic Grills - Admin Menu Items Page");
		VBox content = new VBox();
		content.setPadding(new Insets(30));
		content.setAlignment(Pos.CENTER);
		
		TableView<MenuItem> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
        tableView.setPlaceholder(new Label("No data available"));
        tableView.setMinHeight(170);
        tableView.setMaxHeight(170);
        VBox.setMargin(tableView, new Insets(0, 0, 20, 0));
        
        TableColumn<MenuItem, Integer> menuIdColumn = new TableColumn<>("Menu Item ID");
        TableColumn<MenuItem, String> menuNameColumn = new TableColumn<>("Menu Item Name");
        TableColumn<MenuItem, String> menuDescriptionColumn = new TableColumn<>("Menu Item Description");
        TableColumn<MenuItem, Double> menuPriceColumn = new TableColumn<>("Menu Item Price");
        
        menuIdColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemId" ));
        menuNameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName" ));
        menuDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription" ));
        menuPriceColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice" ));
        
        tableView.getColumns().addAll(menuIdColumn, menuNameColumn, menuDescriptionColumn, menuPriceColumn);
        
        ObservableList<MenuItem> menuItems = menuItemController.getAllMenuItems();
        tableView.setItems(menuItems);
        
        
        Label titleLabel = new Label("Action Menu Item Form");
        titleLabel.setStyle("-fx-font-size: 18px");
        titleLabel.setTextFill(Color.WHITE);
        
        Label pesanLabel = new Label("");
        pesanLabel.setStyle("-fx-font-size: 15px");
        pesanLabel.setTextFill(Color.YELLOW);
        
		TextField menuItemIdTextField = new TextField();
		menuItemIdTextField.setPromptText("Id");
		menuItemIdTextField.setMaxWidth(400);
		menuItemIdTextField.setStyle("-fx-font-size: 15px");
		menuItemIdTextField.setDisable(true);
		
		TextField menuItemNameTextField = new TextField();
		menuItemNameTextField.setPromptText("Name");
		menuItemNameTextField.setMaxWidth(400);
		menuItemNameTextField.setStyle("-fx-font-size: 15px");
		
		TextField menuItemDescriptionTextField = new TextField();
		menuItemDescriptionTextField.setPromptText("Description");
		menuItemDescriptionTextField.setMaxWidth(400);
		menuItemDescriptionTextField.setStyle("-fx-font-size: 15px");
        
		TextField menuItemPriceTextField = new TextField();
		menuItemPriceTextField.setPromptText("Price");
		menuItemPriceTextField.setMaxWidth(400);
		menuItemPriceTextField.setStyle("-fx-font-size: 15px");
        
		
		HBox buttonBox = new HBox();
		
		Button insertButton = new Button("Insert");
		insertButton.setStyle("-fx-font-size: 15px; -fx-padding: 5px 15px");
		insertButton.setOnAction(e -> {
            String menuItemName = "";
            String menuItemDescription = "";
            Double menuItemPrice = 0.0;
            
            if(!menuItemNameTextField.getText().isEmpty()){
            	menuItemName = menuItemNameTextField.getText();
            }
            if(!menuItemDescriptionTextField.getText().isEmpty()){
            	menuItemDescription = menuItemDescriptionTextField.getText();
            }
            if(!menuItemPriceTextField.getText().isEmpty()){
            	try {
            		menuItemPrice = Double.parseDouble(menuItemPriceTextField.getText());
            	} catch(NumberFormatException e1) {
            		menuItemPrice = null;
            	}
            }
            
            String pesan = menuItemController.createMenuItem(menuItemName, menuItemDescription, menuItemPrice);
            pesanLabel.setText(pesan);
            
            if(pesan.contains("Success")) {
            	menuItemController.refreshTableData(tableView, menuItemIdTextField, menuItemNameTextField, menuItemDescriptionTextField, menuItemPriceTextField);
            	pesanLabel.setTextFill(Color.LIGHTGREEN);
            } else {
            	pesanLabel.setTextFill(Color.YELLOW);
            }
        });
        
        Button updateButton = new Button("Update");
        updateButton.setStyle("-fx-font-size: 15px; -fx-padding: 5px 15px");
        updateButton.setOnAction(e -> {
        	int menuItemId = 0;
        	String menuItemName = "";
            String menuItemDescription = "";
            Double menuItemPrice = 0.0;
            
            if(!menuItemIdTextField.getText().isEmpty()){
            	menuItemId = Integer.parseInt(menuItemIdTextField.getText());
            }
            if(!menuItemNameTextField.getText().isEmpty()){
            	menuItemName = menuItemNameTextField.getText();
            }
            if(!menuItemDescriptionTextField.getText().isEmpty()){
            	menuItemDescription = menuItemDescriptionTextField.getText();
            }
            if(!menuItemPriceTextField.getText().isEmpty()){
            	try {
            		menuItemPrice = Double.parseDouble(menuItemPriceTextField.getText());
            	} catch(NumberFormatException e1) {
            		menuItemPrice = null;
            	}
            }
            
            String pesan = menuItemController.updateMenuItem(menuItemId, menuItemName, menuItemDescription, menuItemPrice);
            pesanLabel.setText(pesan);
            
            if(pesan.contains("Success")) {
            	menuItemController.refreshTableData(tableView, menuItemIdTextField, menuItemNameTextField, menuItemDescriptionTextField, menuItemPriceTextField);
            	pesanLabel.setTextFill(Color.LIGHTGREEN);
            } else {
            	pesanLabel.setTextFill(Color.YELLOW);
            }
        });
        
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-font-size: 15px; -fx-padding: 5px 15px");
        deleteButton.setOnAction(e -> {
        	int menuItemId = 0;
        	
        	if(!menuItemIdTextField.getText().isEmpty()){
            	menuItemId = Integer.parseInt(menuItemIdTextField.getText());
            }
        	
        	String pesan = menuItemController.deleteMenuItem(menuItemId);
            pesanLabel.setText(pesan);
            
            if(pesan.contains("Success")) {
            	menuItemController.refreshTableData(tableView, menuItemIdTextField, menuItemNameTextField, menuItemDescriptionTextField, menuItemPriceTextField);
            	pesanLabel.setTextFill(Color.LIGHTGREEN);
            } else {
            	pesanLabel.setTextFill(Color.YELLOW);
            }
        });
        
        VBox.setMargin(buttonBox, new Insets(15, 0, 0, 0));
        HBox.setMargin(insertButton, new Insets(0, 10, 0, 0));
        HBox.setMargin(updateButton, new Insets(0, 10, 0, 0));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(insertButton, updateButton, deleteButton);
        
        tableView.setOnMouseClicked(event -> {
            if (!tableView.getSelectionModel().isEmpty()) {
                MenuItem selectedMenuItem = tableView.getSelectionModel().getSelectedItem();

                menuItemIdTextField.setText(String.valueOf(selectedMenuItem.getMenuItemId()));
                menuItemNameTextField.setText(selectedMenuItem.getMenuItemName());
                menuItemDescriptionTextField.setText(selectedMenuItem.getMenuItemDescription());
                menuItemPriceTextField.setText(String.valueOf(selectedMenuItem.getMenuItemPrice()));
                
                pesanLabel.setText("");
            }
        });
        
        content.getChildren().addAll(tableView, titleLabel, pesanLabel, menuItemIdTextField, menuItemNameTextField, menuItemDescriptionTextField, menuItemPriceTextField, buttonBox);
        
		return content;
		
	}
}

