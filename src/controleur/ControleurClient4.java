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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    TextField textCSecu;

    @FXML
    TextArea recap;

    @FXML
    RadioButton btnDomi;

    @FXML
    RadioButton btnRelais;

    @FXML
    Button btnDeco;

    @FXML
    private ToggleGroup groupLivraison;

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
            this.modele.setCurMag(null);
            this.vue.changerVue("/view/accueil.fxml");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Déconnexion");
            alert.setHeaderText("Déconnexion réussie !");
            alert.setContentText("Vous êtes bien retournée sur la page d'accueil");
        }
    }

    @FXML
    public void validerCommande(ActionEvent e){
    if(this.textCB.getText().isEmpty()||this.textCSecu.getText().isEmpty()||this.textDate.getText().isEmpty()){
                afficherPopup("erreur", "Au moin l'un des champ n'est pas complété");
            }
            else{
                try{
            Client client = (Client) this.modele.getCurUser();
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = myDateObj.format(myFormatObj);
            Commande commandeCli = new Commande(this.modele.getJDBC().maxNumeroCommande()+1 , date, true, this.aDomicile(), client, this.modele.getCurMag());
            for (Livre livre:this.panier.keySet()){
                commandeCli.addLigne(livre, this.panier.get(livre), this.panier.prixTotalLivre(livre));
            }
                this.modele.getJDBC().insererCommande(commandeCli);
                client.ajouterCommande(commandeCli);
                this.modele.setPanier(new Panier());
                afficherPopup("Commande validé", "Votre commande a bien été prise en compte \n Vous allez etre redirigé vers la page catalogue");
                this.vue.changerVue("/view/VuePageClient2.fxml");            
            }catch(SQLException SQLException){
                SQLException.getMessage();
            }
        }
    }
    @FXML
    public void controllerCata(ActionEvent e){
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }
    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public String getTypeLivraison(){
        RadioButton selection = (RadioButton) groupLivraison.getSelectedToggle();
        return selection.getText()+"";
    }
    
    private boolean aDomicile(){
        return this.getTypeLivraison().equals("Domicile");
    }
    @FXML
    public void maJ(){
        this.recap.setText(this.panier.toString());
        this.montant.setText(this.montant.getText()+this.panier.prixTotal()+"€");
        this.nomCli.setText(""+this.modele.getCurUser());
    }

    @Override
    public void chargerPage(){
        this.nomCli.setText(this.modele.getCurUser().getNom()+" "+this.modele.getCurUser().getPrenom());
        this.panier=this.modele.getPanier();
        this.maJ();
    }
}