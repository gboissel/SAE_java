package controleur;

import JDBC.*;
import java.sql.PreparedStatement;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class ControleurCreationMag extends Controleur{

    @FXML
    private TextField nom;

    @FXML
    private TextField ville;

    @FXML
    private Button boutonAcceuil;

    @FXML
    private Button boutonConfirmation;

    public String getNom(){
        return this.nom.getText();
    }

    public String getVille(){
        return this.ville.getText();
    }

    @FXML
    private void gererAcceuil(ActionEvent event) {
        afficherPopup("menu", "Fonction de retour au menu !");
    }


    @FXML
    private void gererCreation(ActionEvent event) {
        //a ajouter: créé un magasin a partir d'un 
        if(nom.getText().isEmpty()||ville.getText().isEmpty()){
            afficherPopup("erreur", "Au moin l'un des 2 textField est vide");
        }
        else{
            //ConnexionMySQL conn = modele.get
            //PreparedStatement ps = conn.prepareStatement("INSERT INTO MAGASIN(idmag, nommag, villemag) VALUES (?, ?, ?)");
            //int nouvelId = this.modele.getMagasins().size() + 1;
            //ps.setInt(1, nouvelId);
            //ps.setString(2, nom.getText()); 
            //ps.setString(3, ville.getText()); 
            System.out.println("nom : " + nom.getText());
            System.out.println("ville : " + ville.getText());
            afficherPopup("Creation", "La creation a bien reussi ");
        }
        
    }

    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}