package controleur;

import java.awt.Label;
import java.util.List;
import modele.*;

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
import javafx.event.ActionEvent;

public class ControleurAdmin1 extends Controleur{

    @FXML
    private Label nomprenom
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
    private Button boutonCreaVendeur;
    @FXML
    private TextField textmois;

    @FXML
    private TextField textannee;

    private int numPage;

    @FXML
    private void gererRechercher(ActionEvent event) {
        afficherPopup("Recherche", "Fonction de recherche déclenchée !");
    }

    @FXML
    private void gererCreamag(ActionEvent event) {
        this.vue.changerVue("/view/ajouterMag.fxml");
    }

    @FXML
    private void gererCreaVendeur(ActionEvent event) {
        this.vue.changerVue("/view/ajouterVendeur.fxml");
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
        String texteMois = this.textmois.getText();
        String texteAnnee = this.textannee.getText();
        try {
            int mois = Integer.parseInt(texteMois);
            int annee = Integer.parseInt(texteAnnee);
            if (mois > 0 && mois <= 12 && texteAnnee.length() == 4) {
                afficherPopupFacture(this.modele.editerFacture(mois, annee));
            }
            else {throw new NumberFormatException();}
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur : Valeurs invalides");
            alert.setContentText("Les valeurs rentrées sont incorrects,\n vérifiez que les valeurs rentrées correspondent bien\n à des mois et des années au format numérique.");
            alert.showAndWait();
        }
    }

    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
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

    /**
     * Permet d'afficher les magasins en fonction du numéro de page
     */
    private void afficherMagasins() {
        List<Magasin> lesMagasins = this.modele.getMagasins();
        if (lesMagasins.size() >= 1+(6*numPage)) {
            boutonChoisir1.setText(lesMagasins.get(0+(6*numPage)).getNom() + "\n" + lesMagasins.get(0+(6*numPage)).getVille());
            boutonChoisir1.setDisable(false);
            if (lesMagasins.size() >= 2+(6*numPage)) {
                boutonChoisir2.setText(lesMagasins.get(1+(6*numPage)).getNom() + "\n" + lesMagasins.get(1+(6*numPage)).getVille());
                boutonChoisir2.setDisable(false);
                if (lesMagasins.size() >= 3+(6*numPage)) {
                    boutonChoisir3.setText(lesMagasins.get(2+(6*numPage)).getNom() + "\n" + lesMagasins.get(2+(6*numPage)).getVille());
                    boutonChoisir3.setDisable(false);
                    if (lesMagasins.size() >= 4+(6*numPage)) {
                        boutonChoisir4.setText(lesMagasins.get(3+(6*numPage)).getNom() + "\n" + lesMagasins.get(3+(6*numPage)).getVille());
                        boutonChoisir4.setDisable(false);
                        if (lesMagasins.size() >= 5+(6*numPage)) {
                            boutonChoisir5.setText(lesMagasins.get(4+(6*numPage)).getNom() + "\n" + lesMagasins.get(4+(6*numPage)).getVille());
                            boutonChoisir5.setDisable(false);
                            if (lesMagasins.size() >= 6+(6*numPage)) {
                                boutonChoisir6.setText(lesMagasins.get(5+(6*numPage)).getNom() + "\n" + lesMagasins.get(5+(6*numPage)).getVille());
                                boutonChoisir6.setDisable(false);
                            }
                            else {
                                boutonChoisir6.setText("");
                                boutonChoisir6.setDisable(true);
                            }
                        }
                        else {
                            boutonChoisir5.setText("");
                            boutonChoisir5.setDisable(true);
                            boutonChoisir6.setText("");
                            boutonChoisir6.setDisable(true);
                        }
                    }
                    else {
                        boutonChoisir3.setText("");
                        boutonChoisir3.setDisable(true);
                        boutonChoisir5.setText("");
                        boutonChoisir5.setDisable(true);
                        boutonChoisir6.setText("");
                        boutonChoisir6.setDisable(true);
                    }
                }
                else {
                    boutonChoisir3.setText("");
                    boutonChoisir3.setDisable(true);
                    boutonChoisir4.setText("");
                    boutonChoisir4.setDisable(true);
                    boutonChoisir5.setText("");
                    boutonChoisir5.setDisable(true);
                    boutonChoisir6.setText("");
                    boutonChoisir6.setDisable(true);
                }
            }
            else {
                boutonChoisir2.setText("");
                boutonChoisir2.setDisable(true);
                boutonChoisir3.setText("");
                boutonChoisir3.setDisable(true);
                boutonChoisir4.setText("");
                boutonChoisir4.setDisable(true);
                boutonChoisir5.setText("");
                boutonChoisir5.setDisable(true);
                boutonChoisir6.setText("");
                boutonChoisir6.setDisable(true);
            }
        }
        else {
            boutonChoisir1.setText("");
            boutonChoisir1.setDisable(true);
            boutonChoisir2.setText("");
            boutonChoisir2.setDisable(true);
            boutonChoisir3.setText("");
            boutonChoisir3.setDisable(true);
            boutonChoisir4.setText("");
            boutonChoisir4.setDisable(true);
            boutonChoisir5.setText("");
            boutonChoisir5.setDisable(true);
            boutonChoisir6.setText("");
            boutonChoisir6.setDisable(true);
        }
    }

    @Override
    public void chargerPage() {
        this.numPage=0;
        boutonDeconnexion.setText("Profil Administrateur\n" + this.modele.getCurUser().getNom() + " " + this.modele.getCurUser().getPrenom() + "\nSe déconnecter");
        this.afficherMagasins();
    }
}

