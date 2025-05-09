import java.util.Scanner;

public  class GestionConsole{
    private boolean run;
    public Librairie lib;
    private static Scanner scan;// je le met en attribut car Scanner on ne peut en initialisé qu'un au sein d'un ficher et si on le ferme on ne peut pas en initialisée d'autre.

    public GestionConsole(){
        this.run = true;
        GestionConsole.scan = new Scanner(System.in);
        this.lib = GestionConsole.initialisation();
        System.out.println("La librairie à été créée");
        this.menuAuth();
    }

    public GestionConsole(Librairie lib) throws PasAdminException{
        if (lib.hasAdmin()){
            this.run = true;
            this.lib = lib;
            this.menuAuth();
        }else{
            throw new PasAdminException();
        }
    }

    public Librairie quittez(){
        this.run = false;
        GestionConsole.scan.close();
        return this.lib;
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
        while (this.run && this.lib.getCurUser()==null){
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
            System.out.println("-00- Déconnection      -");
            System.out.println("------------------------");
            String res = GestionConsole.scan.nextLine();
            switch (res) {
                case "01","1":
                    this.lib.getCurUser().commander();
                    break;
                case "02","2":
                    System.out.println(this.lib.getCurUser().allCommandes();
                default:
                    this.lib.setCurUser(null);
                    System.out.println("vous êtes déconnecter");
                    break;
            }
            if (res.equals("01") || res.equals("1")){
                
            }else if (res.equals("02")||res.equals("2")){
                ;
            }else{
                this.lib.setCurUser(null);
                System.out.println("vous êtes déconnecter");
            }
        }
        this.menuAuth();
    }
    public void menuAdm(){}
    public void menuVend(){}
}