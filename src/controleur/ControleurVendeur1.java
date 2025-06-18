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

public class ControleurVendeur1 extends Controleur {
    private Librairie modele;
    private LivreExpress vue;

    @FXML
    private Button btnDeco;

    @FXML
    private Button btnCommande;

    @FXML
    private Button btnImporter;


    @FXML
    public void controleurBoutDeco(ActionEvent e){
        System.out.println("vous etes bien deconnecter");
    }

    @FXML
    public void controleurCommande(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurImporter(ActionEvent e){
        System.out.println("vous etes sur le magasin2");
    } 


    public void setVue(LivreExpress vue){
        this.vue=vue;
    }
    


}