package view;
import JDBC.*;
import modele.*;
import java.util.Scanner;

import exception.UtilisateurInexistantException;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
public  class affichageConsole{
    private boolean run;
    public Librairie lib;
    private JDBC jdbc;
    private static Scanner scan;// je le met en attribut car Scanner on ne peut en initialisé qu'un au sein d'un ficher et si on le ferme on ne peut pas en initialisée d'autre.

    /* private GestionConsole(){
    *    this.run = true;
    *    GestionConsole.scan = new Scanner(System.in);
    *    this.lib = GestionConsole.initialisation();
    *    System.out.println("La librairie à été créée");
    *    this.menuAuth();
    *}
    */
    public affichageConsole(){
        try{
            System.out.println("Initialisation de l'App ...");
            affichageConsole.scan = new Scanner(System.in);
            ConnexionMySQL connexion = new ConnexionMySQL();
            List<String> attCo = this.connexConsole();//attribut de la connexionMSQL
            connexion.connecter(attCo.get(0),attCo.get(1),attCo.get(2),attCo.get(3));
            this.jdbc = new JDBC(connexion);
            System.out.println("Connexion REUSSI!!!!");
            this.run = true;
            this.lib = new Librairie(new Administrateur("ad", "ad", "1234"));
            this.menuAuth();
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
            System.out.println("erreur sql");
        }catch(ClassNotFoundException exp1){
            System.out.println(exp1.getMessage());
            System.out.println("erreur connexion jdbc");
        }

    }

    public void quittez(){
        this.run = false;
        affichageConsole.scan.close();
        System.out.println("Application fermer");
    }
    public boolean estConnecte(){
        return this.lib.getCurUser() != null;
    }
    public boolean estUtilise(){
        return this.run;
    }
    private List<String> connexConsole(){
        List<String> res = new ArrayList<>();
        System.out.println("Driver: (IUT:servinfo-maria)");
        res.add(""+affichageConsole.scan.nextLine());
        System.out.println("Nom Base:");
        res.add(""+affichageConsole.scan.nextLine());
        System.out.println("Login: ");
        res.add(""+affichageConsole.scan.nextLine());
        System.out.println("Mot de passe");
        res.add(""+affichageConsole.scan.nextLine());
        System.out.println();
        return res;
    }
    //private static Librairie initialisation(){//cette fonction était censé etre capable de créer une base de donné pour le momment c'est pas le cas

        //System.out.println("Création du profil de l'administrateur.");
        //System.out.println("Nom:");
        //String nom = affichageConsole.scan.nextLine();//l'input de l'utilisateur
        //System.out.println("Prénom: ");
        //String prenom = affichageConsole.scan.nextLine();
        //String mdp = "";
        //String conf = null;// pour la confirmation 
        //while(!mdp.equals(conf)){
        //    System.out.println("Mot de passe");
        //    mdp = affichageConsole.scan.nextLine();
        //    System.out.println("Confirmation mot de passe:");
        //    conf = affichageConsole.scan.nextLine();
        //}
        //Administrateur admin = new Administrateur(nom, prenom, mdp);
        //Librairie lib = new Librairie(admin);
        //return lib;
    ////}

