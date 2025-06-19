package controleur;

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
import modele.*;
import view.*;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;

public class ControleurVendeur1_3 extends Controleur {
    private Librairie modele;
    private LivreExpress vue;

    @FXML
    private Button btnDeco;

    @FXML
    private Button btnChoixVendeur;

    @FXML
    private Button btnChoixImporter;

    @FXML
    private Button btnmagv1;

      @FXML
    private Button btnmagv2;

      @FXML
    private Button btnmagv3;

      @FXML
    private Button btnmagv4;

      @FXML
    private Button btnmagv5;

      @FXML
    private Button btnmagv6;

      @FXML
    private Button btnmagv7;
      
    @FXML
    private Button btnmagv8;

      @FXML
    private Button btnmagv9;


    @FXML
    public void controleurBoutDeco(ActionEvent e){
        System.out.println("vous etes bien deconnecter");
    }

    @FXML
    public void controleurMagv1(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurMagv2(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurMagv3(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurMagv4(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurMagv5(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurMagv6(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurMagv7(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurMagv8(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurMagv9(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurChoixImporter(ActionEvent e){
        System.out.println("vous etes sur le magasin2");
    } 
    @FXML
    public void controleurChoixVendeur(ActionEvent e){
        System.out.println("truc");
    }


    public void setVue(LivreExpress vue){
        this.vue=vue;
    }
    


}