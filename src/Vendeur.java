import java.util.Map;

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

    public void ajouterLivre(Livre livre) {
        this.ajouterLivre(livre, 1);
    }

    public void ajouterLivre(Livre livre, int qteDispo) {
        this.magasin.setQteLivre(livre, this.magasin.getQteLivre(livre) + qteDispo);
    }

    public void miseAJourQteLivre(Livre livre, int qte) {
        this.magasin.setQteLivre(livre, qte);
    }

    public boolean disponibiliteLivre(Livre livre) {
        return this.magasin.getQteLivre(livre) > 0;
    }

    public void commanderClient(Client client, Map<Livre, Integer> lesLivres) {
        // A modifier lorsque l'on mettra en place le JDBC pour attribuer un nouveau numéro
        commande = new Commande(0, , 0, 0, client, magasin)
    }

    public String getRoles(){
        return "Vendeur";
    }
    
}