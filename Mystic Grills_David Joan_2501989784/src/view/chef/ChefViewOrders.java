package view.chef;

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
import model.Order;
import view.BaseView;

public class ChefViewOrders extends BaseView {
	private OrderController orderController;
	
	public ChefViewOrders() {
		orderController = new OrderController();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected VBox content(Stage mysticGrillsApp) {
		mysticGrillsApp.setTitle("Mystic Grills - Chef Orders Page");
		VBox content = new VBox();
		content.setPadding(new Insets(30));
		content.setAlignment(Pos.CENTER);
		
		//title
	    Label titleLabel = new Label("Pending Orders");
	    titleLabel.setStyle("-fx-font-size: 30px");
	    titleLabel.setTextFill(Color.WHITE);
	    titleLabel.setAlignment(Pos.CENTER);
	    VBox.setMargin(titleLabel, new Insets(0, 0, 20, 0));
	    
	    //table incoming order (pending)
		TableView<Order> pendingOrderTableView = new TableView<>();
		pendingOrderTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
		pendingOrderTableView.setPlaceholder(new Label("No data available"));
		pendingOrderTableView.setMinHeight(170);
		pendingOrderTableView.setMaxHeight(170);
		VBox.setMargin(pendingOrderTableView, new Insets(0, 0, 20, 0));
      
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
      
		pendingOrderTableView.getColumns().addAll(orderIdColumn, orderUserIdColumn, orderStatusColumn, orderDateColumn, orderTotalColumn);
      
		ArrayList<String> orderStatus = new ArrayList<>();
		orderStatus.add("Pending");
      
		ObservableList<Order> orders = orderController.getOrdersByStatus(orderStatus);
		pendingOrderTableView.setItems(orders);

		content.getChildren().addAll(titleLabel, pendingOrderTableView);
      
		pendingOrderTableView.setOnMouseClicked(event -> {
          if (!pendingOrderTableView.getSelectionModel().isEmpty()) {
          	Order selectedOrderItem = pendingOrderTableView.getSelectionModel().getSelectedItem();

          	ChefViewDetailOrder page = new ChefViewDetailOrder();
          	page.display(mysticGrillsApp, selectedOrderItem.getOrderId());
          }
      });
    

	return content;
	}
}
