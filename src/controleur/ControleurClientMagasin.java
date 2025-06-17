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

public class ControleurClientMagasin {
    private Librairie modele;
    private LivreExpress vue;

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
    public void controleurBoutDeco(ActionEvent e){
        System.out.println("vous etes bien deconnecter");
    }

    @FXML
    public void controleurBoutMag1(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ok");
        alert.setHeaderText(null);
        alert.setContentText("ok");
        alert.showAndWait();
    }

    @FXML
    public void controleurBoutMag2(ActionEvent e){
        System.out.println("vous etes sur le magasin2");
    } 

    @FXML
    public void controleurBoutMag3(ActionEvent e){
        System.out.println("vous etes sur le magasin3");
    } 

    @FXML
    public void controleurBoutMag4(ActionEvent e){
        System.out.println("vous etes sur le magasin4");
    }

    @FXML
    public void controleurBoutMag5(ActionEvent e){
        System.out.println("vous etes sur le magasin5");
    }

    @FXML
    public void controleurBoutMag6(ActionEvent e){
        System.out.println("vous etes sur le magasin6");
    }

    @FXML
    public void controleurBoutMag7(ActionEvent e){
        System.out.println("vous etes sur le magasin7");
    }

    @FXML
    public void controleurBoutMag8(ActionEvent e){
        System.out.println("vous etes sur le magasin8");
    }

    @FXML
    public void controleurBoutMag9(ActionEvent e){
        System.out.println("vous etes sur le magasin9");
    }

    public void setVue(LivreExpress vue){
        this.vue=vue;
    }
    


}
