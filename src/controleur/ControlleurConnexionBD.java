package controleur;
import modele.*;
import view.LivreExpress;
import JDBC.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class ControlleurConnexionBD {    
    private LivreExpress vue;
    @FXML
    private Button btnConnexion;
    @FXML
    private TextField txtNomUtilisateur;
    @FXML
    private TextField txtMotDePasse;
    @FXML
    private TextField txtHost;
    @FXML
    private TextField txtNomBase;
    @FXML
    public void handleConnexion() {
        try{
            ConnexionMySQL laConnexion = new ConnexionMySQL();
            laConnexion.connecter(getHost(), getBDname(), getNomUtilisateur(), getMDP());  
        this.vue.initLibrairie(new Librairie(new JDBC(laConnexion)));
        }catch (Exception e) {
            e.printStackTrace();
            // Afficher un message d'erreur à l'utilisateur
            Text errorText = new Text("Erreur de connexion : " + e.getMessage());
            errorText.setFill(javafx.scene.paint.Color.RED);
            // Ajouter le texte d'erreur à la vue, par exemple dans un Label ou une zone de texte
            // this.vue.getPanelCentral().getChildren().add(errorText); // Exemple d'ajout
        }
        
    }
    private String getMDP() {
        return txtMotDePasse.getText();
    }
    private String getNomUtilisateur() {
        return txtNomUtilisateur.getText();
    }
    private String getBDname() {
        return txtNomBase.getText();
    }
    private String getHost() {
        return txtHost.getText();
    }
    public void setVue(LivreExpress vue){
        this.vue = vue;
    }
}