package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Receipt;

public class ReceiptModel {
	DatabaseConnector dbConnector  = DatabaseConnector.getConnection();
	
	public Boolean createReceipt(int receiptOrderId, Double receiptPaymentAmount, Date receiptPaymentDate, String receiptPaymentType) {
		Boolean isSuccess = false;
		try {
	        PreparedStatement preparedStatement = dbConnector.prepare("INSERT INTO receipts (receiptOrderId, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType) VALUES (?, ?, ?, ?)");
	        preparedStatement.setInt(1, receiptOrderId);
	        preparedStatement.setDouble(2, receiptPaymentAmount);
	        preparedStatement.setDate(3, (java.sql.Date) receiptPaymentDate);
	        preparedStatement.setString(4, receiptPaymentType);
	        
	        dbConnector.executePreparedQueryIUD(preparedStatement);
	        isSuccess = true;
    	} catch (SQLException e) {
           e.printStackTrace();
       }
		return isSuccess;
    }
	
	public Boolean updateReceipt(int receiptOrderId, Double receiptPaymentAmount, Date receiptPaymentDate, String receiptPaymentType) {
    	Boolean isSuccess = false;
    	try {
    		
	        PreparedStatement preparedStatement = dbConnector.prepare("UPDATE receipts SET receiptPaymentAmount = ?, receiptPaymentDate = ?, receiptPaymentType = ? WHERE receiptOrderId = ?");
	        preparedStatement.setDouble(1, receiptPaymentAmount);
	        preparedStatement.setDate(2, (java.sql.Date) receiptPaymentDate);
	        preparedStatement.setString(3, receiptPaymentType);
	        preparedStatement.setInt(4, receiptOrderId);
	        
	        dbConnector.executePreparedQueryIUD(preparedStatement);
	        isSuccess = true;
       } catch (SQLException e) {
           e.printStackTrace();
       }
    	
    	return isSuccess;
	}
	
	public Boolean deleteReceipt(int receiptOrderId) {
    	Boolean isSuccess = false;
    	try {
	        PreparedStatement preparedStatement = dbConnector.prepare("DELETE FROM receipts WHERE receiptOrderId = ?");
	        preparedStatement.setInt(1, receiptOrderId);
	        
	        dbConnector.executePreparedQueryIUD(preparedStatement);
	        isSuccess = true;
       } catch (SQLException e) {
           e.printStackTrace();
       }
    	
    	return isSuccess;
    }
	
	public ArrayList<Receipt> getAllReceipts() {
		ArrayList<Receipt> receipts = new ArrayList<>();

        try {
             PreparedStatement preparedStatement = dbConnector.prepare("SELECT * FROM receipts");
             ResultSet resultSet = dbConnector.executePreparedQueryS(preparedStatement);

            while (resultSet.next()) {
                Receipt receipt = new Receipt();
                receipt.setReceiptId(resultSet.getInt("receiptId"));
                receipt.setReceiptOrderId(resultSet.getInt("receiptOrderId"));
                receipt.setReceiptPaymentAmount(resultSet.getDouble("receiptPaymentAmount"));
                receipt.setReceiptPaymentDate(resultSet.getDate("receiptPaymentDate"));
                receipt.setReceiptPaymentType(resultSet.getString("receiptPaymentType"));

                receipts.add(receipt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return receipts;
    }
	
	public Receipt getReceiptById(int receiptId) {
		Receipt receipt = new Receipt();
		 try {
             PreparedStatement preparedStatement = dbConnector.prepare("SELECT * FROM receipts WHERE receiptId = ?");
             preparedStatement.setInt(1, receiptId);
             
             ResultSet resultSet = dbConnector.executePreparedQueryS(preparedStatement);

            if (resultSet.next()) {
                receipt.setReceiptId(resultSet.getInt("receiptId"));
                receipt.setReceiptOrderId(resultSet.getInt("receiptOrderId"));
                receipt.setReceiptPaymentAmount(resultSet.getDouble("receiptPaymentAmount"));
                receipt.setReceiptPaymentDate(resultSet.getDate("receiptPaymentDate"));
                receipt.setReceiptPaymentType(resultSet.getString("receiptPaymentType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		 
		return receipt;
	}
}
