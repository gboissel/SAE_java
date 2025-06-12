package modele;
import JDBC.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import JDBC.JDBC;
import exception.*;

public class Client extends Utilisateur{
    private String adresse;
    private String cp;
    private String ville;
    private List<Commande> commandes;
    /**
     * Creer une instance d'un Utilisateur qui est un Client
     * @param nom
     * @param prenom
     * @param adresse
     * @param cp
     * @param ville
     * @param mdp
     */
    public Client(String nom, String prenom, String adresse, String cp, String ville, String mdp){
        super(nom, prenom, mdp);
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.commandes = new ArrayList<>();
    }

    @Override
    /**
     * Permet de récupérer le rôle de l'utilisateur
     * @return "Client"
     */
    public String getRoles() {
        return "Client";
    }

    /**
     * Méthode permettant à un client de réaliser une commande, lorsque les stocks correspondent à ceux disponibles dans le magasin
     * @param lesLivres Un dictionnaire ayant pour clés des livres, et pour valeurs un nombre entier représentant la quantité qu'il souhaite commander pour chaque livre
     * @param enLigne Si true, alors la commande sera réalisée en ligne, sinon false
     * @param domicile Si la commande est en ligne et que ce paramètre est à true, alors la commande sera livrée à domicile, sinon false
     * @param magasin Le magasin où sont acheté les différents livres
     * @param jdbc Une instance de la classe permettant d'intéragir avec la base de données
     */
    public void commander(Map<Livre, Integer> lesLivres, boolean enLigne, boolean domicile, Magasin magasin, JDBC jdbc){
        try{
            LocalDateTime dateActuelle = LocalDateTime.now();
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = dateActuelle.format(formatDate);

            
            Commande commande = new Commande(jdbc.maxNumeroCommande(), date, enLigne, domicile, this, magasin);

            for (Livre livre:lesLivres.keySet()) {
                magasin.setQteLivre(livre, lesLivres.get(livre), jdbc);
                commande.addLigne(livre, lesLivres.get(livre), livre.getPrix());
            }
            magasin.addCommande(commande);
            this.commandes.add(commande);
            jdbc.insererCommande(commande);
        }
        catch (PasAssezDeLivreException e) {}
        catch (SQLException e) {}
    }

    /**
     * Ajoute une commande dans la liste des commandes du client
     * @param commande La commande à ajouter
     */
    public void ajouterCommande(Commande commande){
        this.commandes.add(commande);
    }

    public String getAdresse() {
        return this.adresse;
    }

    public String getCodePostal() {
        return this.cp;
    }

    public String getVille() {
        return this.ville;
    }

    /**
     * Permet de récupérer le numéro d'une commande en fonction de son numéro parmis celles que le client a réalisées
     * @param num Le numéro de la commande
     * @return La commande portant le numéro indiqué en paramètre
     * @throws PasAvoirCommandeException Arrive lorsque le client n'a pas réalisé de commande avec ce numéro
     */
    public Commande getCommande(int num) throws PasAvoirCommandeException{
        Commande commandeExemple = new Commande(num, "", false, false, null, null); 
        for (Commande com : this.commandes) {
            if (com.equals(commandeExemple)) {
                return com;
            }
        }
        throw new PasAvoirCommandeException();
    }

    /**
     * Permet de récupérer l'ensemble des livres commandés par le client
     * @return L'ensemble des livres
     */
    public Set<Livre> getLivres() {
        Set<Livre> lesLivres = new HashSet<>();
        for (Commande com : this.commandes) {
            lesLivres.addAll(com.getLivres().keySet());
        }
        return lesLivres;
    }

    /**
     * Permet de modifier le mode de livraison pour une commande en ligne
     * @param num Le numéro de la commande
     * @param domicile Le nouveau mode de livraison
     * @param jdbc Une instance de la classe permettant d'intéragir avec la base de données
     */
    public void modifierModeLivraisonCommande(int num, boolean domicile, JDBC jdbc){
        try{
            Commande commande = this.getCommande(num);
            commande.setLivraison(domicile, jdbc);
        }
        catch (PasAvoirCommandeException | CommandeEnMagasinException e) {}
    }

    /**
     * Méthode permettant de consulter les commandes réalisées par le client
     * @return La liste des commandes
     */
    public List<Commande> consulterCommandes(){  
        return this.commandes;
    }

