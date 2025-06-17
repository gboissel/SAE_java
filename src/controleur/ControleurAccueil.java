package controleur;
import view.LivreExpress;
import modele.Librairie;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class ControleurAccueil{
    private Librairie modele;
    private LivreExpress vue;

    @FXML
    private Button boutonRechercher;

    @FXML
    private Button boutonConnexion;

    @FXML
    private Button boutonChoisir;

    @FXML
    private void gererRechercher(ActionEvent event) {
        afficherPopup("Recherche", "Fonction de recherche déclenchée !");
    }

    @FXML
    private void gererConnexion(ActionEvent event) {
        afficherPopup("Connexion", "Redirection vers la page de connexion...");
    }

    @FXML
    private void gererChoix(ActionEvent event) {
        afficherPopup("Choix", "Option choisie !");
    }
    public void setVue(LivreExpress vue){
        this.vue=vue;
    }
    public void setModele(Librairie lib){
        this.modele = lib;
    }
    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

