package controleur;

import java.util.Optional;

import exception.UtilisateurInexistantException;
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

public class ControleurVendeur1 extends Controleur {

    @FXML
    private Button btnDeco;

    @FXML
    private Button btnCommande;

    @FXML
    private Button btnImporter;

    @FXML 
    private Label idVendeur;

    @FXML
    private TextField nomCli;
    @FXML
    private TextField prenomCli;
    @FXML
    private TextField mdpCli;

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
    public void controleurCommande(ActionEvent e){
        if(nomCli.getText().isEmpty()||prenomCli.getText().isEmpty()||mdpCli.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Au moin l'un des champs est invalide");
        alert.showAndWait();
        }
        else{
            Client tempC = new Client(nomCli.getText(),prenomCli.getText(), null, null, null, mdpCli.getText());
                if (essaieCo(tempC)){
                    this.vue.changerVue("/view/VuePageClient2.fxml");
                }
                
        }
        
    }

    /**
     * test si la connexion est possible
     * @param temp Utilisateur une variable temporaire representant un faux profile
     * qui essai de se connecter en attendant de savoir s'il en a les permission
     * @return boolean true si connectez false sinon
     */
    private boolean essaieCo(Utilisateur temp){
            try{
                if (this.modele.authentification(temp)){
                    Vendeur vendeur =(Vendeur) this.modele.getCurUser();
                    this.modele.setCurMag(vendeur.getMagasin());
                    this.modele.setCurUser(this.modele.reccupUser(temp));
                return true;
                }
            }catch(UtilisateurInexistantException exp){
                this.vue.popUpUtilisateurPasTrouve();
            }return false;
        }

    @FXML
    public void controleurImporter(ActionEvent e){
        this.vue.changerVue("/view/gererStock.fxml");
    }  

    @Override
    public void chargerPage(){
        idVendeur.setText(this.modele.getCurUser().getNom() + " " + this.modele.getCurUser().getPrenom());
    }
}