package vue;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.ButtonBar.ButtonData ;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import java.io.File;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import javax.swing.text.Utilities;
import javax.swing.text.html.ImageView;

public class LivreExpress extends Application{
    
    private BorderPane panelCentral;

    private HBox bande;

    private Button connection;

    private Button création;

    private ImageView logo;

    private Label titre_app;

    private BorderPane fenetreAccueil;

    private BorderPane fenetreClient;

    private BorderPane fenetreVendeur;

    private BorderPane fenetreAdmini;

    @Override
    public void init(){
        this.panelCentral = new BorderPane();
        this.fenetreAccueil = new BorderPane();
        this.fenetreAdmini = new BorderPane();
        this.fenetreVendeur = new BorderPane();
        this.fenetreClient = new BorderPane();
    }

    /**
     * @return  le graphe de scène de la vue à partir de methodes précédantes
     */
    private Scene laScene(){
        BorderPane fenetre = new BorderPane();
        fenetre.setTop(this.bande());
        fenetre.setCenter(this.panelCentral); 
        return new Scene(fenetre, 800, 1000);
    }


    /**
     * @return le panel contenant le titre du jeu
     */
    private HBox titre(){

    }

    /**
    * @return la fenêtre d'accueil sur laquelle on peut choisir les paramètres de jeu
    */
    private BorderPane fenetreAccueil(){

    }

    /**
    * @return la fenêtre d'accueil sur laquelle on peut choisir les paramètres de jeu
    */
    private BorderPane fenetreClient(){

    }

    /**
    * @return la fenêtre d'accueil sur laquelle on peut choisir les paramètres de jeu
    */
    private BorderPane fenetreVendeur(){

    }

    /**
    * @return la fenêtre d'accueil sur laquelle on peut choisir les paramètres de jeu
    */
    private BorderPane fenetreAdmini(){

    }

    public void modeAccueil(){
        this.panelCentral.setCenter(this.fenetreAccueil());
    }
    
    public void modeClient(){
        this.panelCentral.setCenter(this.fenetreClient());
    }

    public void modeAdmin(){
        this.panelCentral.setCenter(this.fenetreAdmini());
    }
    
    public void modeVendeur(){
        this.panelCentral.setCenter(this.fenetreVendeur());
    }


    /**
     * créer le graphe de scène et lance le jeu
     * @param stage la fenêtre principale
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Livre Express App - vente de livre");
        stage.setScene(this.laScene());
        this.modeAccueil();
        stage.show();
    }

    /**
     * Programme principal
     * @param args inutilisé
     */
    public static void main(String[] args) {
        launch(args);
    }
}
