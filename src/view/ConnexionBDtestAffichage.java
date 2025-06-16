package view;

import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.*;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;


public class ConnexionBDtestAffichage extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = FXMLLoader.load(getClass().getResource("/view/ConnexionConsole.fxml"));
            //BorderPane root = new BorderPane();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Connexion à la base de données");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur de chargement du fichier FXML !");
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
//javac -d bin --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml src/view/ConnexionBDtestAffichage.java
//java -cp bin --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml view.ConnexionBDtestAffichage