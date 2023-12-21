package view.customer;

import controller.MenuItemController;
import controller.OrderForTableViewController;
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
import model.OrderForTableView;
import view.BaseView;

public class CustomerViewMenuItems extends BaseView {

    private MenuItemController menuItemController;
    private OrderForTableViewController orderForTableViewController;
    
    public CustomerViewMenuItems() {
    	menuItemController = new MenuItemController();
    	orderForTableViewController = new OrderForTableViewController();
    }
    
	@SuppressWarnings("unchecked")
	@Override
	protected VBox content(Stage mysticGrillsApp) {
		mysticGrillsApp.setTitle("Mystic Grills - Customer Menu Items Page");
		VBox content = new VBox();
		content.setPadding(new Insets(30));
		content.setAlignment(Pos.CENTER);
		
		//table menu
		TableView<MenuItem> menuItemTableView = new TableView<>();
		menuItemTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
		menuItemTableView.setPlaceholder(new Label("No data available"));
		menuItemTableView.setMinHeight(170);
		menuItemTableView.setMaxHeight(170);
        VBox.setMargin(menuItemTableView, new Insets(0, 0, 20, 0));
        
        TableColumn<MenuItem, Integer> menuIdColumn = new TableColumn<>("Menu Item ID");
        TableColumn<MenuItem, String> menuNameColumn = new TableColumn<>("Menu Item Name");
        TableColumn<MenuItem, String> menuDescriptionColumn = new TableColumn<>("Menu Item Description");
        TableColumn<MenuItem, Double> menuPriceColumn = new TableColumn<>("Menu Item Price");
        
        menuIdColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemId" ));
        menuNameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName" ));
        menuDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription" ));
        menuPriceColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice" ));
        
        menuItemTableView.getColumns().addAll(menuIdColumn, menuNameColumn, menuDescriptionColumn, menuPriceColumn);
        
        ObservableList<MenuItem> menuItems = menuItemController.getAllMenuItems();
        menuItemTableView.setItems(menuItems);
        
        
        //order form
        Label titleLabel = new Label("Order Form");
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
		menuItemNameTextField.setDisable(true);
        
		TextField menuItemPriceTextField = new TextField();
		menuItemPriceTextField.setPromptText("Price");
		menuItemPriceTextField.setMaxWidth(400);
		menuItemPriceTextField.setStyle("-fx-font-size: 15px");
		menuItemPriceTextField.setDisable(true);
		
		TextField quantityTextField = new TextField();
		quantityTextField.setPromptText("Quantity");
		quantityTextField.setMaxWidth(400);
		quantityTextField.setStyle("-fx-font-size: 15px");
		
		
		HBox buttonBox = new HBox();
		VBox.setMargin(buttonBox, new Insets(15, 0, 0, 0));
		buttonBox.setAlignment(Pos.CENTER);
        
		Button insertButton = new Button("Add Item");
		insertButton.setStyle("-fx-font-size: 15px; -fx-padding: 5px 15px");
		HBox.setMargin(insertButton, new Insets(0, 10, 0, 0));
		
		Button removeButton = new Button("Remove Item");
		removeButton.setStyle("-fx-font-size: 15px; -fx-padding: 5px 15px");

		buttonBox.getChildren().addAll(insertButton, removeButton);
        
        
        //table order
		TableView<OrderForTableView> orderMenuItemTableView = new TableView<>();
		orderMenuItemTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
		orderMenuItemTableView.setPlaceholder(new Label("No data available"));
		orderMenuItemTableView.setMinHeight(170);
		orderMenuItemTableView.setMaxHeight(170);
        VBox.setMargin(orderMenuItemTableView, new Insets(40, 0, 20, 0));
        
        TableColumn<OrderForTableView, Integer> idColumn = new TableColumn<>("Menu Item ID");
        TableColumn<OrderForTableView, String> nameColumn = new TableColumn<>("Menu Item Name");
        TableColumn<OrderForTableView, Double> priceColumn = new TableColumn<>("Menu Item Price");
        TableColumn<OrderForTableView, Integer> quantityColumn = new TableColumn<>("Quantity");
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemId" ));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName" ));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice" ));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity" ));
        
        orderMenuItemTableView.getColumns().addAll(idColumn, nameColumn, priceColumn, quantityColumn);
        
        ObservableList<OrderForTableView> orderMenuItems = orderForTableViewController.getOrderData();
        orderMenuItemTableView.setItems(orderMenuItems);
        
        //total price
        HBox totalPriceBox = new HBox();
        totalPriceBox.setAlignment(Pos.CENTER_RIGHT);
		
        Label totalPriceLabel = new Label("");
        totalPriceLabel.setStyle("-fx-font-size: 18px");
        totalPriceLabel.setTextFill(Color.YELLOW);
        totalPriceLabel.setText(String.valueOf("Total Price: " + orderForTableViewController.countTotalPrice()));
        
        totalPriceBox.getChildren().add(totalPriceLabel);

        insertButton.setOnAction(e -> {
        	int menuItemId = 0;
        	String menuItemName = "";
            Double menuItemPrice = 0.0;
            int quantity = -999;
            
            if(!menuItemIdTextField.getText().isEmpty()){
            	menuItemId = Integer.parseInt(menuItemIdTextField.getText());
            }
            if(!menuItemNameTextField.getText().isEmpty()){
            	menuItemName = menuItemNameTextField.getText();
            }
            if(!menuItemPriceTextField.getText().isEmpty()){
            	menuItemPrice = Double.valueOf(menuItemPriceTextField.getText());
            }
            if(!quantityTextField.getText().isEmpty()){
            	try {
            		quantity = Integer.parseInt(quantityTextField.getText());
	            } catch(NumberFormatException e1) {
	            	quantity = -998;
	        	}
            }
            
            String pesan = orderForTableViewController.addItem(menuItemId, menuItemName, menuItemPrice, quantity);
            pesanLabel.setText(pesan);
            
            if(pesan.contains("Success")) {
            	orderForTableViewController.refreshOrderTableData(orderMenuItemTableView, menuItemIdTextField, menuItemNameTextField, menuItemPriceTextField, quantityTextField);
            	totalPriceLabel.setText(String.valueOf("Total Price: " + orderForTableViewController.countTotalPrice()));
            	pesanLabel.setTextFill(Color.LIGHTGREEN);
            } else {
            	pesanLabel.setTextFill(Color.YELLOW);
            }
	        
        });
        
        removeButton.setOnAction(e -> {
        	int menuItemId = 0;

            
            if(!menuItemIdTextField.getText().isEmpty()){
            	menuItemId = Integer.parseInt(menuItemIdTextField.getText());
            }
            
            String pesan = orderForTableViewController.deleteMenuItem(menuItemId);
            pesanLabel.setText(pesan);
            
            if(pesan.contains("Success")) {
            	orderForTableViewController.refreshOrderTableData(orderMenuItemTableView, menuItemIdTextField, menuItemNameTextField, menuItemPriceTextField, quantityTextField);
            	totalPriceLabel.setText(String.valueOf("Total Price: " + orderForTableViewController.countTotalPrice()));
            	pesanLabel.setTextFill(Color.LIGHTGREEN);
            	
            } else {
            	pesanLabel.setTextFill(Color.YELLOW);
            }
	        
        });
        
        
		menuItemTableView.setOnMouseClicked(event -> {
            if (!menuItemTableView.getSelectionModel().isEmpty()) {
                MenuItem selectedMenuItem = menuItemTableView.getSelectionModel().getSelectedItem();

                menuItemIdTextField.setText(String.valueOf(selectedMenuItem.getMenuItemId()));
                menuItemNameTextField.setText(selectedMenuItem.getMenuItemName());
                menuItemPriceTextField.setText(String.valueOf(selectedMenuItem.getMenuItemPrice()));
                
                pesanLabel.setText("");
            }
        });
		
		
        orderMenuItemTableView.setOnMouseClicked(event -> {
            if (!orderMenuItemTableView.getSelectionModel().isEmpty()) {
            	OrderForTableView selectedOrderItem = orderMenuItemTableView.getSelectionModel().getSelectedItem();

                menuItemIdTextField.setText(String.valueOf(selectedOrderItem.getMenuItemId()));
                menuItemNameTextField.setText(selectedOrderItem.getMenuItemName());
                menuItemPriceTextField.setText(String.valueOf(selectedOrderItem.getMenuItemPrice()));
                quantityTextField.setText(String.valueOf(selectedOrderItem.getQuantity()));
                pesanLabel.setText("");
            }
        });
		
        
        //submit & 
        HBox buttonBox2 = new HBox();
		VBox.setMargin(buttonBox2, new Insets(10, 0, 0, 0));
		buttonBox2.setAlignment(Pos.CENTER);
        
		Button submitOrder = new Button("Submit Order");
		submitOrder.setStyle("-fx-font-size: 15px; -fx-padding: 5px 15px");
		HBox.setMargin(submitOrder, new Insets(0, 10, 0, 0));
		submitOrder.setOnAction(e -> {
			String pesan = orderForTableViewController.submitOrder();
			pesanLabel.setText(pesan);
			pesanLabel.setTextFill(Color.YELLOW);
			if(pesan.contains("Success")) {
				pesanLabel.setTextFill(Color.LIGHTGREEN);
			}
			
			orderForTableViewController.clearOrder();
			orderForTableViewController.refreshOrderTableData(orderMenuItemTableView, menuItemIdTextField, menuItemNameTextField, menuItemPriceTextField, quantityTextField);
        	totalPriceLabel.setText(String.valueOf("Total Price: " + orderForTableViewController.countTotalPrice()));
		});
		
		Button clearButton = new Button("Clear Order");
		clearButton.setStyle("-fx-font-size: 15px; -fx-padding: 5px 15px");
		clearButton.setOnAction(e -> {
			String pesan = orderForTableViewController.clearOrder();
			pesanLabel.setText(pesan);
			pesanLabel.setTextFill(Color.LIGHTGREEN);
			orderForTableViewController.refreshOrderTableData(orderMenuItemTableView, menuItemIdTextField, menuItemNameTextField, menuItemPriceTextField, quantityTextField);
        	totalPriceLabel.setText(String.valueOf("Total Price: " + orderForTableViewController.countTotalPrice()));
		});
		
		buttonBox2.getChildren().addAll(submitOrder, clearButton);
        
        content.getChildren().addAll(menuItemTableView, titleLabel, pesanLabel, menuItemIdTextField, menuItemNameTextField, menuItemPriceTextField, quantityTextField, buttonBox, orderMenuItemTableView, totalPriceBox, buttonBox2);
        
		return content;
	}
}
