package controleur;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
        popUpDeconnexion().showAndWait();
    }

    @FXML
    private void gererChoix(ActionEvent event) {
        afficherPopup("Choix", "Option choisie !");
    }

    @FXML
    private void gererEditerFact(ActionEvent event) {
        String texte1 = this.textmois.getText();
        String texte2 = this.textannee.getText();
        try {
            int valeur = Integer.parseInt(texte1);
            int valeur2 = Integer.parseInt(texte2);
            //this.modele.editerFacture(valeur, valeur2);
            //afficherPopupFacture(this.modele.editerFacture(valeur, valeur2));     //mettre un return sur editerFacture  ou utiliser le txt
        } catch (NumberFormatException e) {
            System.out.println("Le texte n'est pas un nombre valide !");
                }
    }

    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Alert popUpDeconnexion(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Voulez vous vraiment vous deconnecter ?\nVous serez renvoyer vers la page d'acceuil", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Attention");
        alert.setHeaderText("Confirmation");
        alert.setContentText("La partie est en cours! \n Etes-vous sûr de l'interompre ?");
        return alert;
    }

    private void afficherPopupFacture(String texte) {
        // Créer la zone de texte avec beaucoup de contenu
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setText(texte);

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

