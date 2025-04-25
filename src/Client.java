public class Client extends Utilisateur{

    public Client(String nom,String prenom,String mdp){
        super(nom,prenom,mdp);

    }
    @Override
    public String getRoles() {
        return "Client";
    }
    
}
