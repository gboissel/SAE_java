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

public class ControleurVendeur1_4 extends Controleur {
    private Librairie modele;
    private LivreExpress vue;

    @FXML
    private Button btnDeco;

    @FXML
    private Button btnChoixVendeur;

    @FXML
    private Button btnChoixImporter;

    @FXML
    private Button btnlivrev1;

      @FXML
    private Button btnlivrev2;

      @FXML
    private Button btnlivrev3;

      @FXML
    private Button btnlivrev4;

      @FXML
    private Button btnlivrev5;

      @FXML
    private Button btnlivrev6;

      @FXML
    private Button btnlivrev7 ;
      
    @FXML
    private Button btnlivrev8;

      @FXML
    private Button btnlivrev9;

    @FXML
    private Button btnMagasinV;

    @FXML
    private Button btnRechercheV;


    @FXML
    public void controleurBoutDeco(ActionEvent e){
        System.out.println("vous etes bien deconnecter");
    }

    @FXML
    public void controleurLivrev1(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurLivrev2(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurLivrev3(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurLivrev4(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurLivrev5(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurLivrev6(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurLivrev7(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurLivrev8(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurLivrev9(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurChoixImporter(ActionEvent e){
        System.out.println("vous etes sur le magasin2");
    } 
    @FXML
    public void controleurChoixVendeur(ActionEvent e){
        System.out.println("truc");
    }
    @FXML
    public void controleurMagasinV(ActionEvent e){
        System.out.println("truc");
    }
    @FXML
    public void controleurRechercheV(ActionEvent e){
        System.out.println("truc");
    }


    public void setVue(LivreExpress vue){
        this.vue=vue;
    }
    


}