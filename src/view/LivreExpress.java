package view;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.FXMLLoader;
import modele.Librairie;
import controleur.*;

public class LivreExpress extends Application{

    private Librairie modele;
    private BorderPane fenetreActuel;//charge le FXML normalement


    @Override
    public void init(){
        
    }
    /**
     * Initialise la librairie ce qui permet d'obtenir le modele MVC en place 
     * @param lib librairie qui est connectée a une base de donnée
     */
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
     * Cette methode permet de change la fenetre courante de maniere efficace grâce à l'herediter mise en place 
     * par la classe Controleur
     * @param fxmlChemin "Chemin d'access au fxml de la forme /view/<nomFichier>.fxml"
     */
    public void changerVue(String fxmlChemin) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlChemin));
            BorderPane newRoot = loader.load();
            Scene newScene = new Scene(newRoot);
            Controleur controleur = loader.getController();
            controleur.setVue(this);
            controleur.setModele(this.getModele());
            controleur.chargerPage();
            // Récupère la stage depuis la scène courante existante
            Stage stage = (Stage) this.fenetreActuel.getScene().getWindow();

            // Change la scène
            stage.setScene(newScene);

            // Met à jour fenetreActuel
            this.fenetreActuel = newRoot;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur de chargement du fichier FXML : " + fxmlChemin);
        }
    }

    public void popUpChampsIncomplet(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText("Connexion impossible ");
        alert.setContentText("Au moins un des champs est vide");
        alert.showAndWait();
    }
    public Alert popUpAide(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Support ");
        alert.setContentText("Si vous avez besoin d'aide consulte le manuel d'utilisation");
        return alert;
    }
    /**
     * Previent l'utilisateur dans le cas où la connexion est impossible 
     * 
     */
    public void popUpUtilisateurPasTrouve(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Attention");
        alert.setHeaderText("Connexion impossible ");
        alert.setContentText("L'utilisateur n'a pas été trouvé dans la base de données");
        alert.showAndWait();
    }

    /**
     * Programme principal
     * @param args inutilisé
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // loader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/logBDpage.fxml"));
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
