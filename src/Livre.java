import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Livre implements Comparable<Livre>{
    private int isbn;
    private String titre;
    private double prix;
    private int nbPages;
    private String datePubli;
    private Set<Auteur> auteurs;
    private Set<Editeur> editeurs;
    private Set<Categorie> classification;
    private List<Commande> lesCommandes;
    private List<Magasin> lesMagasins;
    
    public Livre(int isbn,String titre,double prix,int pages,String datePubli){
        this.isbn=isbn;
        this.auteurs= new HashSet<>();
        this.titre=titre;
        this.datePubli=datePubli;
        this.nbPages=pages;
        this.editeurs = new HashSet<>();
        this.classification  = new HashSet<>();
        this.lesCommandes  = new ArrayList<>();
        this.lesMagasins  = new ArrayList<>();
    }

    /**
     * Retourne le code ISBN du livre.
     * @return l'ISBN sous forme d'entier
     */
    public int getISBN() {
        return this.isbn;
    }

    /**
     * Retourne le titre du livre.
     * @return le titre en chaîne de caractères
     */
    public String getTitre() {
        return this.titre;
    }

    /**
     * Retourne le nombre de pages du livre.
     * @return le nombre de pages en entier
     */
    public int getPages() {
        return this.nbPages;
    }

    /**
     * Retourne la date de publication du livre.
     * @return la date sous forme de chaîne
     */
    public String getDate() {
        return this.datePubli;
    }

    /**
     * Retourne le prix du livre.
     * @return le prix en double
     */
    public double getPrix() {
        return this.prix;
    }

    /**
     * Retourne les auteurs du livre.
     * @return l'ensemble des auteurs
     */
    public Set<Auteur> getAuteurs() {
        return this.auteurs;
    }

    /**
     * Retourne les éditeurs du livre.
     * @return l'ensemble des éditeurs
     */
    public Set<Editeur> getEditeurs() {
        return this.editeurs;
    }

    /**
     * Retourne les catégories auxquels appartient le livre
     * @return l'ensemble des catégories
     */
    public Set<Categorie> getClassification() {
        return this.classification;
    }

    /**
     * Retourne les commandes associés
     * @return la liste des commandes
     */
    public List<Commande> getCommandes() {
        return this.lesCommandes;
    }

    /**
     * Retourne les magasin du livre.
     * @return la liste de magasins
     */
    public List<Magasin> getMagasins() {
        return this.lesMagasins;
    }

    /**
     * Associe un auteur au livre en l'ajoutant dans la liste des auteurs
     * Ajoute aussi le livre dans la liste des livres de l'auteur donné en paramètre
     * @param auteur un Auteur
     */
    public void addAuteur(Auteur auteur){
        if (!(this.auteurs.contains(auteur))){
            this.auteurs.add(auteur);
            auteur.ajouterLivre(this);
        }
    }

    /**
     * Associe un éditeur au livre en l'ajoutant dans la liste des éditeurs
     * Ajoute aussi le livre dans la liste des livres de l'éditeur donné en paramètre
     * @param edit un Editeur
     */
    public void addEditeur(Editeur edit){
        if (!(this.editeurs.contains(edit))){
            this.editeurs.add(edit);
            edit.ajouterlivre(this);
        }
    }

    /**
     * Associe une catégorie au livre en l'ajoutant dans la classification
     * Ajoute aussi le livre dans la liste des livres de la catégorie donnée en paramètre
     * @param classi une Catégorie
     */
    public void addClassification(Categorie classi){
        if (!(this.classification.contains(classi))){
            this.classification.add(classi);
            classi.ajouterlivre(this);
        }
    }

    /**
     * Associe une commande au livre en l'ajoutant dans la liste des commandes
     * @param comm une Commande
     */
    public void addCommande(Commande comm){
        if (!(this.lesCommandes.contains(comm))){
            this.lesCommandes.add(comm);
        }
    }

    /**
     * Associe un magasin au livre en l'ajoutant dans la liste des magasins
     * @param mag un Magasin
     */
    public void addMagasin(Magasin mag){
        if (!(this.lesMagasins.contains(mag))){
            this.lesMagasins.add(mag);
        }
    }

    /**
     * Dissocie un magasin du livre en le retirant de la liste des magasins
     * @param mag un Magasin
     */
    public void removeMagasin(Magasin mag) {
        this.lesMagasins.remove(mag);
    }

    @Override
    public String toString() {
        return this.titre;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Livre)){
            return false;
        }
        Livre auteur = (Livre)obj;
        return (auteur.titre.equals(this.titre)&&auteur.prix==this.prix);
    }

    @Override
    public int hashCode(){
        return this.isbn * 31;
    }

    @Override
    public int compareTo(Livre l) {
        return this.isbn - l.isbn;
    }
}