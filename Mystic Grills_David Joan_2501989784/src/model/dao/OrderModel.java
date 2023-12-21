package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.Order;

public class OrderModel {
	DatabaseConnector dbConnector  = DatabaseConnector.getConnection();
	
	public int createOrder(int orderUserId, String orderStatus, Date orderDate, Double orderTotal) {
    	int orderId = 0;
		try {
	        PreparedStatement preparedStatement = dbConnector.prepare("INSERT INTO orders (orderUserId, orderStatus, orderDate, orderTotal) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setInt(1, orderUserId);
	        preparedStatement.setString(2, orderStatus);
	        preparedStatement.setDate(3, (java.sql.Date) orderDate);
	        preparedStatement.setDouble(4, orderTotal);
	        
	        dbConnector.executePreparedQueryIUD(preparedStatement);
	        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            orderId = generatedKeys.getInt(1);
	            return orderId;
	        }	        
    	} catch (SQLException e) {
           e.printStackTrace();
       }
    	
		return orderId;
    }
	
	public Boolean updateOrder(int orderId, Date orderDate, Double orderTotal) {
    	Boolean isSuccess = false;
    	try {
    		
	        PreparedStatement preparedStatement = dbConnector.prepare("UPDATE orders SET orderDate = ?, orderTotal = ? WHERE orderId = ?");
	        preparedStatement.setDate(1, (java.sql.Date) orderDate);
	        preparedStatement.setDouble(2, orderTotal);
	        preparedStatement.setInt(3, orderId);
	        
	        dbConnector.executePreparedQueryIUD(preparedStatement);
	        isSuccess = true;
       } catch (SQLException e) {
           e.printStackTrace();
       }
    	
    	return isSuccess;
	}
	
	public Boolean updateOrderStatus(int orderId, String orderStatus) {
    	Boolean isSuccess = false;
    	try {
    		
	        PreparedStatement preparedStatement = dbConnector.prepare("UPDATE orders SET orderStatus = ? WHERE orderId = ?");
	        preparedStatement.setString(1, orderStatus);
	        preparedStatement.setInt(2, orderId);
	        
	        dbConnector.executePreparedQueryIUD(preparedStatement);
	        isSuccess = true;
       } catch (SQLException e) {
           e.printStackTrace();
       }
    	
    	return isSuccess;
	}
	
	//untuk delete disini saya lakukan sedikit modifikasi yaitu tidak hard delete melainkan soft delete (order akan memiliki status cancel)
	public Boolean deleteOrder(int orderId) {
    	Boolean isSuccess = false;
    	try {
//	        PreparedStatement preparedStatement = dbConnector.prepare("DELETE FROM orders WHERE orderId = ?");
    		PreparedStatement preparedStatement = dbConnector.prepare("UPDATE orders SET orderStatus = ? WHERE orderId = ?");
    		preparedStatement.setString(1, "Canceled");
	        preparedStatement.setInt(2, orderId);
	        
	        dbConnector.executePreparedQueryIUD(preparedStatement);
	        isSuccess = true;
       } catch (SQLException e) {
           e.printStackTrace();
       }
    	
    	return isSuccess;
    }
	
