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

public class ControleurCreationMag {
    private LivreExpress vue;  
    private Librairie modele;

    @FXML
    private TextField nom;

    @FXML
    private TextField ville;

    @FXML
    private Button boutonAcceuil;

    @FXML
    private Button boutonConfirmation;

    public String getNom(){
        return this.nom.getText();
    }

    public String getVille(){
        return this.ville.getText();
    }

    @FXML
    private void gererAcceuil(ActionEvent event) {
        afficherPopup("menu", "Fonction de retour au menu !");
    }

    public void setVue(LivreExpress vue){
        this.vue = vue;
    }

    @FXML
    private void gererCreation(ActionEvent event) {
        afficherPopup("connection", "Fonction de connexion !");
    }

    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}