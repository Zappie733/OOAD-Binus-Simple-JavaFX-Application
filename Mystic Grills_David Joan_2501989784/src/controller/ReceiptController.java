package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Order;
import model.Receipt;
import model.dao.ReceiptModel;
import java.util.Date;

public class ReceiptController {
    private ReceiptModel receiptModel;
    private OrderController orderController;
    public ReceiptController() {
        this.receiptModel = new ReceiptModel();
        this.orderController = new OrderController();
    }

    public String createReceipt(int receiptOrderId, Double receiptPaymentAmount, Date receiptPaymentDate, String receiptPaymentType) {
    	Order order = orderController.getOrderByOrderId(receiptOrderId);
    	if(receiptPaymentType.isEmpty()) {
    		return "Payment Type can not be emtpy";
    	}
    	if(!receiptPaymentType.equals("Cash") && !receiptPaymentType.equals("Debit") && !receiptPaymentType.equals("Credit")) {
    		return "Invalid Payment Type, accepted type(Cash, Debit, Credit)";
    	}
    	if(receiptPaymentAmount == -1) {
    		return "Payment Amount must be in format Double (0.0)";
    	}
    	if(receiptPaymentAmount < order.getOrderTotal()) {
    		return "Payment Amount must be greater or equal than order total price";
    	}
    	
    	Boolean isSuccess = false;
    	
    	isSuccess = receiptModel.createReceipt(receiptOrderId, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType);
        
    	if(isSuccess == true) {
    		//update total price dari order
    		orderController.updateOrder(order.getOrderId(), order.getOrderDate(), receiptPaymentAmount);
    		return "Create Receipt Success";
    	}
    	
        return "Create Receipt Failed";
    }

    public boolean updateReceipt(int receiptOrderId, Double receiptPaymentAmount, Date receiptPaymentDate, String receiptPaymentType) {
        return receiptModel.updateReceipt(receiptOrderId, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType);
    }

    public boolean deleteReceipt(int receiptOrderId) {
        return receiptModel.deleteReceipt(receiptOrderId);
    }

    public ObservableList<Receipt> getAllReceipts() {
        return FXCollections.observableArrayList(receiptModel.getAllReceipts());
    }

    public Receipt getReceiptById(int receiptId) {
        return receiptModel.getReceiptById(receiptId);
    }
}
