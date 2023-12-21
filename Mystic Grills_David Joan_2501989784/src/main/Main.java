package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.dao.DatabaseConnector;
import view.Login;


public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage mysticGrillsApp) throws Exception {
		// TODO Auto-generated method stub
		mysticGrillsApp.setWidth(1100);
		mysticGrillsApp.setHeight(700);
		
		Login login = new Login();
		login.display(mysticGrillsApp);

		mysticGrillsApp.setOnCloseRequest(e -> {
		    //closeConnections() sebelum menutup aplikasi
		    DatabaseConnector.getConnection().closeConnections();
		    //end session
		    SessionManager.endSession();
		});
	}

}