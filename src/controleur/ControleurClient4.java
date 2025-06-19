package controleur;                                                                          
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modele.*;
import view.*;
import javafx.fxml.FXML;

public class ControleurClient4 extends Controleur{
    private Librairie modele;
    private LivreExpress vue;

    @FXML
    private Button btnDeco;

    @FXML
    private Label montant;

    @FXML
    private TextArea recap;
    
    @FXML
    private Label nomCli;

    @FXML
    private Button btnCatalogue;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnPayer;

    @FXML
    private TextField TextCB;

    @FXML
    private TextField TextDate;

    @FXML
    private TextField TextCSecu;

    @FXML
    private RadioButton btnDomi;

    @FXML
    private RadioButton btnRelai;
                                      
    @FXML
    public void controleurBoutDeco(ActionEvent e){
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
    public void controleurPayer(ActionEvent e){
        System.out.println("Payement");        
    }

    @FXML
    public void controleurCatalogue(ActionEvent e){
        
    }

    @FXML
    public void controleurRetour(ActionEvent e){
        this.vue.changerVue("/view/VuPageClient2");
    }

    @FXML
    public void maJ(){
        this.montant.setText(this.montant.getText()+this.modele.getPanier().prixTotal()+"€");
        this.nomCli.setText(""+this.modele.getCurUser());
    }
}