	public ArrayList<Order> getAllOrders() {
		ArrayList<Order> orders = new ArrayList<>();

        try {
             PreparedStatement preparedStatement = dbConnector.prepare("SELECT * FROM orders");
             ResultSet resultSet = dbConnector.executePreparedQueryS(preparedStatement);

            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setOrderUserId(resultSet.getInt("orderUserId"));
                order.setOrderStatus(resultSet.getString("orderStatus"));
                order.setOrderDate(resultSet.getDate("orderDate"));
                order.setOrderTotal(resultSet.getDouble("orderTotal"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orders;
    }
		
	public ArrayList<Order> getOrdersByCustomerId(int customerId){
		ArrayList<Order> orders = new ArrayList<>();
		try {
            PreparedStatement preparedStatement = dbConnector.prepare("SELECT * FROM orders WHERE orderUserId = ?");
            preparedStatement.setInt(1, customerId);
            
            ResultSet resultSet = dbConnector.executePreparedQueryS(preparedStatement);

           while (resultSet.next()) {
               Order order = new Order();
               order.setOrderId(resultSet.getInt("orderId"));
               order.setOrderUserId(resultSet.getInt("orderUserId"));
               order.setOrderStatus(resultSet.getString("orderStatus"));
               order.setOrderDate(resultSet.getDate("orderDate"));
               order.setOrderTotal(resultSet.getDouble("orderTotal"));
               orders.add(order);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       
       return orders;
	}
	
	public Order getOrderByOrderId(int orderId){
		Order order = new Order();
		try {
            PreparedStatement preparedStatement = dbConnector.prepare("SELECT * FROM orders WHERE orderId = ?");
            preparedStatement.setInt(1, orderId);
            
            ResultSet resultSet = dbConnector.executePreparedQueryS(preparedStatement);

           if(resultSet.next()) {
               order.setOrderId(resultSet.getInt("orderId"));
               order.setOrderUserId(resultSet.getInt("orderUserId"));
               order.setOrderStatus(resultSet.getString("orderStatus"));
               order.setOrderDate(resultSet.getDate("orderDate"));
               order.setOrderTotal(resultSet.getDouble("orderTotal"));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       
       return order;
	}
	
	public ArrayList<Order> getOrdersByCustomerIdNStatus(int customerId, ArrayList<String> orderStatus){
		ArrayList<Order> orders = new ArrayList<>();
		try {
			String query = "SELECT * FROM orders WHERE orderUserId = ? AND orderStatus IN (";
			for(int i = 0; i < orderStatus.size(); i++) {
	        	if(i == orderStatus.size() - 1) {
					query += "?";
	        	} else {
	        		query += "?, ";
	        	}
	        }
			query += ")";
			
	        PreparedStatement preparedStatement = dbConnector.prepare(query);
	        preparedStatement.setInt(1, customerId);
	        for (int i = 0; i < orderStatus.size(); i++) {
	            preparedStatement.setString(i + 2, orderStatus.get(i));
	        }
	        
	        ResultSet resultSet = dbConnector.executePreparedQueryS(preparedStatement);

	        while (resultSet.next()) {
               Order order = new Order();
               order.setOrderId(resultSet.getInt("orderId"));
               order.setOrderUserId(resultSet.getInt("orderUserId"));
               order.setOrderStatus(resultSet.getString("orderStatus"));
               order.setOrderDate(resultSet.getDate("orderDate"));
               order.setOrderTotal(resultSet.getDouble("orderTotal"));
               orders.add(order);
	        }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       
       return orders;
	}
	
	public ArrayList<Order> getOrdersByStatus(ArrayList<String> orderStatus){
		ArrayList<Order> orders = new ArrayList<>();
		try {
			String query = "SELECT * FROM orders WHERE orderStatus IN (";
			for(int i = 0; i < orderStatus.size(); i++) {
	        	if(i == orderStatus.size() - 1) {
					query += "?";
	        	} else {
	        		query += "?, ";
	        	}
	        }
			query += ")";
			
	        PreparedStatement preparedStatement = dbConnector.prepare(query);
	        for (int i = 0; i < orderStatus.size(); i++) {
	            preparedStatement.setString(i + 1, orderStatus.get(i));
	        }
	        
	        ResultSet resultSet = dbConnector.executePreparedQueryS(preparedStatement);

	        while (resultSet.next()) {
               Order order = new Order();
               order.setOrderId(resultSet.getInt("orderId"));
               order.setOrderUserId(resultSet.getInt("orderUserId"));
               order.setOrderStatus(resultSet.getString("orderStatus"));
               order.setOrderDate(resultSet.getDate("orderDate"));
               order.setOrderTotal(resultSet.getDouble("orderTotal"));
               orders.add(order);
	        }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       
       return orders;
	}
}
