import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Client extends Utilisateur{
    private List<Commande> commandes;
    /**
     * Creer une instance d'un Utilisateur qui est un Client
     * @param nom
     * @param prenom
     * @param mdp
     */
    public Client(String nom,String prenom,String mdp){
        super(nom,prenom,mdp);
        this.commandes = new ArrayList<>();
    }
    @Override
    public String getRoles() {
        return "Client";
    }

    public void commander(Map<Livre, Integer> lesLivres, boolean enLigne, boolean domicile, Magasin magasin){
        try{
            LocalDateTime dateActuelle = LocalDateTime.now();
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = dateActuelle.format(formatDate);

            // A modifier lorsque l'on mettra en place le JDBC pour attribuer un nouveau numéro
            Commande commande = new Commande(0, date, enLigne, domicile, this, magasin);

            for (Livre livre:lesLivres.keySet()) {
                magasin.setQteLivre(livre, lesLivres.get(livre));
                commande.addLigne(livre, lesLivres.get(livre), livre.getPrix());
            }
        }
        catch (PasAssezDeLivre e) {};
    }

    public void ajouterCommande(Commande commande){
        this.commandes.add(commande);
    }

    public Commande getCommande(int num) throws PasAvoirCommande{
        Commande commandeExemple = new Commande(num, "", false, false, null, null); 
        for (Commande com : this.commandes) {
            if (com.equals(commandeExemple)) {
                return com;
            }
        }
        throw new PasAvoirCommande();
    }

    public void modifierModeLivraisonCommande(Commande commande, boolean domicile){
        commande.setLivraison(domicile);
    }

    public List<Commande> consulterCommandes(){  
        return this.commandes;
    }
    public List<Livre> consulterCatalogueLivre(Magasin mag){
        return mag.getLivres();
    }
}
