package model;

import java.util.Date;

public class Receipt {
	private int receiptId;
    private int receiptOrderId;
    private Double receiptPaymentAmount;
    private Date receiptPaymentDate;
    private String receiptPaymentType;

	public Receipt() {
		// TODO Auto-generated constructor stub
	}

	public int getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}
	public int getReceiptOrderId() {
		return receiptOrderId;
	}
	public void setReceiptOrderId(int receiptOrderId) {
		this.receiptOrderId = receiptOrderId;
	}
	public Double getReceiptPaymentAmount() {
		return receiptPaymentAmount;
	}
	public void setReceiptPaymentAmount(Double receiptPaymentAmount) {
		this.receiptPaymentAmount = receiptPaymentAmount;
	}
	public Date getReceiptPaymentDate() {
		return receiptPaymentDate;
	}
	public void setReceiptPaymentDate(Date receiptPaymentDate) {
		this.receiptPaymentDate = receiptPaymentDate;
	}
	public String getReceiptPaymentType() {
		return receiptPaymentType;
	}
	public void setReceiptPaymentType(String receiptPaymentType) {
		this.receiptPaymentType = receiptPaymentType;
	}
	
	

}
