package view;

import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.*;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import controleur.ControlleurConnexionBD;
public class ConnexionBDtestAffichage extends Application {
    @Override
    public void start(Stage primaryStage) {

    }
    public static void main(String[] args) {
        launch(args);
    }
}
//javac -d bin --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml src/view/ConnexionBDtestAffichage.java
//java -cp bin --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml view.ConnexionBDtestAffichage