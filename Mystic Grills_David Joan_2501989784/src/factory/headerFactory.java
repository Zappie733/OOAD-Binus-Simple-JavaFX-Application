package factory;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.SessionManager;
import view.Login;
import view.admin.AdminHome;
import view.admin.AdminViewMenuItems;
import view.admin.AdminViewUsers;
import view.cashier.CashierHome;
import view.cashier.CashierViewOrders;
import view.cashier.CashierViewReceipts;
import view.chef.ChefHome;
import view.chef.ChefViewOrders;
import view.customer.CustomerHome;
import view.customer.CustomerViewActiveOrders;
import view.customer.CustomerViewMenuItems;
import view.customer.CustomerViewOrderHistory;
import view.waiter.WaiterHome;
import view.waiter.WaiterViewOrders;

public class headerFactory {
    private static String defaultStyle = "-fx-font-size: 15px; -fx-border-color: white; -fx-border-width: 2px; -fx-margin: 0px 10px; -fx-padding: 5px 10px;";
    private static String hoverStyle = "-fx-font-size: 15px; -fx-border-color: black; -fx-border-width: 2px; -fx-margin: 0px 10px; -fx-padding: 5px 10px; -fx-background-color: yellow";
    private static Color defaultColor = Color.WHITE;
    private static Color hoverColor = Color.BLACK;
    
	public static HBox createAdminHeader(Stage mysticGrillsApp, HBox header) {
		Label adminHomeLabel = LabelFactory.createNavigationLabel("adminHomeLabel", "Home",defaultStyle,defaultColor,hoverStyle,hoverColor,
			    () -> {
			        AdminHome page = new AdminHome();
			        page.display(mysticGrillsApp);
			    }
			);
	     
		 Label adminViewUsersLabel = LabelFactory.createNavigationLabel("adminViewUsersLabel", "View Users",defaultStyle,defaultColor,hoverStyle,hoverColor,
				    () -> {
				    	AdminViewUsers page = new AdminViewUsers();
				        page.display(mysticGrillsApp);
				    }
				);
	
		 Label adminViewMenuItemsLabel = LabelFactory.createNavigationLabel("adminViewMenuItemsLabel","Menu Items",defaultStyle,defaultColor,hoverStyle,hoverColor,
				    () -> {
				    	AdminViewMenuItems page = new AdminViewMenuItems();
				        page.display(mysticGrillsApp);
				    }
				);
		 
		 Label logout = LabelFactory.createNavigationLabel("logout","Logout",defaultStyle,defaultColor,hoverStyle,hoverColor,
				    () -> {
				    	Login page = new Login();
				        page.display(mysticGrillsApp);
				        SessionManager.endSession();
				    }
				);
		 
	     header.getChildren().addAll(adminHomeLabel, adminViewUsersLabel, adminViewMenuItemsLabel, logout);
		
	     return header;
	
	}

	public static HBox createCustomerHeader(Stage mysticGrillsApp, HBox header) {
		Label customerHomeLabel = LabelFactory.createNavigationLabel("customerHomeLabel", "Home", defaultStyle, defaultColor, hoverStyle, hoverColor,
				() -> {
						CustomerHome page = new CustomerHome();
						page.display(mysticGrillsApp);
					}
				);
		Label customerViewMenuItems = LabelFactory.createNavigationLabel("customerViewMenuItems", "Menu Items", defaultStyle, defaultColor, hoverStyle, hoverColor,
				() -> {
					CustomerViewMenuItems page = new CustomerViewMenuItems();
					page.display(mysticGrillsApp);
				}
			);
		Label customerViewActiveOrdersLabel = LabelFactory.createNavigationLabel("customerViewActiveOrdersLabel", "Active Orders", defaultStyle, defaultColor, hoverStyle, hoverColor,
				() -> {
					CustomerViewActiveOrders page = new CustomerViewActiveOrders();
					page.display(mysticGrillsApp);
				}
			);
		Label customerViewOrderHistoryLabel = LabelFactory.createNavigationLabel("customerViewOrderHistoryLabel", "Order History", defaultStyle, defaultColor, hoverStyle, hoverColor,
				() -> {
					CustomerViewOrderHistory page = new CustomerViewOrderHistory();
					page.display(mysticGrillsApp);
				}
			);
		 Label logout = LabelFactory.createNavigationLabel("logout","Logout",defaultStyle,defaultColor,hoverStyle,hoverColor,
				    () -> {
				    	Login page = new Login();
				        page.display(mysticGrillsApp);
				        SessionManager.endSession();
				    }
				);
		
		header.getChildren().addAll(customerHomeLabel, customerViewMenuItems, customerViewActiveOrdersLabel, customerViewOrderHistoryLabel, logout);
		
		return header;
	}
	
