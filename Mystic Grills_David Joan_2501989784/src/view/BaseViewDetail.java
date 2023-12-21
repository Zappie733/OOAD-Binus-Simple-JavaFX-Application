package view;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class BaseViewDetail extends TemplateView {

	public void display(Stage mysticGrillsApp, int id) {
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: maroon;");
		
        HBox header = addHeader(mysticGrillsApp);
        VBox footer = addFooter();
        
        ScrollPane scrollPaneContent = new ScrollPane();
        VBox content = content(mysticGrillsApp, id);
        content.setStyle("-fx-background-color: maroon;");
        scrollPaneContent.setContent(content);
        scrollPaneContent.setFitToWidth(true);
        scrollPaneContent.setFitToHeight(true);
        
        root.setTop(header);
        root.setCenter(scrollPaneContent);
        root.setBottom(footer);

        Scene scene = new Scene(root);
        mysticGrillsApp.setScene(scene);
        mysticGrillsApp.show();
	};
	
	protected abstract VBox content(Stage mysticGrillsApp, int id);
}
