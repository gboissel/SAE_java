package controleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.*;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class ControleurChoixMagasin extends Controleur{

    @FXML
    private Label nomPrenom;
    @FXML
    private Button boutonRechercher;

    @FXML
    private Button boutonSuivant;

    @FXML
    private Button boutonPreced;

    @FXML
    private Button boutonRetour;

    @FXML
    private Button boutonDeconnexion;


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
    private TextField textRecherche;

    private List<Magasin> lesMagasins;

    private int numPage;

    @FXML
    private void gererRechercher(ActionEvent event) {
        String nom = textRecherche.getText();
        textRecherche.setText("");
        Magasin magasin = this.modele.rechercheMagParNom(nom);
        if (magasin!=null) {this.infosMagasin(magasin);}
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur : Le magasin que vous aviez tentez de trouver est introuvable");
            alert.setContentText("Vérifiez que vous entrez bien son nom et qu'il soit bien écrit");
            alert.showAndWait();
        }
    }

    @FXML
    private void gererSuivant(ActionEvent event) {
        this.numPage+=1;
        this.majAffichage();
    }

    @FXML
    private void gererPreced(ActionEvent event) {
        this.numPage-=1;
        this.majAffichage();
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
    private void gererRetour(ActionEvent event) {
        this.modele.setCurMag(null);
        this.vue.changerVue("/view/VuePageVendeur1.fxml");
    }

    @FXML
    private void gererChoix(ActionEvent event) {
        Button bouton = (Button) event.getSource();
        String[] texte = bouton.getText().split("\n");
        String nom = texte[0];
        String ville = texte[1];
        Magasin magasin = this.modele.rechercheMag(nom, ville);
        this.infosMagasin(magasin);
    }

    public Alert popUpDeconnexion(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Voulez vous vraiment vous déconnecter ?\nVous serez renvoyer vers la page d'acceuil", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Attention");
        alert.setHeaderText("Déconnexion");
        return alert;
    }

    /**
     * Affiche les statistiques du magasin
     * @param mag L'identifiant du magasin
     */
    private void infosMagasin(Magasin mag) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Nom : " + mag.getNom() + "\nVille : " + mag.getVille(), ButtonType.NO, ButtonType.YES);
        alert.setTitle("Infos Magasin");
        alert.setHeaderText("Souhaitez-vous importer des livres depuis ce magasin ?");
        Optional<ButtonType> reponse = alert.showAndWait();
        if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)) {
            this.modele.setCurMag(mag);
            this.vue.changerVue("/view/gererStock.fxml");
        }
    }

    /**
     * Permet d'afficher les magasins en fonction du numéro de page
     */
    private void afficherMagasins() {
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
                        } else {
                            boutonChoisir5.setText("");
                            boutonChoisir5.setDisable(true);
                            boutonChoisir6.setText("");
                            boutonChoisir6.setDisable(true);
                        }
                    } else {
                        boutonChoisir3.setText("");
                        boutonChoisir3.setDisable(true);
                        boutonChoisir5.setText("");
                        boutonChoisir5.setDisable(true);
                        boutonChoisir6.setText("");
                        boutonChoisir6.setDisable(true);
                    }
                } else {
                    boutonChoisir3.setText("");
                    boutonChoisir3.setDisable(true);
                    boutonChoisir4.setText("");
                    boutonChoisir4.setDisable(true);
                    boutonChoisir5.setText("");
                    boutonChoisir5.setDisable(true);
                    boutonChoisir6.setText("");
                    boutonChoisir6.setDisable(true);
                }
            } else {
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
        }  else {
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
    /**
     * Permet de mettre à jour l'affichage des magasins lorsque l'on change de page
     */
    public void majAffichage() {
        this.afficherMagasins();
        if (this.numPage == 0) {this.boutonPreced.setDisable(true);}
        else {this.boutonPreced.setDisable(false);}
        int reste = this.lesMagasins.size() % 6;
        int nbPagesMax = this.lesMagasins.size() / 6;
        if ((this.numPage == nbPagesMax && reste != 0) || (this.numPage == nbPagesMax - 1 && reste == 0)) {this.boutonSuivant.setDisable(true);}
        else {this.boutonSuivant.setDisable(false);}
    }

    @Override
    public void chargerPage() {
        this.numPage=0;
        this.lesMagasins = new ArrayList<>(this.modele.getMagasins());
        Vendeur vendeur = (Vendeur) this.modele.getCurUser();
        this.lesMagasins.remove(vendeur.getMagasin());
        nomPrenom.setText(this.modele.getCurUser().getNom() + " " + this.modele.getCurUser().getPrenom());
        this.majAffichage();
    }
}
