public class Vendeur extends Utilisateur{
    public Vendeur(String nom,String prenom,String mdp){
        super(nom, prenom, mdp);
    }
    public String getRoles(){
        return "Vendeur";
    }
    
}