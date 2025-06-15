package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;// il faut tester mais normalement selon la doc ça permet de faire l'équivalent d'un input en python.
import JDBC.JDBC;
import exception.UtilisateurInexistantException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class Librairie {
    private Utilisateur curUser;
    private List<Utilisateur> users;
    private List<Magasin> lesMagasins;
    private List<Livre> lesLivres;


    public Librairie(Administrateur admin){
        this.users= new ArrayList<>();
        this.users.add(admin);
        this.curUser = null;
    }


    /**
     * renvoie la liste des utilisateur
     * @return La liste des utilisateurs
     */
    public List<Utilisateur> getUsers() {
        return this.users;
    }

    /**
     * renvoie la liste des livres
     * @return La liste des livres
     */
    public List<Livre> getLivres() {
        return this.lesLivres;
    }

    /**
     * renvoie la liste des magasins
     * @return La liste des magasins
     */
    public List<Magasin> getMagasins() {
        return this.lesMagasins;
    }

    /**
     * renvoie l'utilisateur courant
     * @return L'utilisateur courant
     */
    public Utilisateur getCurUser(){
        return this.curUser;
    }
    
    /**
     * change l'utilisateur courant
     * @param usr Le nouvel utilisateur courant
     */
    public void setCurUser(Utilisateur usr){
        this.curUser=usr;
    }


    /**
     * Créer un client puis l'ajoute à la liste des utilisateur
     * @param nom Son nom
     * @param prenom Son prénom
     * @param adresse Son adresse
     * @param cp Le code postal de sa ville
     * @param ville La ville où il habite
     * @param mdp Son mot de passe
     * @param jdbc Une instance de la classe permettant d'intéragir avec la base de données
     */
    public void createClient(String nom,String prenom, String adresse, String cp, String ville, String mdp, JDBC jdbc){
        try {
            Client client = new Client(nom, prenom, adresse, cp, ville, mdp);
            this.users.add(client);
            jdbc.insererClient(client, mdp);
        }
        catch (SQLException e) {}
    }

    /**
     * vérifie s'il y a un administrateur au sein de la Librarie sinon renvoie false.
     * @return boolean
     */
    public boolean hasAdmin(){
        for (Utilisateur user:this.users){
            if (user.getRoles().equals("Administrateur"))
                return true;
        }return false;
    }


    /**
     * ajoute un utilisateur déjà existant à la liste des utilisateurs 
     * @param user
     */
    public void ajouterUser(Utilisateur user){
        this.users.add(user);
    }
    /**
     * créer un vendeur et l'ajoute à la liste des utilisateurs
     * @param nom Son nom
     * @param prenom Son prénom
     * @param mdp Son mot de passe
     * @param magasin Le magasin auquel le vendeur est raccordé
     * @param jdbc Une instance de la classe permettant d'intéragir avec la base de données
     */

    private void createVendeur(String nom,String prenom,String mdp, Magasin magasin, JDBC jdbc){
        try{
            Vendeur vendeur = new Vendeur(nom, prenom, mdp, magasin);
            this.users.add(vendeur);
            jdbc.insererVendeur(vendeur, mdp);
        }
        catch (SQLException e) {}

    }

    /**
     * renvoie true si l'a connection est correcte l'utilisateur à rentre le bon identifiant et mot de passe
     * @return boolean
     */
    public boolean authentification(Utilisateur temp){
        for (Utilisateur usr:this.users){
            if ( usr != null && usr.equals(temp))
                return true;
        }return false;
    }

    public Utilisateur reccupUser(Utilisateur rech) throws UtilisateurInexistantException{
        for (Utilisateur usr:this.users){
            if (rech.equals(usr)) return usr;
        }throw new UtilisateurInexistantException();
    }

    /**
     * permet d'obtenir les factures de chaque magasin avec un mois et une annee donnée
     * @param mois
     * @param annee
     */
    public void editerFacture(int mois, int annee){
        String res = "Facture du "+mois+"/"+annee+"\n";
        int i = 1;
        String interligne = "      ISBN       Titre         qte     prix   total  \n";
        double ca_global = 0.0;
        int livre_vendu_glo = 0;
        for(Magasin mag: this.lesMagasins){
            int livre_vendu_mag = 0;
            res+="Edition des factures du magasin  "+mag.getNom()+"\n";
            res+="-----------------------------------------------------";
            for(Commande com:mag.getCommandes()){
                String date = com.getDate();
                String[] parties = date.split("/");
                if(parties[2].equals(mois+"")&&parties[3].equals(annee+"")){
                    int cpt = 1;
                    Client cli = com.getClient();
                    double totalCli = 0.0;
                    res+=cli.getNom()+" "+cli.getPrenom()+"\n";
                    res+=cli.getAdresse()+"\n";
                    res+=cli.getCodePostal()+"  "+cli.getVille()+"\n";
                    res+="Commande n° "+i+" du "+date+"\n";
                    res+=interligne;
                    for(DetailCommande det:com.getDetailsCommande()){
                        Livre livre = det.getLivre();
                        totalCli+=det.getQte()*det.getPrixVente();
                        livre_vendu_glo+=det.getQte();
                        livre_vendu_mag+=det.getQte();
                        res+=cpt+"  "+livre.getISBN()+"  "+livre.getTitre()+"  "+det.getQte()+"  "+det.getPrixVente()+"  "+det.getQte()*det.getPrixVente()+"\n";
                        ++cpt;
                    }
                    res+="-------";
                    res+="Total   "+totalCli+"\n";
                    ca_global+=totalCli;
                    res+="-----------------------------------------------------";
                }
            }
            res+=mag.getCommandes().size()+"  factures éditées \n";
            res+=livre_vendu_mag+" livres vendus\n";
        }
        res+="*************************************";
        res+="Chiffre d'affaires global: "+ca_global+"\n";
        res+="Nombre livres vendus: "+livre_vendu_glo+"\n";
        System.out.println(res);
        //mis en place de la transcription en txt:
        try {
            FileWriter writer = new FileWriter("mon_fichier.txt");
            writer.write(res);
            writer.close();
            System.out.println("Fichier texte créé avec succès !");
        } catch (IOException e) {
            System.out.println("Une erreur est survenue.");
            e.printStackTrace();
        }
    }
}
