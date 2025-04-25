
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Authentifieur {
    private List<Utilisateur> users;
    public Authentifieur(){
        this.users=new ArrayList<>();
    }
    /**
     * Créer un client puis l'ajoute à la liste des utilisateur
     * @param nom
     * @param prenom
     * @param mdp
     * 
     */
    public void createClient(String nom,String prenom,String mdp){
        this.users.add(new Client(nom, prenom, mdp));
    }
    /**
     * ajoute un utilisateur déjà existant à la liste des utilisateur 
     * @param user
     */
    public void createUsr(Utilisateur user){
        this.users.add(user);
    }
    /**
     * créer un vendeur et l'ajoute à la liste des utilisateur 
     * @param nom
     * @param prenom
     * @param mdp
     */
    public void createVendeur(String nom,String prenom,String mdp){
        this.users.add(new Vendeur(nom, prenom, mdp));
    }
    
    public boolean authentificationConsole(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("first name: ");
        String fName = scanner.nextLine();
        System.out.println("Qui êtes-vous un Client, un Vendeur,un Administrateur?");
        String role = scanner.nextLine();
        System.out.println("Password: ");
        String pwd = scanner.nextLine();
        if (role.equals("Client")){
            Utilisateur temp = new Client(name,fName , pwd);
        }if (role.equals("Vendeur")){
            Utilisateur temp = new Vendeur(name,fName , pwd);
        }if (role.equals("Administrateur")){
            Utilisateur temp = new Administrateur(name,fName , pwd);
        }else{
            Utilisateur temp = null;
        }
        for (Utilisateur usr:this.users){
            if (usr != null && usr.equals(temp)) 
                return true;
        }return false;
    }
}
