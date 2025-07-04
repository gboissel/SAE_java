package controleur;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.Client;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;

public class ControleurClient1 extends Controleur{
    private int nbPage = 0;
    @FXML
    private Button btnDeco;

    @FXML
    private Button btnMag1;

    @FXML
    private Button btnMag2;

    @FXML
    private Button btnMag3;

    @FXML
    private Button btnMag4;

    @FXML
    private Button btnMag5;

    @FXML
    private Button btnMag6;
    
    @FXML
    private Button btnMag7;

    @FXML
    private Button btnMag8;

    @FXML
    private Button btnMag9;

    @FXML
    private Button btnPrec;

    @FXML
    private Button btnSuiv;

    @FXML
    private Button aideB;

    @FXML
    private Button boutonCom;

    @FXML
    private Label idClient;

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
    public void aideHandler(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Aide");
        alert.setHeaderText("Selectionner un magasin pour consulter les livre du magasin");
        alert.setContentText("Lorsque vous sélectionnerez un magasin, vous arriverez sur une page\n" +
            "présentant tous les livres du magasin ainsi que la possibilité de rechercher un livre en particulier.");
        alert.showAndWait();
    }

    @FXML
    public void gererCom(ActionEvent e) {
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        Client client = (Client) this.modele.getCurUser();
        textArea.setText(this.modele.commandesClient(client));
        textArea.setEditable(false);

        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        VBox layout = new VBox(scrollPane);
        layout.setPrefSize(400, 300);

        Scene scene = new Scene(layout);
        Stage popupStage = new Stage();
        popupStage.setScene(scene);
        popupStage.setTitle("Consultation commandes");
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();
    }

    @FXML
    public void controleurBoutMag1(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(0+this.nbPage*9));     
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }

    @FXML
    public void controleurBoutMag2(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(1+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    } 

    @FXML
    public void controleurBoutMag3(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(2+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    } 

    @FXML
    public void controleurBoutMag4(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(3+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }

    @FXML
    public void controleurBoutMag5(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(4+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }

    @FXML
    public void controleurBoutMag6(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(5+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }

    @FXML
    public void controleurBoutMag7(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(6+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }

    @FXML
    public void controleurBoutMag8(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(7+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }

    @FXML
    public void controleurBoutMag9(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(8+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }
    @FXML
    public void pagePrec(){
        if (this.nbPage>0) {
            this.nbPage-=1;
            btnSuiv.setDisable(false);
            this.maJ();
        }

    }

    @FXML
    public void pageSuivante(){
        this.nbPage+=1;
        this.maJ();
    }

    private void maJ(){
        int dep = nbPage*9;
        int compteur = 0;
        String nomMag;
        List<Button> btnL = Arrays.asList(this.btnMag1,this.btnMag2,this.btnMag3,this.btnMag4,this.btnMag5,this.btnMag6,this.btnMag7,this.btnMag8,this.btnMag9);
        for(Button btn:btnL){
            if (compteur<10 && (nbPage*9+compteur)<this.modele.getMagasins().size()){
                nomMag = this.modele.getMagasins().get(dep+compteur).getNom();
                btn.setText(nomMag);
                btn.setDisable(false);
            }else{
                btn.setDisable(true);
                btn.setText("N/A");
                btnSuiv.setDisable(true);
            }++compteur;
        }if (nbPage == 0)
            btnPrec.setDisable(true);
        else
            btnPrec.setDisable(false);
    }              

    @Override
    public void chargerPage(){
        this.nbPage = 0;
        this.idClient.setText(this.modele.getCurUser().getNom() + " " + this.modele.getCurUser().getPrenom());
        this.maJ();
    }
}
