import java.time.LocalDate;
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

    public void commander(Livre livre, String type, String liaison){
    LocalDate dateActuelle = LocalDate.now();
    this.commandes.add(new Commande(0, liaison, 0, 0, this, null))
    }

    public void modifierCommande(Commande commande, String liaison){

    }

    public List<Commande> consulterCommandes(){  
        return this.commandes;
    }

    public void consulter(){

    }
    
}
