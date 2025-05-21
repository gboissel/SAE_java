import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;

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
     * Ajoute de 1 la quantité disponible d'un livre dans le magasin
     * @param livre Le livre
     */
    public void ajouterLivre(Livre livre) {
        this.ajouterLivre(livre, 1);
    }

    /**
     * Ajoute un nombre à la quantité disponible d'un livre dans le magasin
     * @param livre Le livre
     * @param qteDispo La quantité que l'on veut rajouter au livre
     */
    public void ajouterLivre(Livre livre, int qteDispo) {
        try{
            this.magasin.setQteLivre(livre, this.magasin.getQteLivre(livre) + qteDispo);
        }
        catch (PasAssezDeLivre e) {}
    }

    /**
     * Met à jour la quantité disponible d'un livre en magasin
     * @param livre Le livre
     * @param qteDispo La quantité que l'on veut rajouter au livre
     */
    public void miseAJourQteLivre(Livre livre, int qte) {
        try{
            this.magasin.setQteLivre(livre, qte);
        }
        catch (PasAssezDeLivre e) {}
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
    public void commanderClient(Client client, Map<Livre, Integer> lesLivres) {
        try{
            LocalDateTime dateActuelle = LocalDateTime.now();
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = dateActuelle.format(formatDate);

            // A modifier lorsque l'on mettra en place le JDBC pour attribuer un nouveau numéro
            Commande commande = new Commande(new Random().nextInt(1000), date, false, false, client, this.magasin);

            for (Livre livre:lesLivres.keySet()) {
                this.magasin.setQteLivre(livre, lesLivres.get(livre));
                commande.addLigne(livre, lesLivres.get(livre), livre.getPrix());
            }
            client.ajouterCommande(commande);
        }
        catch (PasAssezDeLivre e) {}
    }

    /**
     * Permet, lorsque c'est possible, de récupérer depuis un autre magasin une certaine quantité de livres pour le transférer au magasin auquel le vendeur est rattaché
     * @param livre Le livre
     * @param magasin Le magasin où on récupère les livres
     * @param qte La quantité de livres que l'on transfère
     */
    public void transfererLivre(Livre livre, Magasin magasin, int qte){
        try{
            magasin.setQteLivre(livre, magasin.getQteLivre(livre) - qte);
            this.magasin.setQteLivre(livre, this.magasin.getQteLivre(livre) + qte);
        }
        catch (PasAssezDeLivre e) {}
    }

    @Override
    public String getRoles(){
        return "Vendeur";
    }
    
}