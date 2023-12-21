package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderItem;
import model.dao.OrderItemModel;

public class OrderItemController {
    private OrderItemModel orderItemModel;

    public OrderItemController() {
        this.orderItemModel = new OrderItemModel();
    }

    public void createOrderItem(int orderId, int menuItemId, int quantity) {
        orderItemModel.createOrderItem(orderId, menuItemId, quantity);
    }

    public boolean updateOrderItem(int orderId, int menuItemId, int quantity) {
        return orderItemModel.updateOrderItem(orderId, menuItemId, quantity);
    }

    public boolean deleteOrderItem(int orderId, int menuItemId) {
        return orderItemModel.deleteOrderItem(orderId, menuItemId);
    }

    public ObservableList<OrderItem> getAllOrderItemsByOrderId(int orderId) {
        return FXCollections.observableArrayList(orderItemModel.getAllOrderItemsByOrderId(orderId));
    }

}
