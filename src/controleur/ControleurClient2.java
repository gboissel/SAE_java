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

public class ControleurClient2 extends Controleur{

    private int numPage;
    private int numPageMax;
    private List<String> tmp;
    @FXML
    private Button btnDeco;
    @FXML
    private Label idClient;
    @FXML
    private Button lb1;
    @FXML
    private Button lb2;
    @FXML
    private Button lb3;
    @FXML
    private Button lb4;
    @FXML
    private Button lb5;
    @FXML
    private Button lb6;
    @FXML
    private Button lb7;
    @FXML
    private Button lb8;
    @FXML
    private Button lb9;
    @FXML
    private Button lb10;

    @FXML
    private Button btnLivre1;

    @FXML
    private Button btnLivre2;

    @FXML
    private Button btnLivre3;

    @FXML
    private Button btnLivre4;

    @FXML
    private Button btPreced;

    @FXML
    private Button btSuivant;

    @FXML
    private Button btnPanier;

    @FXML
    private Button btnRecherche;

    @FXML
    private TextField textRecherche;
                                      
    @FXML
    private void controleurBoutPanier(ActionEvent event){

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
    private void gererRecherchePrecise(ActionEvent event) throws SQLException{
        int nombre;
        if(this.textRecherche.getText().isEmpty()){
            afficherPopup("erreur", "Le champ est vide");
        }
        else{
            try {
                String texte = this.textRecherche.getText().substring(0, 3);
                nombre = Integer.parseInt(texte);
                this.tmp = this.modele.getJDBC().getLivresParClassificationEtMagasinPrecise(nombre, this.modele.getCurMag().getNom());
                this.numPageMax = (int) Math.ceil((double) tmp.size() / 4); // permet de definir le nombre de page maximum
                this.majAffichage();
            } catch (NumberFormatException e) {
                afficherPopup("erreur", "Le champ renseigner n'est pas un nombre ou est invalide");
    }
            
        }
        
    }

    @FXML
    private void gererRecherche(ActionEvent event) throws SQLException{
        Button boutonClique = (Button) event.getSource();
        String texte = boutonClique.getText().substring(0, 3);
        this.tmp = this.modele.getJDBC().getLivresParClassificationEtMagasin(texte, this.modele.getCurMag().getNom()); 
        this.numPageMax = (int) Math.ceil((double) tmp.size() / 4); // permet de definir le nombre de page maximum
        this.majAffichage();
    }

    @FXML
    private void gererDetailLivre(ActionEvent event){
        Button boutonClique = (Button) event.getSource(); // Récupère le bouton cliqué
        String texte = boutonClique.getText();
        Livre livre = modele.rechercheLivreParNom(texte);
        afficherPopup("Detail Livre", this.vue.infoLivre(livre));

    }

    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


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

    /**
     * Permet de mettre à jour l'affichage des magasins lorsque l'on change de page
     */
    public void majAffichage() {
        this.afficherMagasins();
        if (this.numPage == 0) {this.btPreced.setDisable(true);}
        else {this.btPreced.setDisable(false);}
        if (this.numPage == numPageMax) {this.btSuivant.setDisable(true);}
        else {this.btSuivant.setDisable(false);}
    }

    private void afficherMagasins() {
        List<Button> boutons = Arrays.asList(btnLivre1, btnLivre2, btnLivre3, btnLivre4);
        for (int i = 0; i < boutons.size(); i++) {
            if (numPage*4+i < tmp.size()) {
                boutons.get(i).setText(tmp.get(numPage*4+i));
            } 
            else {
                boutons.get(i).setText("N/A");
                boutons.get(i).setDisable(true);
            }
        }
    }

    @Override
    public void chargerPage() {
        this.numPage=0;
        this.idClient.setText(this.modele.getCurUser().getNom() + " " + this.modele.getCurUser().getPrenom());
        //this.majAffichage();
    }
}