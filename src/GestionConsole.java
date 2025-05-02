import java.util.Scanner;

public  class GestionConsole{
    private GestionConsole(){}
    public static void initialisation(){

        System.out.println("Création du profil de l'administrateur.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nom:");
        String nom = scanner.nextLine();//l'input de l'utilisateur
        System.out.println("Prénom: ");
        String prenom = scanner.nextLine();
        String mdp = "";
        String conf = null;
        while(!mdp.equals(conf)){
            System.out.println("Mot de passe");
            mdp = scanner.nextLine();
            System.out.println("Confirmation mot de passe:");
            conf = scanner.nextLine();
        }scanner.close();
        Administrateur admin = new Administrateur(nom, prenom, mdp);
        Librairie lib = new Librairie(admin);
        System.out.println("La librairie à été créée");
        GestionConsole.menuAuth(lib);
    }
    public static void menuAuth(Librairie lib){
        System.out.println("----------Menu----------");
        System.out.println("-01- Connectez-vous    -");
        System.out.println("-02- Créer un compte   -");
        System.out.println("-03- Catalogue         -");
        System.out.println("-00- Quitter           -");
        System.out.println("------------------------");
        Scanner scan = new Scanner(System.in);
        String res = scan.nextLine();
        scan.close();// il faut complétez le document
        if (res.equals("01") || res.equals("1")){
            lib.authentificationConsole();
        }else{
            System.out.println("application fermée");
        }

    }
}