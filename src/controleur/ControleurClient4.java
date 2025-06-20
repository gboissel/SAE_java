package controleur;                                                                          
import java.sql.SQLException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import modele.*;
import javafx.fxml.FXML;

public class ControleurClient4 extends Controleur{
    @FXML
    Panier panier;
    @FXML
    Label nomCli;

    @FXML
    TextField textCB;

    @FXML
    TextField textDate;

    @FXML
    TextField textCS;

    @FXML
    TextArea recap;

    @FXML
    RadioButton btnDomi;

    @FXML
    RadioButton btnRelais;

    @FXML
    Button btnDeco;

    @FXML
    private ToggleGroup groupeLivraison;

    @FXML
    Button btnCata;

    @FXML
    Button btnPayer;
                                      
    @FXML
    Label montant;

    @FXML
    public void BoutDeco(ActionEvent e){
        Optional<ButtonType> reponse = this.popUpDeconnexion().showAndWait();
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
    public void validerCommande(ActionEvent e){
        System.out.println("Payement en cours");
        try{
            Commande commandeCli = new Commande(this.modele.getJDBC().maxNumeroCommande()+1 , null, this.enLigne(), false, (Client) this.modele.getCurUser(), this.modele.getCurMag());
            DetailCommande dCo = null ;
            for (Livre livre:this.panier.keySet()){
                dCo = new DetailCommande(commandeCli, livre, this.panier.get(livre), livre.getPrix());
            }
        }catch(SQLException exceptionSQL){
            exceptionSQL.getMessage();
        }
                
    }

    @FXML
    public void controllerCata(ActionEvent e){
        this.vue.changerVue("/view/VuPageClient2.fxml");
    }

    @FXML
    public String controllerTypeLivraison(){
        RadioButton selection = (RadioButton) groupeLivraison.getSelectedToggle();
        return selection.getText()+"";
    }
    
    private boolean enLigne(){
        return this.controllerTypeLivraison().equals("Domicile");
    }
    @FXML
    public void maJ(){
        this.recap.setText(this.panier.toString());
        this.montant.setText(this.montant.getText()+this.panier.prixTotal()+"€");
        this.nomCli.setText(""+this.modele.getCurUser());
    }

    @Override
    public void chargerPage(){
        this.panier=this.modele.getPanier();
        this.maJ();
    }
}