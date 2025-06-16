package controleur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ConttroleurConnexion implements EventHandler<ActionEvent>{
    /**
     * modèle de l'app
     */
    private MotMystere modele;
    /**
     * vue de l'app
     **/
    private LivreExpress vue;

    /**
     * @param modele modèle du jeu
     * @param p vue du jeu
     */
    public ConttroleurConnexion(MotMystere modele, LivreExpress vue) {
        this.modele=modele;
        this.vue=vue;
    }

    /**
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
    }
}
