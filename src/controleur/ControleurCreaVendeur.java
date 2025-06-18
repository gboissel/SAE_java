package controleur;

import JDBC.ConnexionMySQL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Stream;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.*;
import javafx.event.ActionEvent;
import modele.*;


public class ControleurCreaVendeur extends Controleur{
    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField mdp;

    @FXML
    private TextField magasin;

    @FXML
    private Button boutonAcceuil;

    @FXML
    private Button boutonConfirmation;

    public String getMagasin() {
        return this.magasin.getText();
    }

    public String getMdp() {
        return this.mdp.getText();
    }

    public String getNom() {
        return this.nom.getText();
    }

    public String getPrenom() {
        return this.prenom.getText();
    }

    @FXML
    private void gererAcceuil(ActionEvent event) {
        afficherPopup("menu", "Fonction de retour au menu !");
        this.vue.changerVue("/view/fenetreAdmin1.fxml");

    }

    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void gererCreation(ActionEvent event) throws SQLException{
        //a ajouter: créé un magasin a partir d'un 
        if(nom.getText().isEmpty()||prenom.getText().isEmpty()||mdp.getText().isEmpty()||magasin.getText().isEmpty()){
            afficherPopup("erreur", "Au moins l'un des 4 textField est vide");
        }
        else{
            //System.out.println("nom : " + nom.getText());
            //System.out.println("prenom : " + prenom.getText());
            //System.out.println("mdp : " + mdp.getText());
            //System.out.println("magasin : " + magasin.getText());
            Magasin mag_rech = this.modele.rechercheMagParNom(getMagasin());
            //this.modele.getJDBC().insererVendeur(new Vendeur(getNom(), getPrenom(), getMdp(), mag_rech), getMdp());
            this.modele.createVendeur(getNom(), getPrenom(), getMdp(), mag_rech, this.modele.getJDBC());
            afficherPopup("Creation", "La creation Vendeur a bien reussi ");
            this.vue.changerVue("/view/fenetreAdmini1.fxml");
        }
        
    }
}

