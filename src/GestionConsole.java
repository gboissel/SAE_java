import java.util.Scanner;

public  class GestionConsole{
    public Librairie lib;
    private static Scanner scan;// je le met en attribut car Scanner on ne peut en initialisé qu'un au sein d'un ficher et si on le ferme on ne peut pas en initialisée d'autre.

    public GestionConsole(){
        GestionConsole.scan = new Scanner(System.in);
        this.lib = GestionConsole.initialisation();
        System.out.println("La librairie à été créée");
        this.menuAuth();
    }

    public GestionConsole(Librairie lib) throws PasAdminException{
        if (lib.hasAdmin()){
            this.lib = lib;
            this.menuAuth();
        }else{
            throw new PasAdminException();
        }
    }
    
    private static Librairie initialisation(){

        System.out.println("Création du profil de l'administrateur.");
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
        }
        Administrateur admin = new Administrateur(nom, prenom, mdp);
        Librairie lib = new Librairie(admin);
        return lib;
    }
    private void menuAuth(){
        System.out.println("----------Menu----------");
        System.out.println("-01- Connectez-vous    -");
        System.out.println("-02- Créer un compte   -");
        System.out.println("-03- Catalogue         -");
        System.out.println("-00- Quitter           -");
        System.out.println("------------------------");
        String res = GestionConsole.scan.nextLine();
        if (res.equals("01") || res.equals("1")){
            this.lib.authentificationConsole();
        }else{
            System.out.println("application fermée");
        }
    }
    public Librairie quittez(){
        GestionConsole.scan.close();
        return this.lib;
    }
}