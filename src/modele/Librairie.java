package modele;

import JDBC.JDBC;
import exception.RechercheSansResultatException;
import exception.UtilisateurInexistantException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tri.TriLivreParNom;

public class Librairie {
    private Utilisateur curUser;
    private List<Utilisateur> users;
    private List<Magasin> lesMagasins;
    private List<Livre> lesLivres;
    private JDBC jdbc;
    private Magasin curMag;
    private boolean chargee;
    private Panier panier;


    public Librairie(JDBC jdbc){
        this.chargee = false;
        this.users= new ArrayList<>();
        this.curUser = null;
        this.jdbc = jdbc;
        this.initialisationBD(this.jdbc);

    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }


    public void  ajouteMag(Magasin mag){
        this.lesMagasins.add(mag);
    }

    public Magasin rechercheMagParNom(String nommag){
        for(Magasin mag: this.lesMagasins){
            if(mag.getNom().equals(nommag)){
                return mag;
            }
        }
        return null;
    }

    public Magasin rechercheMag(String nommag,String ville){
        for(Magasin mag: this.lesMagasins){
            if(mag.getNom().equals(nommag)&&mag.getVille().equals(ville)){
                return mag;
            }
        }
        return null;
    }

    public Livre rechercheLivreParNom(String nom){
        for(Livre livre: this.lesLivres){
            if(livre.getTitre().equals(nom)){
                return livre;
            }
        }
        return null;
    }

    public Livre rechercheLivreParNomParMagasin(String nom){
        for(Livre livre: this.curMag.getLivres()){
            if(livre.getTitre().equals(nom)){
                return livre;
            }
        }
        return null;
    }

