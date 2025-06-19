package controleur;

import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import modele.*;
import view.*;

public class Controleur {
    protected Librairie modele;
    protected LivreExpress vue;

    public Librairie getModele() {
        return modele;
    }
    public LivreExpress getVue() {
        return vue;
    }

    public void setModele(Librairie modele) {
        this.modele = modele;
    }

    public void setVue(LivreExpress vue) {
        this.vue = vue;
    }

    public void chargerPage() throws SQLException{
        
    }
    public Alert popUpDeconnexion(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Voulez vous vraiment vous déconnecter ?\nVous serez renvoyer vers la page d'acceuil", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Attention");
        alert.setHeaderText("Déconnexion");
        return alert;
    }
}
