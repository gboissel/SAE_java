import java.util.List;

public class Administrateur extends Utilisateur{
    private List<Utilisateur> users;
    public Administrateur(String nom,String prenom,String mdp){
        super(nom,prenom,mdp);
    }

    @Override
    public String getRoles() {
        return "Administrateur";
    }
}
