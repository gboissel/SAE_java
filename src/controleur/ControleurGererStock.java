package controleur;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import exception.*;
import javafx.event.ActionEvent;
import modele.*;
import view.*;


public class ControleurGererStock extends Controleur{

    @FXML
    private Button bt1;
    @FXML
    private Button bt2;
    @FXML
    private Button bt3;
    @FXML
    private Button bt4;
    @FXML
    private Button bt5;
    @FXML
    private Button bt6;
    @FXML
    private Button bt7;
    @FXML
    private Button bt8;
    @FXML
    private Button bt9;
    @FXML
    private Button boutonPrec;
    @FXML
    private Button boutonSuiv;
    @FXML
    private TextField textLivre;
    @FXML
    private Button boutonDeconnexion;
    @FXML
    private Button boutonRetour;
    @FXML
    private Button boutonRechercher;
    @FXML
    private Button boutonChoisir;
    @FXML
    private Label textMag;
    @FXML
    private Label textProfil;
    @FXML
    private Label nomPrenom;
    private int numPage;
    private List<Livre> lesLivres;

    @FXML
    private void gererRechercher(ActionEvent event) {
        if(this.textLivre.getText().isEmpty()){
            afficherPopup("erreur", "le livre est mal ecrit ou n'existe pas !!");
        }
        else{
            Livre livre = modele.rechercheLivreParNom(this.textLivre.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, this.vue.infoLivre(livre), ButtonType.NO, ButtonType.YES);
            alert.setTitle("Info Livre");
            if (this.modele.getCurUser().getRoles().equals("Administrateur") || this.modele.getCurMag()==null) {
                alert.setHeaderText("Souhaitez-vous modifier le stock de ce livre dans le magasin ?");
            }
            else {alert.setHeaderText("Souhaitez-vous importer ce livre-ci ?");}
            Optional<ButtonType> reponse = alert.showAndWait();
            if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)) {
                popUpQte(livre);
            }
        }
        
    }   

    @FXML
    private void gererBoutonLivre(ActionEvent event){
        Button boutonClique = (Button) event.getSource(); // Récupère le bouton cliqué
        String texte = boutonClique.getText();
        Livre livre = modele.rechercheLivreParNom(texte);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, this.vue.infoLivre(livre), ButtonType.NO, ButtonType.YES);
        alert.setTitle("Info Livre");
        if (this.modele.getCurUser().getRoles().equals("Administrateur") || this.modele.getCurMag()==null) {
            alert.setHeaderText("Souhaitez-vous modifier le stock de ce livre dans le magasin ?");
        }
        else {alert.setHeaderText("Souhaitez-vous importer ce livre-ci ?");}
        Optional<ButtonType> reponse = alert.showAndWait();
        if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)) {
            popUpQte(livre);
        }
    }

    @FXML
    private void gererDeconnexion(ActionEvent event) {
        Optional<ButtonType> reponse = popUpDeconnexion().showAndWait();
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
    private void gererRetour(ActionEvent event) {
        this.modele.setCurMag(null);
        if (this.modele.getCurUser().getRoles().equals("Administrateur")) {
            this.vue.changerVue("/view/fenetreAdmin1.fxml");
        }
    }

    @FXML
    private void gererChoix(ActionEvent event) {
        afficherPopup("Choix", "Option choisie !");
    }

    @FXML
    private void pagePrec() {
        numPage-=1;
        majAffichage();
    }

    @FXML
    private void pageSuiv() {
        numPage+=1;
        majAffichage();
    }

    private void popUpQte(Livre livre) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Sélection Quantité");
        if (this.modele.getCurUser().getRoles().equals("Administrateur") || this.modele.getCurMag()==null) {
            dialog.setHeaderText("Saisissez la quantité de livre à ajouter au magasin");
            if (this.modele.getCurUser().getRoles().equals("Administrateur")) {
                dialog.setContentText("Quantité actuelle : "+this.modele.getCurMag().getQteLivre(livre));
            }
            else {
                Vendeur vendeur = (Vendeur) this.modele.getCurUser();
                dialog.setContentText("Quantité actuelle : "+vendeur.getMagasin().getQteLivre(livre));
            }
        }
        else {
            Vendeur vendeur = (Vendeur) this.modele.getCurUser();
            dialog.setHeaderText("Saisiseez la quantité du livre à récupérer depuis le magasin sélectionné");
            dialog.setContentText("Quantité actuelle : "+vendeur.getMagasin().getQteLivre(livre)+"\nQuantité récupérable : "+this.modele.getCurMag().getQteLivre(livre));
        }
        ButtonType valider = new ButtonType("Valider", ButtonBar.ButtonData.OK_DONE);
        ButtonType annuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().setAll(valider, annuler);
        Optional<String> resultat = dialog.showAndWait();
        if (resultat.isPresent()) {
            String valeur = resultat.get();
            try {
                int qte = Integer.parseInt(valeur);
                if (this.modele.getCurUser().getRoles().equals("Administrateur")) {this.modele.getCurMag().setQteLivre(livre, this.modele.getCurMag().getQteLivre(livre) + qte, this.modele.getJDBC());}
                else {
                    Vendeur vendeur = (Vendeur) this.modele.getCurUser();
                    if (this.modele.getCurMag()==null) {vendeur.getMagasin().setQteLivre(livre, vendeur.getMagasin().getQteLivre(livre) + qte, this.modele.getJDBC());}
                    else {vendeur.transfererLivre(livre, this.modele.getCurMag(), qte, this.modele.getJDBC());}
                }
                afficherPopup("Info Stock", "La mise à jour du stock a bien été effectuée !");
            }
            catch (NumberFormatException | PasAssezDeLivreException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur : La quantité saisie est invalide !");
                alert.setContentText("La valeur saisie est invalide.\nVérifiez bien que la valeur rentrée est bien correcte.");
            }
        }
    }

    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void afficherLivres() {
        List<Button> boutons = Arrays.asList(bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9);
        for (int i=0; i<boutons.size(); ++i) {
            if (numPage*9 + i < this.lesLivres.size()) {
                boutons.get(i).setDisable(false);
                boutons.get(i).setText(this.lesLivres.get(numPage*9 + i).getTitre());
            }
            else {
                boutons.get(i).setDisable(true);
                boutons.get(i).setText("");
            }
        }
    }

    private void majAffichage() {
        afficherLivres();
        if (numPage==0) {boutonPrec.setDisable(true);}
        else {boutonPrec.setDisable(false);}
        int reste = this.lesLivres.size() % 6;
        int nbPagesMax = this.lesLivres.size() / 6;
        if ((this.numPage == nbPagesMax && reste != 0) || (this.numPage == nbPagesMax - 1 && reste == 0)) {this.boutonSuiv.setDisable(true);}
        else {this.boutonSuiv.setDisable(false);}
    }

    @Override
    public void chargerPage(){
        numPage = 0;
        nomPrenom.setText(this.modele.getCurUser().getNom() + " " + this.modele.getCurUser().getPrenom());
        if (this.modele.getCurUser().getRoles().equals("Administrateur")) {
            textMag.setText(this.modele.getCurMag().getNom()+"\n"+this.modele.getCurMag().getVille());
            this.textProfil.setText("Profil Administrateur");
            this.lesLivres = this.modele.getLivres();
            majAffichage();
        }
        if (this.modele.getCurUser().getRoles().equals("Vendeur")) {
            this.textProfil.setText("Profil Vendeur");
            if (this.modele.getCurMag()==null) {
                Vendeur vendeur = (Vendeur) this.modele.getCurUser();
                this.textMag.setText(vendeur.getMagasin().getNom()+"\n"+vendeur.getMagasin().getVille());
                this.lesLivres = vendeur.getMagasin().getLivres();
                majAffichage();
            }
            else {
                this.textMag.setText(this.modele.getCurMag().getNom()+"\n"+this.modele.getCurMag().getVille());
                this.lesLivres = this.modele.getCurMag().getLivres();
                majAffichage();
            }
        }
    }
}
