package view;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.*;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import java.io.File;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import modele.Librairie;
import controleur.ControlleurConnexionBD;
public class LivreExpress extends Application{

    private Librairie modele;
    private BorderPane fenetreActuel;//charge le FXML normalement


    @Override
    public void init(){
        
    }
    public void initLibrairie(Librairie lib){
        this.modele = lib;
    }
    /**
     * @return le modèle de la librairie
     */
    public Librairie getModele(){
        return this.modele;
    }

    /**
     * @return  le graphe de scène de la vue à partir de methodes précédantes
     */
    private Scene laScene(){
        /* BorderPane fenetre = new BorderPane();
        fenetre.setTop(this.bande());
        fenetre.setCenter(this.fenetreActuel); 
        return new Scene(fenetre, 800, 1000); */
        return new Scene(this.fenetreActuel, 800, 1000);
    }


    /**
     * @return le panel contenant le titre du jeu
     */
    private HBox titre(){
        return new HBox();
    }
    
    /**
    * @return la fenêtre d'accueil sur laquelle on peut choisir les paramètres de jeu
    */
    private BorderPane fenetreAccueil(){
        return new BorderPane();
    }

    /**
    * @return la fenêtre d'accueil sur laquelle on peut choisir les paramètres de jeu
    */
    private BorderPane fenetreClient(){
        return new BorderPane();
    }

    /**
    * @return la fenêtre d'accueil sur laquelle on peut choisir les paramètres de jeu
    */
    private BorderPane fenetreVendeur(){
        return new BorderPane();
    }

    /**
    * @return la fenêtre d'accueil sur laquelle on peut choisir les paramètres de jeu
    */
    private BorderPane fenetreAdmini(){
        return new BorderPane();
    }

    /**
    * @return la fenêtre d'accueil sur laquelle on peut choisir les paramètres de jeu
    */
    private BorderPane fenetreConnexion(){
        return new BorderPane();
    }

    public void modeAccueil(){
        //this.panelCentral.setCenter(this.fenetreAccueil());
    }
    
    public void modeClient(){
        //this.panelCentral.setCenter(this.fenetreClient());
    }

    public void modeAdmin(){
        //this.panelCentral.setCenter(this.fenetreAdmini());
    }
    
    public void modeVendeur(){
        //this.panelCentral.setCenter(this.fenetreVendeur());
    }

    public BorderPane bande(){
        return new BorderPane();
    }
    /* 
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Livre Express App - vente de livre");
        stage.setScene(this.laScene());
        this.modeAccueil();
        stage.show();
    } 
    */

    public Alert popUpReglesDuJeu(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Regle du jeu");
        alert.setContentText("Il faut completer le mot pour gagné");
        return alert;
    }

    /**
     * Programme principal
     * @param args inutilisé
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // loader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ConnexionConsole.fxml"));
            BorderPane root = loader.load();
            this.fenetreActuel = root;

            // Init controleur Vue
            ControlleurConnexionBD controleur = loader.getController();
            controleur.setVue(this);
            Scene scene = new Scene(this.fenetreActuel);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Connexion à la base de données");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur de chargement du fichier FXML !");
        }
    }

    public static void main(String[] args) {
        launch(args); 
    }
}
