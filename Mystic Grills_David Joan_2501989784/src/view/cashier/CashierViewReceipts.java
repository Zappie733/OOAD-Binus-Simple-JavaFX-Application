package view.cashier;

import java.util.Date;
import controller.ReceiptController;
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
import model.Receipt;
import view.BaseView;

public class CashierViewReceipts extends BaseView{
	private ReceiptController receiptController;
	
	public CashierViewReceipts() {
		receiptController = new ReceiptController();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected VBox content(Stage mysticGrillsApp) {
		mysticGrillsApp.setTitle("Mystic Grills - Cashier Receipts Page");
		VBox content = new VBox();
		content.setPadding(new Insets(30));
		content.setAlignment(Pos.CENTER);
		
		//title
        Label titleLabel = new Label("Order Receipts");
        titleLabel.setStyle("-fx-font-size: 30px");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setAlignment(Pos.CENTER);
        VBox.setMargin(titleLabel, new Insets(0, 0, 20, 0));
        
		//table active order (pending, prepared, served)
		TableView<Receipt> receiptsTableView = new TableView<>();
		receiptsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
		receiptsTableView.setPlaceholder(new Label("No data available"));
		receiptsTableView.setMinHeight(170);
		receiptsTableView.setMaxHeight(170);
        VBox.setMargin(receiptsTableView, new Insets(0, 0, 20, 0));
        
        TableColumn<Receipt, Integer> receiptIdColumn = new TableColumn<>("Receipt Id");
        TableColumn<Receipt, Integer> receiptOrderIdColumn = new TableColumn<>("Receipt Order Id");
        TableColumn<Receipt, String> receiptPaymentTypeColumn = new TableColumn<>("Receipt Payment Type");
        TableColumn<Receipt, Double> receiptPaymentAmountColumn = new TableColumn<>("Receipt Payment Amount");
        TableColumn<Receipt, Date> receiptPaymentDateColumn = new TableColumn<>("Receipt Payment Date");

        
        receiptIdColumn.setCellValueFactory(new PropertyValueFactory<>("receiptId" ));
        receiptOrderIdColumn.setCellValueFactory(new PropertyValueFactory<>("receiptOrderId" ));
        receiptPaymentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentType" ));
        receiptPaymentAmountColumn.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentAmount" ));
        receiptPaymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentDate" ));
        
        receiptsTableView.getColumns().addAll(receiptIdColumn, receiptOrderIdColumn, receiptPaymentTypeColumn, receiptPaymentAmountColumn, receiptPaymentDateColumn);
        
        ObservableList<Receipt> receipts = receiptController.getAllReceipts();
        receiptsTableView.setItems(receipts);

        receiptsTableView.setOnMouseClicked(event -> {
            if (!receiptsTableView.getSelectionModel().isEmpty()) {
            	Receipt selectedReceiptItem = receiptsTableView.getSelectionModel().getSelectedItem();

            	CashierViewDetailReceipt page = new CashierViewDetailReceipt();
            	page.display(mysticGrillsApp, selectedReceiptItem.getReceiptId());
            }
        });
        
        content.getChildren().addAll(titleLabel, receiptsTableView);
        
		return content;
	}

}
