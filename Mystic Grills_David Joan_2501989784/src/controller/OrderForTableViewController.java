package controller;

import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.SessionManager;
import model.MenuItem;
import model.OrderForTableView;
import model.OrderItem;
import model.User;

public class OrderForTableViewController {
    private ObservableList<OrderForTableView> orderData;
    private OrderController orderController;
    private OrderItemController orderItemController;
    private MenuItemController menuItemController;
    
    public OrderForTableViewController() {
        orderData = FXCollections.observableArrayList();
        orderController = new OrderController();
        orderItemController = new OrderItemController();
        menuItemController = new MenuItemController();
    }

    public ObservableList<OrderForTableView> getOrderData() {
        return orderData;
    }

	public String addItem(int menuItemId, String menuItemName, Double menuItemPrice, int quantity) {
    	if(menuItemName.isEmpty()) {
    		return "No item to be added";
    	}
    	if(quantity == -999) {
    		return "Quantity can not be empty";
    	}
    	if(quantity == -998) {
    		return "Quantity is invalid, (ex valid: 1)";
    	}
    	if(quantity < 0) {
			return "Quantity must be grater or equal to 0";
		}
    	
    	OrderForTableView order = checkOrderById(menuItemId);

        if (order.getMenuItemName() == null) {
            order = new OrderForTableView();
            order.setMenuItemId(menuItemId);
            order.setMenuItemName(menuItemName);
            order.setMenuItemPrice(menuItemPrice);
            order.setQuantity(quantity);
            orderData.add(order);
            return "Add Item Success";
        } else {
        	int newQuantity = order.getQuantity() + quantity;
            orderData.remove(order);
            
            order = new OrderForTableView();
            order.setMenuItemId(menuItemId);
            order.setMenuItemName(menuItemName);
            order.setMenuItemPrice(menuItemPrice);
            order.setQuantity(newQuantity);
            orderData.add(order);
            return "Add Item Success";
        }
        
    }
    
    public OrderForTableView checkOrderById(int menuItemId) {
    	for(OrderForTableView order: getOrderData()) {
    		if(order.getMenuItemId() == menuItemId) {
    			return order;
    		}
    	}
    	
    	return new OrderForTableView();
    }
    
    public String deleteMenuItem(int menuItemId) {
    	OrderForTableView order = checkOrderById(menuItemId);
    	if (order.getMenuItemName() == null) {
    		return "Nothing to remove, you don't have this item";
    	} else {
    		//ubah jadi quantity 0
    		OrderForTableView updatedOrder = new OrderForTableView();
    		updatedOrder.setMenuItemId(order.getMenuItemId());
    		updatedOrder.setMenuItemName(order.getMenuItemName());
    		updatedOrder.setMenuItemPrice(order.getMenuItemPrice());
    		updatedOrder.setQuantity(0);
    		orderData.add(updatedOrder);
    		
            orderData.remove(order);
            
    		return "Remove Item Success";
    	}
    } 
    
    public Double countTotalPrice() {
    	Double total = 0.0;
    	for(OrderForTableView order: getOrderData()) {
    		total += order.getMenuItemPrice() * order.getQuantity();
    	}
    	
    	return total;
    }
    
    public String submitOrder() {
    	User user = SessionManager.getCurrentUser();
    	Date currentDate = new Date();
    	java.sql.Date sqlCurrentDate = new java.sql.Date(currentDate.getTime());
    	if(orderData.size() > 0) {
    		int orderId = -1;
        	for(OrderForTableView order: getOrderData()) { 
        		if(orderId == -1 && order.getQuantity() > 0) {
        			orderId = orderController.createOrder(user.getUserId(), "Pending", sqlCurrentDate, countTotalPrice());
        		}
        		if(order.getQuantity() > 0) {
            		orderItemController.createOrderItem(orderId, order.getMenuItemId(), order.getQuantity());
        		}
        	}
        	if(orderId == -1) {
        		return "Nothing to submit";
        	}
        	return "Submit Order Success";
    	} else {
    		return "Nothing to submit";
    	}
    	
    }
    
    public String updateOrder(int orderId) {
    	Date currentDate = new Date();
    	java.sql.Date sqlCurrentDate = new java.sql.Date(currentDate.getTime());
    	if(orderData.size() > 0) {
    		String pesan = orderController.updateOrder(orderId, sqlCurrentDate, countTotalPrice());
    		if(pesan.contains("Success")) {
    			ObservableList<OrderItem> orderItems = orderItemController.getAllOrderItemsByOrderId(orderId);
    			
    			ArrayList<Integer> menuItemsIdList = new ArrayList<>();
    			for(OrderItem o : orderItems) {
    				menuItemsIdList.add(o.getMenuItemId());
    			}
    			
            	for(OrderForTableView order: getOrderData()) {
            		if(menuItemsIdList.contains(order.getMenuItemId())) {
            			if(order.getQuantity() > 0) {
            				System.out.println("Update " + order.getMenuItemName());
            				orderItemController.updateOrderItem(orderId, order.getMenuItemId(), order.getQuantity());
            			} else {
            				System.out.println("Delete " + order.getMenuItemName());
            				orderItemController.deleteOrderItem(orderId, order.getMenuItemId());
            			}
            		} else {
            			if(order.getQuantity() > 0) {
            				System.out.println("Create " + order.getMenuItemName());
                    		orderItemController.createOrderItem(orderId, order.getMenuItemId(), order.getQuantity());
                		} else {
                			System.out.println("0 jadi tidak di Create " + order.getMenuItemName());
                		}
            		}
            	}
    		}

        	clearOrder();
        	return pesan;
    	} else {
    		return "Nothing to Update";
    	}
    	
    }
    
    public String clearOrder() {
    	orderData = FXCollections.observableArrayList();
    	return "Clear/Revert Order Success";
    }
    
    public void refreshOrderTableData(TableView<OrderForTableView> tableView, TextField menuItemIdTextField, TextField menuItemNameTextField, TextField menuItemPriceTextField, TextField quantityTextFields) {
	    ObservableList<OrderForTableView> updatedOrderItems = getOrderData();
	    tableView.setItems(updatedOrderItems);
	    
	    menuItemIdTextField.setText("");
	    menuItemNameTextField.setText("");
	    menuItemPriceTextField.setText("");
	    quantityTextFields.setText("");
	}
    
    public void initializeData(int orderId){
    	clearOrder();
    	ObservableList<OrderItem> orderItems = orderItemController.getAllOrderItemsByOrderId(orderId);
    	
    	for(OrderItem item : orderItems) {
    		MenuItem menu = menuItemController.getMenuItemById(item.getMenuItemId());
    		
    		OrderForTableView order = new OrderForTableView();
            order.setMenuItemId(menu.getMenuItemId());
            order.setMenuItemName(menu.getMenuItemName());
            order.setMenuItemPrice(menu.getMenuItemPrice());
            order.setQuantity(item.getQuantity());
            orderData.add(order);
            
    	}
    }
}
