package controleur;

import javafx.application.Application;
import javafx.application.Platform;
// import javafx.beans.binding.Bindings;                                       \
                                                                                
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.*;
import view.*;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;

public class ControleurVendeur2 {
    private Librairie modele;
    private LivreExpress vue;

    @FXML
    private Button btnDeco;

    @FXML
    private Button btnRecherche;

     @FXML
    private Button btnLivre;

     @FXML
    private Button btnLivre2;

     @FXML
    private Button btnLivre3;

     @FXML
    private Button btnLivre4;

     @FXML
    private Button btnLivre5;

    @FXML
    private Button btnLivre6;

    @FXML
    private Button btnLivre7;

    @FXML
    private Button btnLivre8;

    @FXML
    private Button btnLivre9;

    @FXML
    private Button btnPanier;

    @FXML
    private Button btnAcceuilChoix;


    @FXML
    public void controleurBoutDeco(ActionEvent e){
        System.out.println("vous etes bien deconnecter");
    }

    @FXML
    public void controleurPanier(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurRecherche(ActionEvent e){
        System.out.println("vous etes sur le magasin2");
    }
    @FXML
    public void controleurLivre(ActionEvent e){
        System.out.println("truc");
    }
     @FXML
    public void controleurLivre2(ActionEvent e){
        System.out.println("truc");
    } 
     @FXML
    public void controleurLivre3(ActionEvent e){
        System.out.println("truc");
    } 
     @FXML
    public void controleurLivre4(ActionEvent e){
        System.out.println("truc");
    } 
     @FXML
    public void controleurLivre5(ActionEvent e){
        System.out.println("truc");
    } 
     @FXML
    public void controleurLivre6(ActionEvent e){
        System.out.println("truc");
    }
    @FXML
    public void controleurLivre7(ActionEvent e){
        System.out.println("truc");
    }
    @FXML
    public void controleurLivre8(ActionEvent e){
        System.out.println("truc");
    }
    @FXML
    public void controleurLivre9(ActionEvent e){
        System.out.println("truc");
    }
    @FXML
    public void controleurAcceuilChoix(ActionEvent e){
        System.out.println("truc");
    }
      


    public void setVue(LivreExpress vue){
        this.vue=vue;
    }
    


}