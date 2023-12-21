package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.OrderItem;

public class OrderItemModel {
    DatabaseConnector dbConnector = DatabaseConnector.getConnection();

    public Boolean createOrderItem(int orderId, int menuItemId, int quantity) {
    	Boolean isSuccess = false;
    	try {
            PreparedStatement preparedStatement = dbConnector.prepare(
                    "INSERT INTO orderitems (orderId, menuItemId, quantity) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, menuItemId);
            preparedStatement.setInt(3, quantity);

            dbConnector.executePreparedQueryIUD(preparedStatement);
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return isSuccess;
    }

    public Boolean updateOrderItem(int orderId, int menuItemId, int quantity) {
        Boolean isSuccess = false;
        try {

            PreparedStatement preparedStatement = dbConnector.prepare(
                    "UPDATE orderitems SET quantity = ? WHERE orderId = ? AND menuItemId = ?");
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, orderId);
            preparedStatement.setInt(3, menuItemId);

            dbConnector.executePreparedQueryIUD(preparedStatement);
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    public Boolean deleteOrderItem(int orderId, int menuItemId) {
        Boolean isSuccess = false;
        try {
            PreparedStatement preparedStatement = dbConnector.prepare(
                    "DELETE FROM orderitems WHERE orderId = ? AND menuItemId = ?");
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, menuItemId);

            dbConnector.executePreparedQueryIUD(preparedStatement);
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    public ArrayList<OrderItem> getAllOrderItemsByOrderId(int orderId) {
    	ArrayList<OrderItem> orderItems = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = dbConnector.prepare("SELECT * FROM orderitems WHERE orderId = ?");
            preparedStatement.setInt(1, orderId);
            
            ResultSet resultSet = dbConnector.executePreparedQueryS(preparedStatement);

            while (resultSet.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(resultSet.getInt("orderId"));
                orderItem.setMenuItemId(resultSet.getInt("menuItemId"));
                orderItem.setQuantity(resultSet.getInt("quantity"));

                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }
}
