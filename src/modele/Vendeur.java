package modele;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.List;

import JDBC.JDBC;
import exception.PasAssezDeLivreException;

public class Vendeur extends Utilisateur{
    private Magasin magasin;
    /**
    * Creer une instance d'un Utilisateur qui est un Vendeur.
    * @param nom
    * @param prenom
    * @param mdp
    * @param magasin
    */
    public Vendeur(String nom,String prenom,String mdp,Magasin magasin){
        super(nom, prenom, mdp);
        this.magasin = magasin;
    }

    /**
     * Méthode permettant de connaître le magasin auquel est relié ce vendeur
     * @return Le magasin
     */
    public Magasin getMagasin() {
        return this.magasin;
    }
    
    /**
     * Ajoute de 1 la quantité disponible d'un livre dans le magasin
     * @param livre Le livre
     * @param jdbc Une instance de la classe permettant d'intéragir avec la base de données
     */
    public void ajouterLivre(Livre livre, JDBC jdbc) {
        this.ajouterLivre(livre, 1, jdbc);
    }

    /**
     * Ajoute un nombre à la quantité disponible d'un livre dans le magasin
     * @param livre Le livre
     * @param qteDispo La quantité que l'on veut rajouter au livre
     * @param jdbc Une instance de la classe permettant d'intéragir avec la base de données
     */
    public void ajouterLivre(Livre livre, int qteDispo, JDBC jdbc) {
        try{
            this.magasin.setQteLivre(livre, this.magasin.getQteLivre(livre) + qteDispo, jdbc);
        }
        catch (PasAssezDeLivreException e) {}
    }

    /**
     * Met à jour la quantité disponible d'un livre en magasin
     * @param livre Le livre
     * @param qteDispo La quantité que l'on veut rajouter au livre
     * @param jdbc Une instance de la classe permettant d'intéragir avec la base de données
     */
    public void miseAJourQteLivre(Livre livre, int qte, JDBC jdbc) {
        try{
            this.magasin.setQteLivre(livre, qte, jdbc);
        }
        catch (PasAssezDeLivreException e) {}
    }

    /**
     * Indique si le livre est disponible en magasin
     * @param livre Le livre
     * @return Si true, alors le stock du livre est strictement supérieur à 0, sinon false
     */
    public boolean disponibiliteLivre(Livre livre) {
        return this.magasin.getQteLivre(livre) > 0;
    }

    /**
     * Permet de prendre la commande en magasin d'un client
     * @param client Le client à qui appartient la commande
     * @param lesLivres Un dictionnaire prenant ayant pour clés des livres et pour valeurs la quantité achetée pour chaque livre
     */
    public void commanderClient(Client client, Map<Livre, Integer> lesLivres, JDBC jdbc) {
        try{
            LocalDateTime dateActuelle = LocalDateTime.now();
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = dateActuelle.format(formatDate);

            Commande commande = new Commande(jdbc.maxNumeroCommande(), date, false, false, client, this.magasin);

            for (Livre livre:lesLivres.keySet()) {
                this.magasin.setQteLivre(livre, lesLivres.get(livre), jdbc);
                commande.addLigne(livre, lesLivres.get(livre), livre.getPrix());
            }
            client.ajouterCommande(commande);
            this.magasin.addCommande(commande);
            jdbc.insererCommande(commande);
        }
        catch (PasAssezDeLivreException e) {}
        catch (SQLException e) {}
    }

    /**
     * Permet, lorsque c'est possible, de récupérer depuis un autre magasin une certaine quantité de livres pour le transférer au magasin auquel le vendeur est rattaché
     * @param livre Le livre
     * @param magasin Le magasin où on récupère les livres
     * @param qte La quantité de livres que l'on transfère
     */
    public void transfererLivre(Livre livre, Magasin magasin, int qte, JDBC jdbc){
        try{
            magasin.setQteLivre(livre, magasin.getQteLivre(livre) - qte, jdbc);
            this.magasin.setQteLivre(livre, this.magasin.getQteLivre(livre) + qte, jdbc);
        }
        catch (PasAssezDeLivreException e) {}
    }

    @Override
    public String getRoles(){
        return "Vendeur";
    }
    
    @Override
    public List<Commande> gestionCommande() {
        return this.magasin.getCommandes();
    }
}