    /**
     * fonction appeler par un programe en console pour vérifier la connection d'un utilisateur
     * renvoie true si l'a connection est correcte l'utilisateur à rentre le bon identifiant et mot de passe
     * @return boolean
     */
    public void authentificationConsole(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nom: ");
        String nom = scanner.nextLine();//l'input de l'utilisateur
        System.out.println("Prénom: ");
        String prenom = scanner.nextLine();
        System.out.println("Qui êtes-vous un Client, un Vendeur,un Administrateur?");
        String role = scanner.nextLine();
        System.out.println("Mot de Passe: ");
        String mdp = scanner.nextLine();
        Utilisateur temp = null;
        if (role.equals("Client")){
            System.out.println("adresse:");
            temp = new Client(nom,prenom , null,null,null,mdp);
        }if (role.equals("Vendeur")){
            System.out.println("Nom du magasin");
            String nomMag = scanner.nextLine();
            System.out.println("Ville du magasin");
            String ville = scanner.nextLine();
            Magasin magasin = new Magasin(nomMag, ville);
            temp = new Vendeur(nom,prenom , mdp, magasin);
        }if (role.equals("Administrateur")){
            temp = new Administrateur(nom,prenom,mdp);
        }
        Utilisateur res;
        try{
            res = this.lib.reccupUser(temp);
        }catch(UtilisateurInexistantException e){
            res = null;
            System.out.println("Utilisateur inexistant");
        }
        scanner.close();//ferme le scanner pour qu'il ne soit plus en écoute sinon il y a des erreur.
        if (this.lib.authentification(res)){
            System.out.println("Connection réussi ...");
            this.lib.setCurUser(temp);
        }else{
            System.out.println("Echec de la connection...");
            this.lib.setCurUser(null);
        }
       
    }
    public void creaClientCons(){
        System.out.println("Nom:");
        String nom = affichageConsole.scan.nextLine();//l'input de l'utilisateur
        System.out.println("Prénom: ");
        String prenom = affichageConsole.scan.nextLine();
        System.out.println("Adresse: ");
        String adresse = affichageConsole.scan.nextLine();
        System.out.println("Code postal: ");
        String ville = affichageConsole.scan.nextLine();
        System.out.println("Ville: ");
        String cp = affichageConsole.scan.nextLine();
        String mdp = "";
        String conf = null;
        while(!mdp.equals(conf)){
            System.out.println("Mot de passe");
            mdp = affichageConsole.scan.nextLine();
            System.out.println("Confirmation mot de passe:");
            conf = affichageConsole.scan.nextLine();
        }

        this.lib.createClient(nom, prenom,adresse, cp, ville, mdp,this.jdbc);
    }

