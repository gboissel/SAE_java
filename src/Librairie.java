
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;// il faut tester mais normalement selon la doc ça permet de faire l'équivalent d'un input en python.


public class Librairie {

    private List<Utilisateur> users;


    public Librairie(){
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
    public void createUser(Utilisateur user){
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

    /**
     * renvoie true si l'a connection est correcte l'utilisateur à rentre le bon identifiant et mot de passe
     * @return boolean
     */
    public boolean authentification(String nom,String prenom,String mdp,String role){
        Utilisateur temp = null;
        if (role.equals("Client")){
            temp = new Client(nom,prenom , mdp);
        }if (role.equals("Vendeur")){
            temp = new Vendeur(nom,prenom , mdp);
        }if (role.equals("Administrateur")){
            temp = new Administrateur(nom,prenom,mdp);
        }
        for (Utilisateur usr:this.users){
            if ( usr != null && usr.equals(temp))
                return true;
        }return false;
    }

    /**
     * fonction appeler par un programe en console pour vérifier la connection d'un utilisateur
     * renvoie true si l'a connection est correcte l'utilisateur à rentre le bon identifiant et mot de passe
     * @return boolean
     */
    public boolean authentificationConsole(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name: ");
        String nom = scanner.nextLine();
        System.out.println("first name: ");
        String prenom = scanner.nextLine();
        System.out.println("Qui êtes-vous un Client, un Vendeur,un Administrateur?");
        String role = scanner.nextLine();
        System.out.println("Password: ");
        String mdp = scanner.nextLine();
        scanner.close();
        return this.authentification(nom, prenom, mdp, role);
    }
}
