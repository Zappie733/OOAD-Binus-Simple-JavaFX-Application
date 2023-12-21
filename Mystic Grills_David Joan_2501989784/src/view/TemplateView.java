package view;

import factory.headerFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.SessionManager;
import model.User;

public class TemplateView {

    public HBox addHeader(Stage mysticGrillsApp) {
    	HBox header = new HBox();
    	header.setPadding(new Insets(20));
        header.setSpacing(20); 
        header.setStyle("-fx-background-color: #b4b4b4;"); 
        header.setAlignment(Pos.CENTER);
       
        
        User user = SessionManager.getCurrentUser();
        String userRole = null;
        if(user != null) {
        	userRole = user.getUserRole();
        	
        	 if(userRole.equals("Admin")) {
        		 header = headerFactory.createAdminHeader(mysticGrillsApp, header);
             } else if(userRole.equals("Customer")) {
            	 header = headerFactory.createCustomerHeader(mysticGrillsApp, header);
             } else if(userRole.equals("Chef")) {
            	 header = headerFactory.createChefHeader(mysticGrillsApp, header);
             } else if(userRole.equals("Waiter")) {
            	 header = headerFactory.createWaiterHeader(mysticGrillsApp, header);
             } else if(userRole.equals("Cashier")) {
            	 header = headerFactory.createCashierHeader(mysticGrillsApp, header);
             }
        } 
        
        return header;
    };
    
    
    public VBox addFooter() {
    	VBox footer = new VBox();
        footer.setPadding(new Insets(10));
        footer.setStyle("-fx-background-color: #b4b4b4;"); // Background color for the footer
        footer.setSpacing(10);
        footer.setAlignment(Pos.CENTER);

        Text copyright = new Text("© 2023 Mystic Grills");
        copyright.setFill(Color.WHITE);

        Text instagram = new Text("Follow us on Instagram @MysticGrills");
        instagram.setFill(Color.WHITE);

        footer.getChildren().addAll(copyright, instagram);
		
        return footer;
    };
}
