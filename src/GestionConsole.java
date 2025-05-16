import java.util.Scanner;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
public  class GestionConsole{
    private boolean run;
    public Librairie lib;
    private static Scanner scan;// je le met en attribut car Scanner on ne peut en initialisé qu'un au sein d'un ficher et si on le ferme on ne peut pas en initialisée d'autre.

    /* private GestionConsole(){
    *    this.run = true;
    *    GestionConsole.scan = new Scanner(System.in);
    *    this.lib = GestionConsole.initialisation();
    *    System.out.println("La librairie à été créée");
    *    this.menuAuth();
    *}
    */
    public GestionConsole(){
        try{
            GestionConsole.scan = new Scanner(System.in);
            ConnexionMySQL connec = new ConnexionMySQL();
            List<String> attCo = this.connexConsole();
            connec.connecter(attCo.get(0),attCo.get(1),attCo.get(2));
            System.out.println("Connexion REUSSI!!!!");
            this.run = true;
            this.menuAuth();
        }catch(SQLException exp){
            System.out.println("erreur sql");
        }catch(ClassNotFoundException exp1){
            System.out.println("erreur connexion jdbc");
        }

    }

    public Librairie quittez(){
        this.run = false;
        GestionConsole.scan.close();
        return this.lib;
    }
    public boolean estConnecte(){
        return this.lib.getCurUser() != null;
    }
    public boolean estUtilise(){
        return this.run;
    }
    private List<String> connexConsole(){
        List<String> res = new ArrayList<>();
        System.out.println("Nom Base:");
        res.add(""+GestionConsole.scan.nextLine());
        System.out.println("Login: ");
        res.add(""+GestionConsole.scan.nextLine());
        System.out.println("Mot de passe");
        res.add(""+GestionConsole.scan.nextLine());
        System.out.println();
        return res;
    }
    private static Librairie initialisation(){//cette fonction était censé etre capable de créer une base de donné pour le momment c'est pas le cas

        System.out.println("Création du profil de l'administrateur.");
        System.out.println("Nom:");
        String nom = GestionConsole.scan.nextLine();//l'input de l'utilisateur
        System.out.println("Prénom: ");
        String prenom = GestionConsole.scan.nextLine();
        String mdp = "";
        String conf = null;// pour la confirmation 
        while(!mdp.equals(conf)){
            System.out.println("Mot de passe");
            mdp = GestionConsole.scan.nextLine();
            System.out.println("Confirmation mot de passe:");
            conf = GestionConsole.scan.nextLine();
        }
        Administrateur admin = new Administrateur(nom, prenom, mdp);
        Librairie lib = new Librairie(admin);
        return lib;
    }

    public void creaClientCons(){
        System.out.println("Nom:");
        String nom = GestionConsole.scan.nextLine();//l'input de l'utilisateur
        System.out.println("Prénom: ");
        String prenom = GestionConsole.scan.nextLine();
        String mdp = "";
        String conf = null;
        while(!mdp.equals(conf)){
            System.out.println("Mot de passe");
            mdp = GestionConsole.scan.nextLine();
            System.out.println("Confirmation mot de passe:");
            conf = GestionConsole.scan.nextLine();
        }this.lib.createClient(nom, prenom, mdp);
    }

    private void menuAuth(){
        while (this.estUtilise() && !this.estConnecte()){
            System.out.println("----------Menu----------");
            System.out.println("-01- Connectez-vous    -");
            System.out.println("-02- Créer un compte   -");
            System.out.println("-03- Catalogue         -");
            System.out.println("-00- Quitter           -");
            System.out.println("------------------------");
            String res = GestionConsole.scan.nextLine();
            switch (res) {
                case "01","1":
                    this.lib.authentificationConsole();
                    break;
                case "02","2":
                    this.creaClientCons();
                    break;
                default:
                    this.quittez();
                    System.out.println("application fermée");
            }
        if (this.lib.getCurUser().getRoles().equals("Client")) this.menuCli();
        else if(this.lib.getCurUser().getRoles().equals("Vendeur")){
            this.menuVend();
            }else{this.menuAdm();}
        // si différent de null renvoie un autre menu qui correspondera a ce que peut faire l'utilisateur.
        }
    }
    public void menuCli(){
        while (this.lib.getCurUser().getRoles().equals("Client")){
            System.out.println("-------Menu Client------");
            System.out.println("-01- Commander         -");
            System.out.println("-02- Consulter commande-");
            System.out.println("-03- Catalogue         -");
            System.out.println("-04- Recommandation    -");
            System.out.println("-00- Déconnection      -");
            System.out.println("------------------------");
            String res = GestionConsole.scan.nextLine();
            Client leclient = (Client) this.lib.getCurUser();
            switch (res) {
                case "01","1":
                    this.menuCommander();
                    break;
                case "02","2":
                    System.out.println(leclient.consulterCommandes());
                default:
                    this.lib.setCurUser(null);
                    System.out.println("vous êtes déconnecter");
                    break;
            }this.menuAuth();
        }
    }
    public void menuCommander(){
        
        System.out.println("Indiquez la recherche de quel type (Par defaut vous consultez le catalogue):");
        System.out.println("--------Creation de votre commande-------");
        System.out.println("-01- CATALOGUE                          -");
        System.out.println("-02- ISBN                               -");
        System.out.println("-03- AUTEUR                             -");
        System.out.println("-04- TITRE                              -");
        System.out.println("-00- Valider commande                   -");
        System.out.println("-05- Annuler la commande                -");//mettre recomandation dans l'affichage du catalogue
        System.out.println("-----------------------------------------");
        String res = GestionConsole.scan.nextLine();
        Client leclient = (Client) this.lib.getCurUser();
        switch (res) {
                case "0","00":
                    this.menuAuth();
                    break;
                case "01","1":
                    this.catalogue();
                    break;
                case "02","2":
                    System.out.println(leclient.consulterCommandes());
                    break;
                default:
                    this.catalogue();
                    break;
            }
    }

    public void catalogue(){}
    public void menuAdm(){}
    public void menuVend(){}
}