    /**
     * Méthode permettant d'obtenir les catégories de livres que le client commande le plus, sert pour la méthode livresRecommandes
     * @return Un dictionnaire ayant comme clés des catégories de livres et comme valeurs le nombre d'exemplaires de livres appartenant à cette catégorie parmis les commandes du client
     */
    private Map<Categorie, Integer> categoriesPreferees() {
        Map<Categorie, Integer> dicoCat = new HashMap<>();
        for (Commande commande:this.commandes) {
            for (DetailCommande dC:commande.getDetailsCommande()) {
                for (Categorie cat:dC.getLivre().getClassification()) {
                    dicoCat.put(cat, dicoCat.getOrDefault(cat, 0) + dC.getQte());
                }
            }
        }
        return dicoCat;
    }

    /**
     * Méthode permettant d'obtenir les auteurs des livres que le client commande le plus, sert pour la méthode livresRecommandes
     * @return Un dictionnaire ayant comme clés des auteurs et comme valeurs le nombre d'exemplaires de livres écris par cet auteur parmis les commandes du client
     */
    private Map<Auteur, Integer> auteursPreferes() {
        Map<Auteur, Integer> dicoAut = new HashMap<>();
        for (Commande commande:this.commandes) {
            for (DetailCommande dC:commande.getDetailsCommande()) {
                for (Auteur auteur:dC.getLivre().getAuteurs()) {
                    dicoAut.put(auteur, dicoAut.getOrDefault(auteur, 0) + dC.getQte());
                }
            }
        }
        return dicoAut;
    }

    /**
     * Méthode permettant d'obtenir les livres que le client à acheté qui seraient recommandés aux clients ayant comme catégories et auteurs préférés ceux donnés en paramètre, sert pour la méthode onVousRecommande
     * @param dicoCat Un dictionnaire obtenu grâce à l'utilisation de la méthode categoriesPreferees
     * @param dicoAut Un dictionnaire obtenu grâce à l'utilisation de la méthode auteursPreferes
     * @return Un dictionnaire ayant comme clés des livres et comme valeurs un nombre de points, résultant du produit entre les valeurs des différentes catégories du livre et des différents auteurs ayant écris le livre selon les dictionnaires donnés en paramètres
     */
    private Map<Livre, Integer> livresRecommandes(Map<Categorie, Integer> dicoCat, Map<Auteur, Integer> dicoAut) {
        Map<Livre, Integer> dicoLivre = new HashMap<>();
        for (Livre livre:this.getLivres()) {
            for (Categorie cat:dicoCat.keySet()) {
                if (livre.getClassification().contains(cat)) {
                    dicoLivre.put(livre, dicoLivre.getOrDefault(livre, 1) * dicoCat.get(cat));
                }
            }
            for (Auteur aut:dicoAut.keySet()) {
                if (livre.getAuteurs().contains(aut)) {
                    dicoLivre.put(livre, dicoLivre.getOrDefault(livre, 1) * dicoAut.get(aut));
                }
            }
        }
        return dicoLivre;
    }

    /**
     * Méthode permettant d'obtenir la liste des livres que l'on recommande au client
     * Cette liste sera ordonnée du plus recommandé au moins recommandé et ne contiendra pas un livre que le client a déjà commandé
     * @return La liste des livres recommandés
     */
    public List<Livre> onVousRecommande(){
        Map<Categorie, Integer> dicoCat = this.categoriesPreferees();
        Map<Auteur, Integer> dicoAut = this.auteursPreferes();
        Map<Livre, Integer> livresPref = this.livresRecommandes(dicoCat, dicoAut);
        Set<Livre> setLivres = new HashSet<>(livresPref.keySet());
        Map<Livre, Integer> livresPrefClient;
        Map<Livre, Integer> recommandation = new HashMap<>();
        for (Livre livre:livresPref.keySet()) {
            for (Commande com:livre.getCommandes()) {
                if (!this.equals(com.getClient())) {
                    livresPrefClient = com.getClient().livresRecommandes(dicoCat, dicoAut);
                    for (Livre livreReco:livresPrefClient.keySet()) {
                        if (!setLivres.contains(livreReco)) {
                            recommandation.put(livreReco, recommandation.getOrDefault(livreReco, 0) + livresPrefClient.get(livreReco));
                            setLivres.add(livreReco);
                        }
                    }
                }
            }
        }
        TrieMap<Livre> tri = new TrieMap<>(recommandation); 
        Map<Livre, Integer> recoTrie = new TreeMap<>(tri); 
        //TreeMap est une classe qui descend de Map, et permet d'ordonner ses clés comme un arbre, permettant de les trier selon une classe qui implémente Comparator comme TrieMap juste au-dessus, utile si on veut trier les clés d'un dictionnaire selon leurs valeurs
        recoTrie.putAll(recommandation);
        return new ArrayList<>(recoTrie.keySet());
    }

    @Override
    public String toString() {
        return "Client : " + this.prenom  + " " + this.nom;
    }
}
