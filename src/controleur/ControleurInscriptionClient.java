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


public class ControleurInscriptionClient extends Controleur{
    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField mdp;

    @FXML
    private TextField address;

    @FXML
    private TextField codepostal;

    @FXML
    private TextField ville;

    @FXML
    private Button boutonAcceuil;

    @FXML
    private Button boutonCreation;


    public String getMdp() {
        return this.mdp.getText();
    }

    public String getNom() {
        return this.nom.getText();
    }

    public String getPrenom() {
        return this.prenom.getText();
    }

    public String getVille() {
        return this.ville.getText();
    }

    public String getPostal() {
        return this.codepostal.getText();
    }

    public String getAddress() {
        return this.address.getText();
    }

    @FXML
    private void gererAcceuil(ActionEvent event) {
        afficherPopup("menu", "Fonction de retour au menu !");
        this.vue.changerVue("/view/connexionUser.fxml");

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
<<<<<<< HEAD:src/controleur/ControleurCreaVendeur.java
        if(nom.getText().isEmpty()||prenom.getText().isEmpty()||mdp.getText().isEmpty()||magasin.getText().isEmpty()){
            afficherPopup("erreur", "Au moins l'un des 4 textField est vide");
=======
        if(getNom().isEmpty()||getPrenom().isEmpty()||getAddress().isEmpty()||getPostal().isEmpty()||getMdp().isEmpty()||getVille().isEmpty()){
            afficherPopup("erreur", "Au moin l'un des 4 textField est vide");
>>>>>>> origin/fenetreClient:src/controleur/ControleurInscriptionClient.java
        }
        else{
            this.modele.createClient(getNom(), getPrenom(), getAddress() ,getPostal(),getVille(),getMdp(), this.modele.getJDBC());
            afficherPopup("Creation", "La creation Client a bien reussi \n Vous pouvez maintenant vous connecter");
            this.vue.changerVue("/view/connexionUser.fxml");
        }
        
    }
}

