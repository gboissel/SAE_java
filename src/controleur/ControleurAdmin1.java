package controleur;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.*;
import javafx.event.ActionEvent;
import modele.*;

public class ControleurAdmin1 {

    private LivreExpress vue;  
    private Librairie modele;
    @FXML
    private Button boutonRechercher;

    @FXML
    private Button boutonDeconnexion;

    @FXML
    private Button boutonEditerFact;

    @FXML
    private Button boutonChoisir1;
    @FXML
    private Button boutonChoisir2;
    @FXML
    private Button boutonChoisir3;
    @FXML
    private Button boutonChoisir4;
    @FXML
    private Button boutonChoisir5;
    @FXML
    private Button boutonChoisir6;
    @FXML
    private Button boutonCreamag;
    @FXML
    private TextField textmois;

    @FXML
    private TextField textannee;

    @FXML
    private void gererRechercher(ActionEvent event) {
        afficherPopup("Recherche", "Fonction de recherche déclenchée !");
    }

    @FXML
    private void gererCreamag(ActionEvent event) {
        afficherPopup("Recherche", "Fonction de creation magasin !");
    }


    @FXML
    private void gererDeconnexion(ActionEvent event) {
        afficherPopup("Connexion", "Redirection vers la page de déconnexion...");
    }

    @FXML
    private void gererChoix(ActionEvent event) {
        afficherPopup("Choix", "Option choisie !");
    }

    @FXML
    private void gererEditerFact(ActionEvent event) {
        afficherPopupFacture();
    }

    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void afficherPopupFacture() {
        // Créer la zone de texte avec beaucoup de contenu
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setText("Ceci est une longue zone de texte...\n".repeat(100)); // Remplissage

        // Mettre la TextArea dans un ScrollPane (optionnel ici car TextArea scrolle déjà)
        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Créer la scène et la fenêtre
        VBox layout = new VBox(scrollPane);
        layout.setPrefSize(400, 300);

        Scene scene = new Scene(layout);
        Stage popupStage = new Stage();
        popupStage.setScene(scene);
        popupStage.setTitle("Popup avec scroll");
        popupStage.initModality(Modality.APPLICATION_MODAL); // bloque la fenêtre principale
        popupStage.showAndWait();
    }

    public void setVue(LivreExpress vue) {
        this.vue = vue;
    }

    public void setModele(Librairie librairie) {
        this.modele = librairie;
    }
}

