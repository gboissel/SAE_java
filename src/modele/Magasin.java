package modele;
import exception.PasAssezDeLivreException;
import exception.RechercheSansResultatException;

import java.lang.module.ResolutionException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;

import java.util.Collections;
import JDBC.JDBC;
import tri.TriLivreParNom;


public class Magasin implements Comparable<Magasin>{
    private String nom;
    private String ville;
    private List<Vendeur> vendeurs;
    private List<Commande> commandes;
    private Map<Livre, Integer> stock;

    /**
     * Constructeur de la classe Magasin
     * @param nom Le nom du magasin
     * @param ville La ville où se situe le magasin
     */
    public Magasin(String nom,String ville){
        this.nom = nom;
        this.ville = ville;
        this.vendeurs = new ArrayList<>();
        this.commandes = new ArrayList<>();
        this.stock = new HashMap<>();
    }
    
    /**
     * Retourne le nom du magasin.
     *
     * @return Le nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Retourne la ville du magasin.
     *
     * @return La ville
     */
    public String getVille() {
        return this.ville;
    }

    /**
     * Récupère la liste des livres trouvable avec un stock supérieur à 0 dans ce magasin
     * @return Une liste de livres
     */
    public List<Livre> getLivres() {
        return new ArrayList<>(this.stock.keySet());
    }

    /**
     * Récupère le stock en magasin du livre donné en paramètre
     * @param livre Le livre
     * @return La quantité disponible du livre
     */
    public int getQteLivre(Livre livre) {
        if (this.stock.containsKey(livre)) {
            return this.stock.get(livre);
        }
        return 0;
    }

    /**
     * Retourne les commandes associés
     * @return la liste des commandes
     */
    public List<Commande> getCommandes() {
        return this.commandes;
    }

    /**
     * Retourne les commandes associés
     * @return la liste des commandes
     */
    public List<Vendeur> getVendeurs() {
        return this.vendeurs;
    }

     /**
     * Met à jour la quantité d'un livre, en ajoute un dans la liste des livres du magasin, ou bien en supprime un
     * Cette fonction ne modifie pas la base de données et ne sert que pour en charger les données
     * @param livre Le livre dont on souhaite modifier la quantité
     * @param qte La nouvelle quantité de livre dans le stock
     * @exception PasAssezDeLivreException arrive lorsque la quantité de livre est strictement inférieure à 0
     */
    public void setQteLivre(Livre livre, int qte) throws PasAssezDeLivreException{
        if (qte > 0) {
            this.stock.put(livre, qte);
            livre.addMagasin(this);
        }
        else if (qte == 0) {
            this.stock.remove(livre);
            livre.removeMagasin(this);
        }
        else {
            throw new PasAssezDeLivreException();
        }
    }

    /**
     * Met à jour la quantité d'un livre, en ajoute un dans la liste des livres du magasin, ou bien en supprime un,
     * tout en modifiant la base de données
     * @param livre Le livre dont on souhaite modifier la quantité
     * @param qte La nouvelle quantité de livre dans le stock
     * @param jdbc Une instance de la classe permettant d'intéragir avec la base de données
     * @exception PasAssezDeLivreException arrive lorsque la quantité de livre est strictement inférieure à 0, ou bien lorsque la connexion à la base de données à échouée
     */
    public void setQteLivre(Livre livre, int qte, JDBC jdbc) throws PasAssezDeLivreException{
        try{
            if (qte > 0) {
                this.stock.put(livre, qte);
                livre.addMagasin(this);
                jdbc.updateStock(this, livre);
            }
            else if (qte == 0) {
                this.stock.remove(livre);
                livre.removeMagasin(this);
                jdbc.updateStock(this, livre);
            }
            else {
                throw new PasAssezDeLivreException();
            }
        }
        catch (SQLException e) {throw new PasAssezDeLivreException();}
    }

    /**
     * Décrémente de 1 la quantité du livre, et le supprime si il y en a plus 
     * @param livre Le livre a enlever
     * @param jdbc Une instance de la classe permettant d'intéragir avec la base de données
     */
    public void removeLivre(Livre livre, JDBC jdbc){
        try {
            this.setQteLivre(livre, this.getQteLivre(livre) - 1, jdbc);
        }
        catch (PasAssezDeLivreException e) {}
    }

    /**
     * Ajoute une commande parmis la liste des commandes
     * @param comm Une commande
     */
    public void addCommande(Commande comm){
        if (!(this.commandes.contains(comm))){
            this.commandes.add(comm);
        }
    }

    /**
     * Ajoute un vendeur parmis la liste des vendeurs
     */
    public void addVendeur(Vendeur ven){
        if (!(this.vendeurs.contains(ven))){
            this.vendeurs.add(ven);
        }
    }

    /**
     * Permet de trouver une liste de livres, triées par ordre croissant de titre, correspondant à une recherche dans ce magasin
     * Les résultats de la recherche sont des livres ayant un titre commençant par le texte donné en paramètre, en ignorant les majuscules/minuscules
     * @param texte La chaîne de caractère avec laquelle on recherche des livres correspondant
     * @return Une liste de livres, résultats de la recherche
     * @throws RechercheSansResultatException Arrive lorsque la recherche ne donne aucun résultat
     */
    public List<Livre> rechercherLivre(String texte) throws RechercheSansResultatException{
        /*Recherche dichotomique pour trouver le premier résultat de notre recherche*/
        texte = texte.toLowerCase();
        List<Livre> tousLesLivres = this.getLivres();
        Collections.sort(tousLesLivres, new TriLivreParNom());
        List<Livre> resultat = new ArrayList<>();
        int debut = 0;
        int fin = tousLesLivres.size();
        int ind = (debut+fin)/2;
        boolean trouve = false;
        while (!trouve && fin>debut) {
            if (tousLesLivres.get(ind).getTitre().toLowerCase().startsWith(texte)) {
                if (ind == 0 || !tousLesLivres.get(ind - 1).getTitre().toLowerCase().startsWith(texte)) {
                    trouve = true; /*Nous avons trouvé le premier élément correspondant à notre recherche */
                }
                else {fin = ind; /*Nous avons trouvé un élément correspondant à notre recherche mais il n'est pas le premier élément */}
            }
            else if (tousLesLivres.get(ind).getTitre().compareToIgnoreCase(texte) < 0) {
                fin = ind; /*Les éléments recherchés se trouvent avant celui-ci dans la liste*/
            }
            else {debut = ind + 1; /*Les éléments recherchés se trouvent après celui-ci dans la liste*/}
            if (!trouve) {ind = (debut+fin)/2;}
        }
        /**On récupère maintenant tous les éléments correspondant à notre recherche */
        while (tousLesLivres.get(ind).getTitre().toLowerCase().startsWith(texte)) {
            resultat.add(tousLesLivres.get(ind));
            ++ind;
        }
        if (resultat.isEmpty()) {
            throw new RechercheSansResultatException();
        }
        return resultat;
    }

    @Override
    public String toString() {
        return this.nom + " " +  this.ville;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Magasin)){
            return false;
        }
        Magasin auteur = (Magasin)obj;
        return (auteur.nom.equals(this.nom)&&auteur.ville.equals(this.ville));
    }

    @Override
    public int hashCode(){
        return this.nom.hashCode()*503+this.ville.hashCode()*7;
    }

    @Override
    public int compareTo(Magasin m) {
        return this.nom.compareTo(m.nom);
    }
}
