import java.util.ArrayList;
import java.util.List;

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

    public void commander(Commande com){
        this.commandes.add(com);
    }

    public void modifierModeLivraisonCommande(Commande commande, String livraison){
        
    }

    public List<Commande> consulterCommandes(){  
        return this.commandes;
    }

    public void consulter(){

    }
    
}
