package modele;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import JDBC.JDBC;
import exception.PasAssezDeLivreException;

public class Administrateur extends Utilisateur{
    public Administrateur(String nom,String prenom,String mdp){
        super(nom,prenom,mdp);
    }

    /**
     * Méthode permettant de créer un vendeur qui sera associé avec le magasin correspondant
     * @param nom Le nom du vendeur
     * @param prenom Le prénom du vendeur
     * @param mdp Le mot de passe du vendeur
     * @param magasin Le magasin auquel est lié le vendeur
     * @param jdbc Une instance de la classe permettant d'intéragir avec la base de données
     */
    public void creerVendeur(String nom, String prenom, String mdp, Magasin magasin, JDBC jdbc) {
        try {
            Vendeur vendeur = new Vendeur(nom, prenom, mdp, magasin);
            jdbc.insererVendeur(vendeur, mdp);
            magasin.addVendeur(vendeur);
        }
        catch (SQLException e) {}
    }

    /**
     * Méthode permettant de créer un magasin
     * @param nom Le nom du vendeur
     * @param prenom Le prénom du vendeur
     * @param jdbc Une instance de la classe permettant d'intéragir avec la base de données
     */
    public void creerMagasin(String nom, String prenom, JDBC jdbc) {
        try {jdbc.insererMagasin(new Magasin(nom, prenom));}
        catch (SQLException e) {}
    }

    /**
     * Méthode permettant de gérer le stock en magasin
     * @param livre Le livre
     * @param magasin Le magasin
     * @param qte La nouvelle qte du livre
     * @param jdbc Une instance de la classe permettant d'intéragir avec la base de données
     */
    public void gererStockMagasin(Livre livre, Magasin magasin, int qte, JDBC jdbc) {
        try {
            magasin.setQteLivre(livre, qte, jdbc);
        }
        catch (PasAssezDeLivreException e) {}
    }

    @Override
    public String getRoles() {
        return "Administrateur";
    }

    @Override 
    protected List<Commande> gestionCommande() {
        return new ArrayList<Commande>();
    }
}