    private void menuAuth(){
        while (this.estUtilise() && !this.estConnecte()){
            System.out.println("----------Menu----------");
            System.out.println("-01- Connectez-vous    -");
            System.out.println("-02- Créer un compte   -");
            System.out.println("-03- Catalogue         -");
            System.out.println("-00- Quitter           -");
            System.out.println("------------------------");
            String res = affichageConsole.scan.nextLine();
            switch (res) {
                case "01","1":
                    this.authentificationConsole();
                    break;
                case "02","2":
                    this.creaClientCons();
                    break;
                case "03","3":
                    this.catalogueLivre(1);
                    break;
                default:
                    this.quittez();
                    System.out.println("application fermée");
            }
        if (this.lib.getCurUser().getRoles().equals("Client")) {
            try{
                Magasin mag = this.menuMagasinConsole();
                this.menuCli(new Panier(),mag);
            }catch(MagasinInexistantException exp){
                System.out.println("Le nom du magasin saisie n'existe pas");
            }
        }
        else if(this.lib.getCurUser().getRoles().equals("Vendeur")){
            this.menuVend();
            }else{
                this.menuAdm();
            }
        // si différent de null renvoie un autre menu qui correspondera a ce que peut faire l'utilisateur.
        }
    }
    public Magasin menuMagasinConsole()throws MagasinInexistantException{
        System.out.println("Choisissez un magasin. Seulement le nom.");
        List<Magasin> mags = this.lib.getMagasin();
        for (Magasin mag : mags){
            System.out.println(mag.toString());
        }
        String nomMag = affichageConsole.scan.nextLine();
        for (Magasin mag : mags){
            if (mag.getNom().equals(nomMag)) return mag; 
        }throw new MagasinInexistantException();
    }
    public void menuCli(Panier panier,Magasin Mag){
        while (this.lib.getCurUser().getRoles().equals("Client")){
            System.out.println("-------Menu Client------");
            System.out.println("-01- Commander         -");
            System.out.println("-02- Consulter commande-");
            System.out.println("-03- Catalogue         -");
            System.out.println("-04- Recommandation    -");
            System.out.println("-00- Déconnexion       -");
            System.out.println("------------------------");
            String res = affichageConsole.scan.nextLine();
            Client leclient = (Client) this.lib.getCurUser();
            switch (res) {
                case "01","1":
                    this.menuCommander(mag,panier,leclient);
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
    public void trouverLivreConsole(){
        System.out.println("Entrez l'ISBN du livre:");
        affichageConsole.scan.nextLine();
    }

    public void menuCommander(Magasin mag, Panier panier,Client leclient){
        
        System.out.println("Indiquez la recherche de quel type (Par defaut vous consultez le catalogue):");
        System.out.println("--------Creation de votre commande-------");
        System.out.println("-01- CATALOGUE                          -");
        System.out.println("-02- ISBN                               -");
        System.out.println("-03- AUTEUR                             -");
        System.out.println("-04- TITRE                              -");
        System.out.println("-00- Valider commande                   -");
        System.out.println("-05- Annuler la commande                -");//mettre recomandation dans l'affichage du catalogue
        System.out.println("-----------------------------------------");
        String res = affichageConsole.scan.nextLine();
        switch (res) {
            case "0","00":
                this.menuAuth();
                break;
            case "01","1":
                this.catalogueMag(mag,1);
                break;
            case "02","2":
                System.out.println(leclient.consulterCommandes());
                break;
            default:
                this.cataReco(leclient);
                break;
        }
    }
    public void catalogueMag(Magasin leMag,int laPage){
        
    }
    /**
     * Cette methode permet d'affivher tout les livres de la base et les ranges en différentes pages
     * @param nbPage la valeur de la page du catalogue demander
     */
    public void catalogueLivre(int laPage){
        if (laPage > 0){
            int nbLivresParPage = 10;
            String res = "----------------------\n";
            try{
                
                List<Livre> lesLivres = this.lib.getLivres();
                for (int i = 0;i<10;i++){
                        res+=lesLivres.get((laPage*nbLivresParPage)-i).toString()+"\n";
                }
            }catch(NoSuchElementException plusDelivre){
                res+="Fin du catalogue merci de l'avoir lu";
            }finally{
                res+="----------------------\n";
                System.out.println(res);
            }
        }else{
            System.out.println("Catalogue\n Pour plus de livres allez a la page suivante");
        }
        System.out.println("-------Catalogue------");
        System.out.println("-1 - Page Precedente -");
        System.out.println("-2 - Page Suivante   -");
        System.out.println("-XX- Quitter         -");
        System.out.println("-----------------------");
        String choix = affichageConsole.scan.nextLine();
        switch(choix){
            case "1","01":
                this.catalogueLivre(laPage-1);
                break;
            case "2","02":
                this.catalogueLivre(laPage+1);
                break;
            default:
                this.menuAuth();
        }
    }
    public void cataReco(Client lClient){
        String affiche = "-----Recommandation-------\n";
        for (Livre l: lClient.onVousRecommande()){
            affiche += l.toString()+"\n";
        }affiche+="-----------FIN------------";
        System.out.println(affiche);
    }
    public void menuAdm(){
        
    }
    public void menuVend(){
        while (this.lib.getCurUser().getRoles().equals("Vendeur")){
            System.out.println("-------Menu Vendeur------");
            System.out.println("-01- Ajouter Livre      -");
            System.out.println("-02- Consulter Commande -");
            System.out.println("-02- Consulter Stock    -");
            System.out.println("-03- Catalogue          -");
            System.out.println("-04- Recommandation     -");
            System.out.println("-00- Déconnection       -");
            System.out.println("-------------------------");
            String res = affichageConsole.scan.nextLine();
            Vendeur leVendeur = (Vendeur) this.lib.getCurUser();
            switch (res) {
                case "01","1":
                    this.catalogue();
                    break;
                case "02","2":
                    break;
                case "03","3":
                    break;
                case "04","4":
                    break;
                default:
                    this.catalogue();
                    break;
            }this.menuAuth();}}
    public static void main(String[] args){
        new affichageConsole();
    }
}}