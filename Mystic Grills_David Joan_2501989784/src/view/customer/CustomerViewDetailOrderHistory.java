package view.customer;

import controller.OrderController;
import controller.OrderForTableViewController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Order;
import model.OrderForTableView;
import view.BaseViewDetail;

public class CustomerViewDetailOrderHistory extends BaseViewDetail{
	private OrderForTableViewController orderForTableViewController;
	private OrderController orderController;
	public CustomerViewDetailOrderHistory() {
		orderForTableViewController = new OrderForTableViewController();
		orderController = new OrderController();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected VBox content(Stage mysticGrillsApp, int orderId) {
		Order curOrder = orderController.getOrderByOrderId(orderId);
		
		mysticGrillsApp.setTitle("Mystic Grills - Customer Detail Order History Page");
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
			CustomerViewOrderHistory page = new CustomerViewOrderHistory();
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
        
        Double biayaLainLain = curOrder.getOrderTotal() - orderForTableViewController.countTotalPrice();
        totalPriceLabel.setText(String.valueOf("Total Price: " + orderForTableViewController.countTotalPrice() + "(main) + " + biayaLainLain + "(others)"));
        
        totalPriceBox.getChildren().add(totalPriceLabel);
        
        content.getChildren().addAll(buttonBox, orderMenuItemTableView, totalPriceBox);
        
		return content;
	}

}
