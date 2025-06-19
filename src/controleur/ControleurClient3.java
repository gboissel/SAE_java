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
    private Button btnAvant;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnPayement;

    @FXML
    private Label idClient;

    @FXML
    private Label totalPanier;

    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    private Label q1;
    @FXML
    private Label q2;
    @FXML
    private Label q3;
    @FXML
    private Label q4;
    @FXML
    private Label p1;
    @FXML
    private Label p2;
    @FXML
    private Label p3;
    @FXML
    private Label p4;
    @FXML
    private Label t1;
    @FXML
    private Label t2;
    @FXML
    private Label t3;
    @FXML
    private Label t4;
    @FXML
    private Button a1;
    @FXML
    private Button a2;
    @FXML
    private Button a3;
    @FXML
    private Button a4;
    @FXML
    private Button s1;
    @FXML
    private Button s2;
    @FXML
    private Button s3;
    @FXML
    private Button s4;
    @FXML
    private Button d1;
    @FXML
    private Button d2;
    @FXML
    private Button d3;
    @FXML
    private Button d4;



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
    private void gererRetourCatalogue(ActionEvent event){
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }

    private void majAffichage(){

    }

    @FXML
    private void gererAjouter(ActionEvent event){
        if (this.numPage<this.numPageMax) {
            this.numPage+=1;
            this.majAffichage();
        }
    }

    @FXML
    private void gererSupprimer(ActionEvent event){
        if (this.numPage<this.numPageMax) {
            this.numPage+=1;
            this.majAffichage();
        }
    }
    @FXML
    private void gererEnlever(ActionEvent event){
        if (this.numPage<this.numPageMax) {
            this.numPage+=1;
            this.majAffichage();
        }
    }
    


    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}