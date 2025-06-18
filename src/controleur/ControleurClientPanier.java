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

public class ControleurClientPanier{
    private Librairie modele;
    private LivreExpress vue;

    @FXML
    private Button btnDeco;

    @FXML
    private Button btnCatalogue;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnAvant;

    @FXML
    private Button btnLivre1;

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
    private Button btnRecherche;

    @FXML
    private TextField TextRecherche;
                                      
    @FXML
    public void controleurBoutDeco(ActionEvent e){
        System.out.println("vous etes bien deconnecter");
    }

    @FXML
    public void controleurBoutLivre1(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurBoutLivre2(ActionEvent e){
        System.out.println("vous etes sur le magasin2");
    } 

    @FXML
    public void controleurBoutLivre3(ActionEvent e){
        System.out.println("vous etes sur le magasin3");
    } 

    @FXML
    public void controleurBoutLivre4(ActionEvent e){
        System.out.println("vous etes sur le magasin4");
    }

    @FXML
    public void controleurBoutLivre5(ActionEvent e){
        System.out.println("vous etes sur le magasin5");
    }

    @FXML
    public void controleurBoutLivre6(ActionEvent e){
        System.out.println("vous etes sur le magasin6");
    }

    @FXML
    public void controleurBoutLivre7(ActionEvent e){
        System.out.println("vous etes sur le magasin7");
    }

    @FXML
    public void controleurBoutLivre8(ActionEvent e){
        System.out.println("vous etes sur le magasin8");
    }

    @FXML
    public void controleurBoutLivre9(ActionEvent e){
        System.out.println("vous etes sur le magasin9");
    }

    @FXML
    public void controleurBoutPanier(ActionEvent e){
        System.out.println("vous etes sur le magasin2");
    } 

    @FXML
    public void controleurBoutRecherche(ActionEvent e){
        System.out.println("vous etes sur le magasin2");
    }
    
     @FXML
    public void controleurTextRecherche(ActionEvent e){
        System.out.println("vous etes bien deconnecter");
    }

    public void setVue(LivreExpress vue){
        this.vue=vue;
    }
}