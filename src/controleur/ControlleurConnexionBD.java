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
    private TextField txtBD;
    @FXML
    public void handleConnexion() {
        try{
            ConnexionMySQL laConnexion = new ConnexionMySQL();
            laConnexion.connecter(getHost(), getBDname(), getNomUtilisateur(), getMDP()); 
            System.out.println("Connexion réussie à la base de données"); 
            this.vue.initLibrairie(new Librairie (new JDBC(laConnexion)));
            while (!this.vue.getModele().estChargee()){
                // Attendre que la librairie soit chargée
            }this.vue.changerVue("/view/accueil.fxml");
        }catch (Exception e) {
            e.printStackTrace();
            // Afficher un message d'erreur à l'utilisateur
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
        return txtBD.getText();
    }
    private String getHost() {
        return txtHost.getText();
    }
    public void setVue(LivreExpress vue){
        this.vue = vue;
    }
}
// /usr/share/java/mariadb-java-client.jar
// Pour lancer le programme :
//java -cp bin:/usr/share/java/mariadb-java-client.jar  --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml view.LivreExpress
// Pour compiler le programme :
/* javac -d bin --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml $(find src/exception src/tri src/modele src/JDBC  src/view src/controleur -name "*.java" ! -name "affichageConsole.java") */