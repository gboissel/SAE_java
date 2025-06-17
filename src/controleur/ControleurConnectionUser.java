package controleur;

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

public class ControleurConnectionUser {
    private LivreExpress vue;  
    private Librairie modele;
    private String role_user;
    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField mdp;

    @FXML
    private Button boutonAcceuil;

    @FXML 
    private RadioButton option1;

    @FXML 
    private RadioButton option2;

    @FXML 
    private RadioButton option3;

    @FXML
    private ToggleGroup groupeUtilisateur;

    @FXML
    private Button boutonConfirmation;

    @FXML
    private Button boutonConnexion;



    public void setModele(Librairie lib){
        this.modele=lib;
    }
    public void setVue(LivreExpress vue){
        this.vue = vue;
    }
    @FXML
    private void validerChoix() {
    RadioButton selection = (RadioButton) groupeUtilisateur.getSelectedToggle();
    if (selection != null) {
        //System.out.println("Choix : " + selection.getText());
        this.role_user=selection.getText()+"";
    }
    }

    public String getNom(){
        return this.nom.getText();
    }

    public String getPrenom(){
        return this.prenom.getText();
    }

    public String getMdp(){
        return this.mdp.getText();
    }

    public String getRole(){
        return this.role_user;
    }
    @FXML
    private void gererAcceuil(ActionEvent event) {
        afficherPopup("menu", "Fonction de retour au menu !");
    }

    @FXML
    private void gererConnexion(ActionEvent event) {
        afficherPopup("connection", "Fonction de connexion !");
        //this.connetion
    }


    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
