package factory;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import main.SessionManager;

public class LabelFactory {
	public static Label createNavigationLabel(String id, String text, String defaultStyle, Color defaultColor,
                                             String hoverStyle, Color hoverColor, Runnable onClickAction) {
        Label label = new Label(text);
        label.setId(id);
        
//        System.out.println(SessionManager.getCurrentPage());
//        System.out.println(id.equals(SessionManager.getCurrentPage()));
        
        //jika currentPage belum ada di SessionManager maka kita set label yang pertama yaitu home menjadi current page, ya memang karena pertama kali user masuk juga akan masuknya ke home page
        if(SessionManager.getCurrentPage() == null) {
        	SessionManager.setCurrentPage(id);
        }
        //jika id sama dengan yang di session maka dikasih style yang menandakan itu current page
        if(id.equals(SessionManager.getCurrentPage())) {
            label.setStyle(hoverStyle);
            label.setTextFill(hoverColor);
        }else {
            label.setTextFill(defaultColor);
            label.setStyle(defaultStyle);
            
            //buat hover
            label.setOnMouseEntered(e -> {
                label.setStyle(hoverStyle);
                label.setTextFill(hoverColor);
            });
            label.setOnMouseExited(e -> {
                label.setStyle(defaultStyle);
                label.setTextFill(defaultColor);
            });
        }
        
        //navigasi page
        label.setOnMouseClicked(e -> {
        	SessionManager.setCurrentPage(id);
            onClickAction.run();
        });



        return label;
    }
}

