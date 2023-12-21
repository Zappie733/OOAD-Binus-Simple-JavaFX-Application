package view.admin;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.SessionManager;
import model.User;
import view.BaseView;

public class AdminHome extends BaseView {

	@Override
	protected VBox content(Stage mysticGrillsApp) {
		mysticGrillsApp.setTitle("Mystic Grills - Admin Home Page");
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		
		User user = SessionManager.getCurrentUser();
		Label greetingLabel = new Label("Welcome to Mystic Grills");
		greetingLabel.setTextFill(Color.YELLOW);
		greetingLabel.setStyle("-fx-font-size: 30px;");
		Label enjoyLabel = new Label("Enjoy our best grills");
		enjoyLabel.setTextFill(Color.WHITE);
		enjoyLabel.setStyle("-fx-font-size: 30px;");
		Label goodDayLabel = new Label("Have a good day " + user.getUserName());
		goodDayLabel.setTextFill(Color.YELLOW);
		goodDayLabel.setStyle("-fx-font-size: 30px;");
        
		content.getChildren().addAll(greetingLabel, enjoyLabel, goodDayLabel);
		
		return content;
		
	}
}
