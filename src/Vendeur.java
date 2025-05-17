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

    public void ajouterLivre(Livre livre) {
        this.ajouterLivre(livre, 1);
    }

    public void ajouterLivre(Livre livre, int qteDispo) {
        try{
            this.magasin.setQteLivre(livre, this.magasin.getQteLivre(livre) + qteDispo);
        }
        catch (PasAssezDeLivre e) {}
    }

    public void miseAJourQteLivre(Livre livre, int qte) {
        try{
            this.magasin.setQteLivre(livre, qte);
        }
        catch (PasAssezDeLivre e) {}
    }

    public boolean disponibiliteLivre(Livre livre) {
        return this.magasin.getQteLivre(livre) > 0;
    }

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