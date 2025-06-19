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
import javafx.scene.control.RadioButton;
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

public class ControleurVendeur4 extends Controleur{
    private Librairie modele;
    private LivreExpress vue;

    @FXML
    private Button btnDeco;

    @FXML
    private Button btnCatalogue;

    @FXML
    private Button btnPayer;

    @FXML
    private TextField TextCB;

    @FXML
    private TextField TextDate;

    @FXML
    private TextField TextCSecu;

    @FXML
    private RadioButton btnDomi;

    @FXML
    private RadioButton btnRelai;
                                      
    @FXML
    public void controleurBoutDeco(ActionEvent e){
        System.out.println("vous etes bien deconnecter");
    }
    @FXML
    public void controleurPayer(ActionEvent e){
        System.out.println("Payement");        
    }
    @FXML
    public void controleurCatalogue(ActionEvent e){
        System.out.println("truc");
    }

    public void setVue(LivreExpress vue){
        this.vue=vue;
    }
}