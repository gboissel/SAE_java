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

public class ControleurClient3 extends Controleur{
    private Librairie modele;
    private LivreExpress vue;

    @FXML
    private Button btnDeco;

    @FXML
    private Button btnCatalogue;

    @FXML
    private Button btnAvant;
    
    @FXML
    private Button btnSuppr;

    @FXML
    private Button btnSupprPanier;

    @FXML
    private TextField btnAjoute;

    @FXML
    private Button btnRetour;
                                      
    @FXML
    public void controleurBoutDeco(ActionEvent e){
        System.out.println("vous etes bien deconnecter");
    }

    @FXML
    public void controleurAjoute(ActionEvent e){
        System.out.println("Ajoute");  
    }
    @FXML
    public void controleurAjoute2(ActionEvent e){
        System.out.println("Ajoute");  
    }
    @FXML
    public void controleurAjoute3(ActionEvent e){
        System.out.println("Ajoute");  
    }
    @FXML
    public void controleurAjoute4(ActionEvent e){
        System.out.println("Ajoute");  
    }
    @FXML
    public void controleurSuppr(ActionEvent e){
        System.out.println("Suppr");        
    }
     @FXML
    public void controleurSuppr2(ActionEvent e){
        System.out.println("Suppr");        
    }
     @FXML
    public void controleurSuppr3(ActionEvent e){
        System.out.println("Suppr");        
    }
     @FXML
    public void controleurSuppr4(ActionEvent e){
        System.out.println("Suppr");        
    }
    @FXML
    public void controleurSupprPanier(ActionEvent e){
        System.out.println("Suppr Panier");        
    }
    @FXML
    public void controleurSupprPanier2(ActionEvent e){
        System.out.println("Suppr Panier");        
    }
    @FXML
    public void controleurSupprPanier3(ActionEvent e){
        System.out.println("Suppr Panier");        
    }
    @FXML
    public void controleurSupprPanier4(ActionEvent e){
        System.out.println("Suppr Panier");        
    }
    @FXML
    public void controleurAvant(ActionEvent e){
        System.out.println("truc");
    }
    @FXML
    public void controleurCatalogue(ActionEvent e){
        System.err.println("truc");
    }
    @FXML
    public void controleurRetour(ActionEvent e){
        System.out.println("truc");
    }

    public void setVue(LivreExpress vue){
        this.vue=vue;
    }
}