public class Administrateur extends Utilisateur{
    
    public Administrateur(String nom,String prenom,String mdp){
        super(nom,prenom,mdp);
    }

    @Override
    public String getRoles() {
        return "Administrateur";
    }
}
