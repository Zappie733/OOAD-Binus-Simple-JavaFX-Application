package controller;

import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Order;
import model.dao.OrderModel;

public class OrderController {

    private OrderModel orderModel;

    public OrderController() {
        orderModel = new OrderModel();
    }

    public int createOrder(int orderUserId, String orderStatus, Date orderDate, Double orderTotal) {
        return orderModel.createOrder(orderUserId, orderStatus, orderDate, orderTotal);
    }

    public String updateOrder(int orderId, Date orderDate, Double orderTotal) {
    	Boolean isSuccess = false;
    	isSuccess = orderModel.updateOrder(orderId, orderDate, orderTotal); 
        if(isSuccess == true) {
        	return "Update Order Success";
        }
        
        return "Update Order Failed";
    }

    public String updateOrderStatus(int orderId, String orderStatus) {
        Boolean isSuccess = false;
        
        isSuccess = orderModel.updateOrderStatus(orderId, orderStatus);
        if(isSuccess == true) {
        	return orderStatus + " Order Success";
        }
        		
    	return orderStatus + " Order Failed";
    }

    public String deleteOrder(int orderId) {
    	Boolean isSuccess = false;
    	isSuccess = orderModel.deleteOrder(orderId);
    	
    	if(isSuccess == true) {
            return "Cancel Order Success";
    	}
    	
    	return "Cancel Order Failed";
    }

    public ObservableList<Order> getAllOrders() {
        ArrayList<Order> orders = orderModel.getAllOrders();
        return FXCollections.observableArrayList(orders);
    }

    public ObservableList<Order> getOrdersByCustomerId(int customerId) {
        ArrayList<Order> orders = orderModel.getOrdersByCustomerId(customerId);
        return FXCollections.observableArrayList(orders);
    }
    
    public Order getOrderByOrderId(int orderId) {
        return orderModel.getOrderByOrderId(orderId);
    }
    
    public ObservableList<Order> getOrdersByCustomerIdNStatus(int customerId, ArrayList<String> orderStatus) {
        ArrayList<Order> orders = orderModel.getOrdersByCustomerIdNStatus(customerId, orderStatus);
        return FXCollections.observableArrayList(orders);
    }
    
    public ObservableList<Order> getOrdersByStatus(ArrayList<String> orderStatus) {
        ArrayList<Order> orders = orderModel.getOrdersByStatus(orderStatus);
        return FXCollections.observableArrayList(orders);
    }


}
