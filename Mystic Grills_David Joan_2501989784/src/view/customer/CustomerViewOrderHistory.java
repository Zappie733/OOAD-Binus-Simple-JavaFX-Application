package view.customer;

import java.util.ArrayList;
import java.util.Date;

import controller.OrderController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.SessionManager;
import model.Order;
import view.BaseView;

public class CustomerViewOrderHistory extends BaseView {
	private OrderController orderController;
	
	
	public CustomerViewOrderHistory() {
		orderController = new OrderController();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected VBox content(Stage mysticGrillsApp) {
		mysticGrillsApp.setTitle("Mystic Grills - Customer Order History Page");
		VBox content = new VBox();
		content.setPadding(new Insets(30));
		content.setAlignment(Pos.CENTER);
		
		//title
        Label titleLabel = new Label("Order History");
        titleLabel.setStyle("-fx-font-size: 30px");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setAlignment(Pos.CENTER);
        VBox.setMargin(titleLabel, new Insets(0, 0, 20, 0));
        
		//table active order (pending, prepared, served)
		TableView<Order> activeOrderTableView = new TableView<>();
		activeOrderTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
		activeOrderTableView.setPlaceholder(new Label("No data available"));
		activeOrderTableView.setMinHeight(170);
		activeOrderTableView.setMaxHeight(170);
        VBox.setMargin(activeOrderTableView, new Insets(0, 0, 20, 0));
        
        TableColumn<Order, Integer> orderIdColumn = new TableColumn<>("Order Id");
        TableColumn<Order, String> orderUserIdColumn = new TableColumn<>("Order User Id");
        TableColumn<Order, String> orderStatusColumn = new TableColumn<>("Order Status");
        TableColumn<Order, Date> orderDateColumn = new TableColumn<>("Order Date");
        TableColumn<Order, Double> orderTotalColumn = new TableColumn<>("Order Total Price");
        
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId" ));
        orderUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderUserId" ));
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus" ));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate" ));
        orderTotalColumn.setCellValueFactory(new PropertyValueFactory<>("orderTotal" ));
        
        activeOrderTableView.getColumns().addAll(orderIdColumn, orderUserIdColumn, orderStatusColumn, orderDateColumn, orderTotalColumn);
        
        ArrayList<String> orderStatus = new ArrayList<>();
        orderStatus.add("Paid");
        orderStatus.add("Canceled");
        
        ObservableList<Order> orders = orderController.getOrdersByCustomerIdNStatus(SessionManager.getCurrentUser().getUserId(), orderStatus);
        activeOrderTableView.setItems(orders);

        content.getChildren().addAll(titleLabel, activeOrderTableView);
        
        activeOrderTableView.setOnMouseClicked(event -> {
            if (!activeOrderTableView.getSelectionModel().isEmpty()) {
            	Order selectedOrderItem = activeOrderTableView.getSelectionModel().getSelectedItem();

            	CustomerViewDetailOrderHistory page = new CustomerViewDetailOrderHistory();
            	page.display(mysticGrillsApp, selectedOrderItem.getOrderId());
            }
        });
        
		return content;
	}

}
