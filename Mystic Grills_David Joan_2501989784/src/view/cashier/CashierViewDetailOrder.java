package view.cashier;

import java.util.Date;

import controller.OrderController;
import controller.OrderForTableViewController;
import controller.ReceiptController;
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
import model.OrderForTableView;
import view.BaseViewDetail;

public class CashierViewDetailOrder extends BaseViewDetail{
	private OrderForTableViewController orderForTableViewController;
	private OrderController orderController;
	private ReceiptController receiptController;
	
	public CashierViewDetailOrder() {
		orderForTableViewController = new OrderForTableViewController();
		orderController = new OrderController();
		receiptController = new ReceiptController();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	protected VBox content(Stage mysticGrillsApp, int orderId) {
		mysticGrillsApp.setTitle("Mystic Grills - Cashier Detail Order Page");
		VBox content = new VBox();
		content.setPadding(new Insets(30));
		content.setAlignment(Pos.CENTER);
		
		//back button
		HBox buttonBox = new HBox();
		VBox.setMargin(buttonBox, new Insets(0, 0, 20, 0));
		buttonBox.setAlignment(Pos.TOP_LEFT);
        
		Button backButton = new Button("Back");
		backButton.setStyle("-fx-font-size: 15px; -fx-padding: 5px 15px");
		backButton.setOnAction(e -> {
			CashierViewOrders page = new CashierViewOrders();
        	page.display(mysticGrillsApp);
        });
		
		buttonBox.getChildren().add(backButton);
		
		
		//table order
		TableView<OrderForTableView> orderMenuItemTableView = new TableView<>();
		orderMenuItemTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
		orderMenuItemTableView.setPlaceholder(new Label("No data available"));
		orderMenuItemTableView.setMinHeight(170);
		orderMenuItemTableView.setMaxHeight(170);
        VBox.setMargin(orderMenuItemTableView, new Insets(0, 0, 20, 0));
        
        TableColumn<OrderForTableView, Integer> idColumn = new TableColumn<>("Menu Item ID");
        TableColumn<OrderForTableView, String> nameColumn = new TableColumn<>("Menu Item Name");
        TableColumn<OrderForTableView, Double> priceColumn = new TableColumn<>("Menu Item Price");
        TableColumn<OrderForTableView, Integer> quantityColumn = new TableColumn<>("Quantity");
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemId" ));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName" ));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice" ));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity" ));
        
        orderMenuItemTableView.getColumns().addAll(idColumn, nameColumn, priceColumn, quantityColumn);
        
        orderForTableViewController.initializeData(orderId);
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
        
        
      	//Payment Form
        Label titleLabel = new Label("Payment Order Form");
        titleLabel.setStyle("-fx-font-size: 25px");
        titleLabel.setTextFill(Color.WHITE);
        
        Label pesanLabel = new Label("");
        pesanLabel.setStyle("-fx-font-size: 15px");
        pesanLabel.setTextFill(Color.YELLOW);
        
		TextField orderIdTextField = new TextField(String.valueOf(orderId));
		orderIdTextField.setMaxWidth(400);
		orderIdTextField.setStyle("-fx-font-size: 15px");
		orderIdTextField.setDisable(true);
		
		TextField paymentTypeTextField = new TextField();
		paymentTypeTextField.setPromptText("Payment Type");
		paymentTypeTextField.setMaxWidth(400);
		paymentTypeTextField.setStyle("-fx-font-size: 15px");
        
		TextField paymentAmountTextField = new TextField();
		paymentAmountTextField.setPromptText("Payment Amount");
		paymentAmountTextField.setMaxWidth(400);
		paymentAmountTextField.setStyle("-fx-font-size: 15px");
		
		Button processButton = new Button("Process Payment");
		processButton.setStyle("-fx-font-size: 15px; -fx-padding: 5px 15px");
		VBox.setMargin(processButton, new Insets(15, 0, 0, 0));
		processButton.setOnAction(e -> {
			String paymentType = "";
			Double paymentAmount = 0.0;
            
            if(!paymentTypeTextField.getText().isEmpty()){
            	paymentType = paymentTypeTextField.getText();
            }
            if(!paymentAmountTextField.getText().isEmpty()){
            	try {
                	paymentAmount = Double.valueOf(paymentAmountTextField.getText());
	            } catch(NumberFormatException e1) {
	            	paymentAmount = -1.0;
	        	}
            }
            
        	Date currentDate = new Date();
        	java.sql.Date sqlCurrentDate = new java.sql.Date(currentDate.getTime());
        	
			String pesan = receiptController.createReceipt(orderId, paymentAmount, sqlCurrentDate, paymentType);
			pesanLabel.setText(pesan);
			pesanLabel.setTextFill(Color.YELLOW);
			
			if(pesan.contains("Success")) {
				pesanLabel.setTextFill(Color.LIGHTGREEN);
				String pesan2 = orderController.updateOrderStatus(orderId, "Paid");
				
				if(!pesan2.contains("Success")) {
					pesanLabel.setTextFill(Color.YELLOW);
				} else {
					pesan = "Payment process & " + pesan;
					pesanLabel.setText(pesan);
					
					orderForTableViewController.initializeData(orderId);
					totalPriceLabel.setText(String.valueOf("Total Price: " + orderForTableViewController.countTotalPrice()));
				
					orderMenuItemTableView.setDisable(true);
					paymentTypeTextField.setDisable(true);
					paymentAmountTextField.setDisable(true);
					processButton.setDisable(true);
				}
			}

        });
		
        content.getChildren().addAll(buttonBox, orderMenuItemTableView, totalPriceBox, titleLabel, pesanLabel, orderIdTextField, paymentTypeTextField, paymentAmountTextField, processButton);
		
		return content;
	}
}
