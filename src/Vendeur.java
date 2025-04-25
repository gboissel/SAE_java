public class Vendeur extends Utilisateur{

    /**
    * Creer une instance d'un Utilisateur qui est un Vendeur.
    * @param nom
    * @param prenom
    * @param mdp
    */
    public Vendeur(String nom,String prenom,String mdp){
        super(nom, prenom, mdp);
    }
    public String getRoles(){
        return "Vendeur";
    }
    
}