package controleur;
import modele.*;
import view.LivreExpress;
import JDBC.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


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
        if (!(this.getHost().isEmpty()| this.getBDname().isEmpty() | this.getNomUtilisateur().isEmpty()| this.getBDname().isEmpty())){
            try{
                ConnexionMySQL laConnexion = new ConnexionMySQL();
                laConnexion.connecter(getHost(), getBDname(), getNomUtilisateur(), getMDP()); 
                System.out.println("Connexion réussie à la base de données"); 
                this.vue.initLibrairie(new Librairie (new JDBC(laConnexion)));
                while (!this.vue.getModele().estChargee()){
                    // Attendre que la librairie soit chargée
                }this.vue.changerVue("/view/accueil.fxml");
            }catch (Exception e) {
                popUpConnexionInvalide();
            }
        }else{
            this.vue.popUpChampsIncomplet();
        }
        
    }
    private void popUpConnexionInvalide(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Connexion impossible ");
        alert.setContentText("Erreur lors de la connexion au serveur");
        alert.showAndWait();
    }
    /**
     * Permet d'acceder aux Mot de passe contenu dans le TextFiel
     * @return String Le mot de passe de la connexion a la base de donnée
     */
    private String getMDP() {
        return txtMotDePasse.getText();
    }
    /**
     * Permet d'acceder aux Nom de l'utilisateur contenu dans le TextFiel
     * @return String L'indentifiant de connexion à la base de donnée
     */
    private String getNomUtilisateur() {
        return txtNomUtilisateur.getText();
    }

    /**
     * Permet d'acceder aux Nom de la base de donnée contenu dans le TextFiel
     * @return String Le nom de la Base de donnée
     */
    private String getBDname() {
        return txtBD.getText();
    }
    /**
     * Permet de savoir sur quel reseau dois ce connecter le driver de la base de donnée
     * @return String le réseau sur lequel l'utilisateur se connecte
     */
    private String getHost() {
        return txtHost.getText();
    }
    /**
     * Obligatoire sert a initialiser la vue du modele MVC dans les attribut de ControlleurConnexionBD
     * @param vue
     */
    public void setVue(LivreExpress vue){
        this.vue = vue;
    }
}
// /usr/share/java/mariadb-java-client.jar
// Pour lancer le programme :
//java -cp bin:/usr/share/java/mariadb-java-client.jar  --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml view.LivreExpress
// Pour compiler le programme :
/* javac -d bin --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml $(find src/exception src/tri src/modele src/JDBC  src/view src/controleur -name "*.java" ! -name "affichageConsole.java") */