	public static HBox createCashierHeader(Stage mysticGrillsApp, HBox header) {
		Label cashierHomeLabel = LabelFactory.createNavigationLabel("cashierHomeLabel","Home",defaultStyle,defaultColor,hoverStyle,hoverColor,
			    () -> {
			    	CashierHome page = new CashierHome();
			        page.display(mysticGrillsApp);
			    }
			);
		Label cashierViewOrders = LabelFactory.createNavigationLabel("cashierViewOrders","Ongoing Orders",defaultStyle,defaultColor,hoverStyle,hoverColor,
			    () -> {
			    	CashierViewOrders page = new CashierViewOrders();
			        page.display(mysticGrillsApp);
			    }
			);
		Label cashierViewReceipts = LabelFactory.createNavigationLabel("cashierViewReceipts","Receipts",defaultStyle,defaultColor,hoverStyle,hoverColor,
			    () -> {
			    	CashierViewReceipts page = new CashierViewReceipts();
			        page.display(mysticGrillsApp);
			    }
			);
		 Label logout = LabelFactory.createNavigationLabel("logout","Logout",defaultStyle,defaultColor,hoverStyle,hoverColor,
			    () -> {
			    	Login page = new Login();
			        page.display(mysticGrillsApp);
			        SessionManager.endSession();
			    }
			);
		
		header.getChildren().addAll(cashierHomeLabel, cashierViewOrders, cashierViewReceipts, logout);
		
		return header;
		
	}
	
	public static HBox createWaiterHeader(Stage mysticGrillsApp, HBox header) {
		Label waiterHomeLabel = LabelFactory.createNavigationLabel("waiterHomeLabel","Home",defaultStyle,defaultColor,hoverStyle,hoverColor,
			    () -> {
			    	WaiterHome page = new WaiterHome();
			        page.display(mysticGrillsApp);
			    }
			);
		Label waiterViewOrders = LabelFactory.createNavigationLabel("waiterViewOrders","Prepared Orders",defaultStyle,defaultColor,hoverStyle,hoverColor,
			    () -> {
			    	WaiterViewOrders page = new WaiterViewOrders();
			        page.display(mysticGrillsApp);
			    }
			); 
		Label logout = LabelFactory.createNavigationLabel("logout","Logout",defaultStyle,defaultColor,hoverStyle,hoverColor,
				    () -> {
				    	Login page = new Login();
				        page.display(mysticGrillsApp);
				        SessionManager.endSession();
				    }
				);
		
		header.getChildren().addAll(waiterHomeLabel, waiterViewOrders, logout);
		
		return header;
		
	}
	
	public static HBox createChefHeader(Stage mysticGrillsApp, HBox header) {
		Label chefHomeLabel = LabelFactory.createNavigationLabel("chefHomeLabel","Home",defaultStyle,defaultColor,hoverStyle,hoverColor,
			    () -> {
			    	ChefHome page = new ChefHome();
			        page.display(mysticGrillsApp);
			    }
			);
		Label chefViewOrders = LabelFactory.createNavigationLabel("chefViewOrders","Pending Orders",defaultStyle,defaultColor,hoverStyle,hoverColor,
			    () -> {
			    	ChefViewOrders page = new ChefViewOrders();
			        page.display(mysticGrillsApp);
			    }
			);
		
		Label logout = LabelFactory.createNavigationLabel("logout","Logout",defaultStyle,defaultColor,hoverStyle,hoverColor,
			    () -> {
			    	Login page = new Login();
			        page.display(mysticGrillsApp);
			        SessionManager.endSession();
			    }
			);
		
		header.getChildren().addAll(chefHomeLabel, chefViewOrders, logout);
		
		return header;
		
	}
}
