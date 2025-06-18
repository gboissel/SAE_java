package controleur;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import modele.*;
import view.*;


public class ControleurAccueil extends Controleur{

    @FXML
    private Button bt1;
    @FXML
    private Button bt2;
    @FXML
    private Button bt3;
    @FXML
    private Button bt4;
    @FXML
    private Button bt5;
    @FXML
    private Button bt6;
    @FXML
    private Button bt7;
    @FXML
    private Button bt8;
    @FXML
    private Button bt9;

    @FXML
    private TextField textLivre;
    @FXML
    private Button boutonConnexion;
    @FXML
    private Button boutonRechercher;
    @FXML
    private Button boutonChoisir;

    @FXML
    private void gererRechercher(ActionEvent event) {
        if(this.textLivre.getText().isEmpty()){
            afficherPopup("erreur", "le livre est mal ecrit ou n'existe pas !!");
        }
        else{
            Livre livre = modele.rechercheLivreParNom(this.textLivre.getText());
        afficherPopup("Detail Livre", this.vue.infoLivre(livre));
        }
        
    }   

    @FXML
    private void gererBoutonLivre(ActionEvent event){
        Button boutonClique = (Button) event.getSource(); // Récupère le bouton cliqué
        String texte = boutonClique.getText();
        Livre livre = modele.rechercheLivreParNom(texte);
        afficherPopup("Detail Livre", this.vue.infoLivre(livre));

    }

    @FXML
    private void gererConnexion(ActionEvent event) {
        this.vue.changerVue("/view/connexionUser.fxml");
    }

    @FXML
    private void gererChoix(ActionEvent event) {
        afficherPopup("Choix", "Option choisie !");
    }
    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void chargerPage() throws SQLException{
        List<String> tmp = this.modele.getJDBC().lesXmeilleursVentes(9);
        List<Button> boutons = Arrays.asList(bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9);
        for (int i = 0; i < boutons.size(); i++) {
            if (i < tmp.size()) {
                boutons.get(i).setText(tmp.get(i));
            } 
            else {
                boutons.get(i).setText("N/A");
                boutons.get(i).setDisable(true);
            }
        }

    }
}

