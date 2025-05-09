public class Client extends Utilisateur{
    
    /**
     * Creer une instance d'un Utilisateur qui est un Client
     * @param nom
     * @param prenom
     * @param mdp
     */
    public Client(String nom,String prenom,String mdp){
        super(nom,prenom,mdp);

    }
    @Override
    public String getRoles() {
        return "Client";
    }
    public 
    
    
}
