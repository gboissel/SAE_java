package controleur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import modele.*;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;

public class ControleurClient3 extends Controleur {
    private int numPage;
    private int numPageMax;
    @FXML
    private Button btnDeco;

    @FXML
    private GridPane grid;

    @FXML
    private Button btnCatalogue;

    @FXML
    private Button btPreced;

    @FXML
    private Button btSuivant;

    @FXML
    private Button btnPayement;

    @FXML
    private Label idClient;

    @FXML
    private Label totalPanier;

    @FXML
    private Button l1;
    @FXML
    private Button l2;
    @FXML
    private Button l3;
    @FXML
    private Button l4;
    @FXML
    private Label q1;
    @FXML
    private Label q2;
    @FXML
    private Label q3;
    @FXML
    private Label q4;
    @FXML
    private Label p1;
    @FXML
    private Label p2;
    @FXML
    private Label p3;
    @FXML
    private Label p4;
    @FXML
    private Label t1;
    @FXML
    private Label t2;
    @FXML
    private Label t3;
    @FXML
    private Label t4;
    @FXML
    private Button a1;
    @FXML
    private Button a2;
    @FXML
    private Button a3;
    @FXML
    private Button a4;
    @FXML
    private Button s1;
    @FXML
    private Button s2;
    @FXML
    private Button s3;
    @FXML
    private Button s4;
    @FXML
    private Button d1;
    @FXML
    private Button d2;
    @FXML
    private Button d3;
    @FXML
    private Button d4;


    /**
     * pemret de recuperer l'indice de la ligne d'un bouton, quand on clic dessus
     * @param event
     * @param gridPane
     * @return
     */
    public int getlignedepuisEvent(ActionEvent event) {
        Node source = (Node) event.getSource(); // bouton cliqué
        return GridPane.getRowIndex(source);
    }

    /**
     * permet de recuperer un element de gridPane en fonction des coordonées
     * @param grid
     * @param col
     * @param row
     * @return
     */
    public Node getNode(GridPane grid, int col, int row) {
    for (Node node : grid.getChildren()) {
        Integer colIndex = GridPane.getColumnIndex(node);
        Integer rowIndex = GridPane.getRowIndex(node);
        int c = (colIndex != null) ? colIndex : 0;
        int r = (rowIndex != null) ? rowIndex : 0;
        if (c == col && r == row) {
            return node;
        }
    }
    return null;
}


