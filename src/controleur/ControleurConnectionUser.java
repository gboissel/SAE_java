package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import modele.Administrateur;
import modele.Client;
import modele.Utilisateur;
import modele.Vendeur;
import exception.UtilisateurInexistantException;
import javafx.event.ActionEvent;

public class ControleurConnectionUser extends Controleur{

    private String role_user;
    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField mdp;

    @FXML
    private Button boutonAcceuil;

    @FXML 
    private RadioButton option1;

    @FXML 
    private RadioButton option2;

    @FXML 
    private RadioButton option3;

    @FXML
    private ToggleGroup groupeUtilisateur;

    @FXML
    private Button boutonConfirmation;

    @FXML
    private Button boutonConnexion;


    @FXML
    private void validerChoix() {
    RadioButton selection = (RadioButton) groupeUtilisateur.getSelectedToggle();
    if (selection != null) {
        //System.out.println("Choix : " + selection.getText());
        this.role_user=selection.getText()+"";
    }
    }

    public String getNom(){
        return this.nom.getText();
    }

    public String getPrenom(){
        return this.prenom.getText();
    }

    public String getMdp(){  
        return this.mdp.getText();
    }

    public String getRole(){
        RadioButton selection = (RadioButton) groupeUtilisateur.getSelectedToggle();
        return selection.getText()+"";
    }
    @FXML
    private void gererAcceuil(ActionEvent event) {
        this.vue.changerVue("/view/accueil.fxml");
    }

    @FXML
    private void gererConnexion(ActionEvent event) {

        switch (this.getRole()) {
            case "Client":
                Client tempC = new Client(this.getNom(),this.getPrenom(), null, null, null, this.getMdp());
                if (essaieCo(tempC))
                    this.vue.changerVue("/view/client.fxml");
                break;
            case "Administrateur":
                Administrateur tempA = new Administrateur(this.getNom(), this.getPrenom(), this.getMdp());
                if (essaieCo(tempA))
                this.vue.changerVue("/view/fenetreAdmin1.fxml");
                break;
            case "Vendeur":
                Vendeur tempV = new Vendeur(this.getNom(), this.getPrenom(), this.getMdp(), null);
                if (essaieCo(tempV))
                    this.vue.changerVue("/view/fenetreVendeur.fxml");
                break;
            default:
                this.vue.changerVue("view/accueil.fxml");// retour a l'acceuil
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
                this.modele.setCurUser(this.modele.reccupUser(temp));
                return true;
                }
            }catch(UtilisateurInexistantException exp){
                this.vue.popUpUtilisateurPasTrouve();
            }return false;
        }


}