    public boolean estChargee() {
        return this.chargee;
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
     * renvoie magasin courant
     * @return le magasin courant
     */
    public Magasin getCurMag() {
        return this.curMag;
    }

    /**
     * Récupère l'instance de la classe permettant de manipuler la base de données
     * @return Une instance de la classe JDBC
     */
    public JDBC getJDBC() {
        return this.jdbc;
    }
    
    /**
     * change l'utilisateur courant
     * @param usr Le nouvel utilisateur courant
     */
    public void setCurUser(Utilisateur usr){
        this.panier=new Panier();
        this.curUser=usr;
    }
    /**
     * Met à jour le magasin courant
     * @param mag
     */
    public void setCurMag(Magasin mag){
        this.curMag=mag;
    }

    /**
     * change le magasin courant
     * @param mag Le nouveau magasin courant
     */
    public void setCurMag(Magasin mag) {
        this.curMag = mag;
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
        catch (SQLException e) {System.out.println("Erreur lors de la création du client");}
    }

    /**
     * Méthode à utiliser dans le contructeur permettant d'initialiser une base de données
     * @param jdbc
     */
    private void initialisationBD(JDBC jdbc) {
        try {
            this.lesLivres = jdbc.recupererLivres(jdbc.recupererAuteurs(), jdbc.recupererEditeurs(), jdbc.recupererCategories());
            Collections.sort(this.lesLivres, new TriLivreParNom());
            System.out.println("Chargement des livres réussi. " + this.lesLivres.size() + " livres récupérés.");
            this.lesMagasins = jdbc.recupererMagasins(this.lesLivres);
            System.out.println("Chargement des magasins réussi. " + this.lesMagasins.size() + " magasins récupérés.");
            this.users = jdbc.recupererUtilisateurs(lesMagasins, lesLivres);
            Collections.sort(this.users);
            System.out.println("Chargement des utilisateurs réussi. " + this.users.size() + " utilisateurs récupérés.");
            this.chargee = true;
        }
        catch (SQLException e) {
            this.lesLivres = new ArrayList<>();
            this.lesMagasins = new ArrayList<>();
            this.users = new ArrayList<>();
        }
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

    public void createVendeur(String nom,String prenom,String mdp, Magasin magasin, JDBC jdbc){
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
     * Permet de récupérer la liste des clients portant le nom de famille donné en paramètre, indépendament de la casse majuscule/minuscule
     * @param nom Le nom de famille du client
     * @return Une liste de clients portant ce nom de familles, trié par ordre alphabétique du prénom
     * @throws RechercheSansResultatException Arrive lorsque la recherche ne donne aucun résultat
     */
    public List<Client> rechercheClientParNoms(String nom) throws RechercheSansResultatException{
        /*Recherche dichotomique pour trouver le premier client correspondant à notre recherche */
        List<Client> res = new ArrayList<>();
        nom = nom.toLowerCase();
        boolean trouve = false;
        int debut = 0;
        int fin = this.users.size();
        int ind = (debut+fin) / 2;
        while (!trouve && fin>debut) {
            if (this.users.get(ind).getRoles().equals("Client")) {
                if (this.users.get(ind).getNom().toLowerCase().equals(nom)) {
                    if (ind == 0 || !this.users.get(ind - 1).getNom().toLowerCase().equals(nom)) {trouve = true;}
                    else {fin = ind;}
                }
                else if (this.users.get(ind).getNom().compareToIgnoreCase(nom) < 0) {fin = ind;}
                else {debut = ind + 1;}
            }
            else {fin = ind;}
            if (!trouve) {ind = (debut+fin)/2;}
        }
        if (trouve) {
            /*On récupère maintenant tous les clients correspondant à la recherche */
            while (this.users.get(ind).getRoles().equals("Client") && this.users.get(ind).getNom().toLowerCase().equals(nom)) {
                res.add((Client) this.users.get(ind));
                ++ind;
            }
            return res;
        }
        throw new RechercheSansResultatException();
    }

    /**
     * permet d'obtenir les factures de chaque magasin avec un mois et une annee donnée
     * @param mois
     * @param annee
     */
    public String editerFacture(int mois, int annee){
        String moisTexte = "" + mois;
        if (moisTexte.length()==1) {moisTexte="0"+moisTexte;}
        String res = "Facture du "+moisTexte+"/"+annee+"\n";
        String interligne = "      ISBN                Titre          qte   prix    total  \n";
        double ca_global = 0.0;
        int livre_vendu_glo = 0;
        DecimalFormat prix = new DecimalFormat();
        prix.setMaximumFractionDigits(2);
        for(Magasin mag: this.lesMagasins){
            int livre_vendu_mag = 0;
            int nb_factures = 0;
            res+="Edition des factures du magasin  "+mag.getNom()+"\n";
            res+="-----------------------------------------------------\n";
            for(Commande com:mag.getCommandes()){
                String date = com.getDate();
                String[] parties = date.split("/");
                if(parties[1].equals(moisTexte)&&parties[2].equals(annee+"")){
                    ++nb_factures;
                    int cpt = 1;
                    Client cli = com.getClient();
                    double totalCli = 0.0;
                    res+=cli.getNom()+" "+cli.getPrenom()+"\n";
                    res+=cli.getAdresse()+"\n";
                    res+=cli.getCodePostal()+"  "+cli.getVille()+"\n";
                    res+="Commande n° "+com.getNum()+" du "+date+"\n";
                    res+=interligne;
                    for(DetailCommande det:com.getDetailsCommande()){
                        Livre livre = det.getLivre();
                        totalCli+=det.getQte()*det.getPrixVente();
                        livre_vendu_glo+=det.getQte();
                        livre_vendu_mag+=det.getQte();
                        String titre = livre.getTitre();
                        while (titre.length()<22) {titre+=" ";}
                        String prixLivre = prix.format(det.getPrixVente());
                        if (prixLivre.split(",").length==1) {prixLivre+=",00";}
                        else if (prixLivre.split(",")[1].length()<2) {prixLivre+="0";}
                        while (prixLivre.length() < 6) {prixLivre+=" ";}
                        String prixTotalLivre = prix.format(det.getPrixVente()*det.getQte());
                        if (prixTotalLivre.split(",").length==1) {prixTotalLivre+=",00";}
                        else if (prixTotalLivre.split(",")[1].length()<2) {prixTotalLivre+="0";}
                        res+=cpt+"  "+livre.getISBN()+"  "+titre.substring(0, 22)+"  "+prix.format(det.getQte())+"   "+prixLivre+"  "+prixTotalLivre+"\n";
                        ++cpt;
                    }
                    res+="-------";
                    String prixTotal = prix.format(totalCli);
                    if (prixTotal.split(",").length==1) {prixTotal+=",00";}
                    else if (prixTotal.split(",")[1].length()<2) {prixTotal+="0";}
                    res+="Total   "+prixTotal+"\n";
                    ca_global+=totalCli;
                    res+="-----------------------------------------------------\n";
                }
            }
            res+=nb_factures+" factures éditées\n";
            res+=livre_vendu_mag+" livres vendus\n";
        }
        res+="*************************************\n";
        String caText = prix.format(ca_global);
        if (caText.split(",").length==1) {caText+=",00";}
        else if (caText.split(",")[1].length()<2) {caText+="0";}
        res+="Chiffre d'affaires global: "+caText+"\n";
        res+="Nombre livres vendus: "+livre_vendu_glo+"\n";
        System.out.println(res);
        //mis en place de la transcription en txt:
        try {
            FileWriter writer = new FileWriter("factures-" + moisTexte + "-" + annee + ".txt");
            writer.write(res);
            writer.close();
            System.out.println("Fichier texte créé avec succès !");
        } catch (IOException e) {
            System.out.println("Une erreur est survenue.");
            e.printStackTrace();//ca c'est bon
        }
        return res;
    }
}