    public Alert popUpDeconnexion(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Voulez vous vraiment vous déconnecter ?\nVous serez renvoyer vers la page d'acceuil", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Attention");
        alert.setHeaderText("Déconnexion");
        return alert;
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
    private void gererPreced(ActionEvent event){
        if(this.numPage>0) {
            this.numPage-=1;
            this.majAffichage();
        }
    }

    @FXML
    private void gererSuivant(ActionEvent event){
        if (this.numPage<this.numPageMax) {
            this.numPage+=1;
            this.majAffichage();
        }
    }

    @FXML
    private void gererPayement(ActionEvent event){
        this.vue.changerVue("/view/VuePageClient4.fxml");
    }
    @FXML
    private void gererRetourCatalogue(ActionEvent event){
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }

    @FXML
    private void gererAjouter(ActionEvent event){
        int ligne = getlignedepuisEvent(event);
        Node elem = getNode(this.grid, 0, ligne);
        Button tmp_titre = (Button) elem;
        Livre tmp_livre = this.modele.rechercheLivreParNomParMagasin(tmp_titre.getText());
        if((this.modele.getCurMag().getQteLivre(tmp_livre)<=this.modele.getPanier().get(tmp_livre))){
            afficherPopup("Erreur","Il n'y a plus assez de stock dans le magasin");
        }
        else{
            this.modele.getPanier().ajouter(tmp_livre, 1);
        }
        this.majAffichage();
    }

    @FXML
    private void gererSupprimer(ActionEvent event){
        int ligne = getlignedepuisEvent(event);
        Node elem = getNode(this.grid, 0, ligne);
        Button tmp_titre = (Button) elem;
        Livre tmp_livre = this.modele.rechercheLivreParNomParMagasin(tmp_titre.getText());
        this.modele.getPanier().retirerQte(tmp_livre, 1);
        this.majAffichage();
    }
    @FXML
    private void gererEnlever(ActionEvent event){
        int ligne = getlignedepuisEvent(event);
        Node elem = getNode(this.grid, 0, ligne);
        Button tmp_titre = (Button) elem;
        Livre tmp_livre = this.modele.rechercheLivreParNomParMagasin(tmp_titre.getText());
        this.modele.getPanier().supprimer(tmp_livre);
        this.majAffichage();
    }
    
    @FXML
    private void gererDetailLivre(ActionEvent event){
        this.numPage=0;
        Button boutonClique = (Button) event.getSource(); // Récupère le bouton cliqué
        String texte = boutonClique.getText();
        Livre livre = modele.rechercheLivreParNom(texte);
        afficherPopup("Detail Livre", this.vue.infoLivre(livre));

    }

    private void afficherPopup(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void afficherPanier(){
        Panier tmp = this.modele.getPanier();
        List<Livre> cles = new ArrayList<>(tmp.keySet());
        this.numPageMax = (int) Math.ceil((double) cles.size() / 4); // permet de definir le nombre de page maximum
        List<Button> l = Arrays.asList(l1,l2,l3,l4);
        List<Label> q = Arrays.asList(q1,q2,q3,q4);
        List<Label> p = Arrays.asList(p1,p2,p3,p4);
        List<Label> t = Arrays.asList(t1,t2,t3,t4);
        List<Button> a = Arrays.asList(a1,a2,a3,a4);
        List<Button> s = Arrays.asList(s1,s2,s3,s4);
        List<Button> d = Arrays.asList(d1,d2,d3,d4);
        this.totalPanier.setText(""+tmp.prixTotal());
        for (int i = 0; i < 4; i++) {
            if (numPage*4+i < cles.size()) {
                Livre tmp_livre=cles.get(numPage*4+i);
                l.get(i).setText(tmp_livre.getTitre());
                l.get(i).setDisable(false);
                a.get(i).setDisable(false);
                s.get(i).setDisable(false);
                d.get(i).setDisable(false);
                q.get(i).setText(""+tmp.get(tmp_livre));
                p.get(i).setText(""+tmp_livre.getPrix());
                t.get(i).setText(""+tmp.prixTotalLivre(tmp_livre));
            } 
            else {
                l.get(i).setText("N/A");
                l.get(i).setDisable(true);
                l.get(i).setDisable(true);
                a.get(i).setDisable(true);
                s.get(i).setDisable(true);
                d.get(i).setDisable(true);
                q.get(i).setText("N/A");
                p.get(i).setText("N/A");
                t.get(i).setText("N/A");
            }
        }
    }

    private void majAffichage(){
        this.afficherPanier();
        if (this.numPage == 0) {this.btPreced.setDisable(true);}
        else {this.btPreced.setDisable(false);}
        if (this.numPage == numPageMax-1) {this.btSuivant.setDisable(true);}
        else {this.btSuivant.setDisable(false);}
        }

    @Override
    public void chargerPage(){
        this.idClient.setText(this.modele.getCurUser().getNom() + " " + this.modele.getCurUser().getPrenom());
        this.numPage=0;
        List<Button> l = Arrays.asList(l1,l2,l3,l4);
        List<Button> a = Arrays.asList(a1,a2,a3,a4);
        List<Button> s = Arrays.asList(s1,s2,s3,s4);
        List<Button> d = Arrays.asList(d1,d2,d3,d4);
        for(int i =0;i<4;i++){
            l.get(i).setDisable(true);
            a.get(i).setDisable(true);
            s.get(i).setDisable(true);
            d.get(i).setDisable(true);
        }
        this.majAffichage();
    }
}