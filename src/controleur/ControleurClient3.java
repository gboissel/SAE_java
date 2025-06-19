package controleur;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
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

public class ControleurClient3 extends Controleur {
    private int numPage;
    private int numPageMax;
    @FXML
    private Button btnDeco;

     @FXML
    private Button btnCatalogue;

     @FXML
    private Button btnSupprPanier;

     @FXML
    private Button btnSuppr;

    @FXML
    private Button btnAjoute;

    @FXML
    private Button btnAvant;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnPayement;

    @FXML
    private Label idClient;


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

    @FXML
    private void gererPreced(ActionEvent event){
        if(this.numPage>0) {
            this.numPage-=1;
            this.majAffichage();
        }
    }

    @FXML
    private void gererSuivant(ActionEvent event){
        if (this.numPage<this.numPageMax) {
            this.numPage+=1;
            this.majAffichage();
        }
    }

    @FXML
    private void gererPayement(ActionEvent event){
        if (this.numPage<this.numPageMax) {
            this.numPage+=1;
            this.majAffichage();
        }
    }
    @FXML
    private void gererRetour(ActionEvent event){
        if (this.numPage<this.numPageMax) {
            this.numPage+=1;
            this.majAffichage();
        }
    }

    private void majAffichage(){

    }

    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}