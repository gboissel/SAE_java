package controleur;

import java.util.Optional;

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
import javafx.scene.control.ButtonType;
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

public class ControleurClient2 extends Controleur{
    @FXML
    private Button btnDeco;
    @FXML
    private Label idClient;
    @FXML
    private Button lb1;
    @FXML
    private Button lb2;
    @FXML
    private Button lb3;
    @FXML
    private Button lb4;
    @FXML
    private Button lb5;
    @FXML
    private Button lb6;
    @FXML
    private Button lb7;
    @FXML
    private Button lb8;
    @FXML
    private Button lb9;
    @FXML
    private Button lb10;

    @FXML
    private Button btnLivre1;

    @FXML
    private Button btnLivre2;

    @FXML
    private Button btnLivre3;

    @FXML
    private Button btnLivre4;

     @FXML
    private Button btnLivre5;

    @FXML
    private Button btnLivre6;
    
    @FXML
    private Button btnLivre7;

    @FXML
    private Button btnLivre8;

    @FXML
    private Button btnLivre9;

    @FXML
    private Button btnPanier;

    @FXML
    private Button btnRecherche;

    @FXML
    private TextField TextRecherche;
                                      
    @FXML
    private void gererPanier(ActionEvent event){

    }
    @FXML
    private void gererRecherche(ActionEvent event){
        
    }


    public Alert popUpDeconnexion(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Voulez vous vraiment vous déconnecter ?\nVous serez renvoyer vers la page d'acceuil", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Attention");
        alert.setHeaderText("Déconnexion");
        return alert;
    }

    @FXML
    private void gererDeconnexion(ActionEvent event) {
        Optional<ButtonType> reponse = popUpDeconnexion().showAndWait();
        if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)) {
            this.modele.setCurUser(null);
            this.vue.changerVue("/view/accueil.fxml");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Déconnexion");
            alert.setHeaderText("Déconnexion réussie !");
            alert.setContentText("Vous êtes bien retournée sur la page d'accueil");
        }

    }
}