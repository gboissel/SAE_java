package view;

import javafx.application.Application;
import javafx.application.Platform;
// import javafx.beans.binding.Bindings;                                       \
                                                                                
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class VuePagePourClient extends Application{
 


//@Override
    //public void init(){

    //}

@Override
    public void start(Stage stage) throws Exception{
	Pane root = FXMLLoader.load(getClass().getResource("/view/VuePageClient.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("Livre Express eshop");
    stage.show();                                                                             
    }


public static void main(String[] args) {
    launch(args);
}

}