import java.util.ArrayList;
import java.util.List;

public class Livre {
    private int isbn;
    private String titre;
    private double prix;
    private int nombrePage;
    private String datePubli;
    private List<Auteur> auteurs;
    private List<Editeur> editeurs;
    private List<Classification> classifications;
    private List<Commande> commandes;
    private List<Magasin> magasins;
    
    public Livre(int isbn,String titre,double prix,int pages,String datePubli,Auteur aut,Editeur editeur){
        this.isbn=isbn;
        this.auteurs= new ArrayList<>();
        this.titre=titre;
        this.datePubli=datePubli;
        this.nombrePage=pages;
        this.editeurs = new ArrayList<>();
        this.classifications  = new ArrayList<>();
        this.commandes  = new ArrayList<>();
        this.magasins  = new ArrayList<>();
    }

    /**
     * Retourne les commandes associés
     * @return la liste des commandes
     */
    public int getCommandes() {
        return this.isbn;
    }

/**
     * Retourne les magasin du livre.
     * @return la liste de magasins
     */
    public int getMagasins() {
        return this.commandes;
    }


/**
     * Retourne le code ISBN du livre.
     * @return l'ISBN sous forme d'entier
     */
    public int getIsbn() {
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
     * Retourne le prix du livre.
     * @return le prix en double
     */
    public double getPrix() {
        return this.prix;
    }

    /**
     * Retourne le nombre de pages du livre.
     * @return le nombre de pages en entier
     */
    public int getNombrePage() {
        return this.nombrePage;
    }

    /**
     * Retourne la date de publication du livre.
     * @return la date sous forme de chaîne
     */
    public String getDatePubli() {
        return this.datePubli;
    }

    /**
     * Retourne les auteurs du livre.
     * @return un objet de type Auteur
     */
    public List<Auteur> getAuteur() {
        return this.auteurs;
    }

    /**
     * Retourne les éditeurs du livre.
     * @return un objet de type Editeur
     */
    public List<Editeur> getEditeur() {
        return this.editeurs;
    }

    public void addAuteur(Auteur auteur){
        if (!(this.auteurs.contains(auteur))){
            this.auteurs.add(auteur);
        }
    }

    public void addEditeur(Editeur edit){
        if (!(this.editeurs.contains(edit))){
            this.editeurs.add(edit);
        }
    }

    public void addClassification(Classification classi){
        if (!(this.classifications.contains(classi))){
            this.classifications.add(classi);
        }
    }

    public void addCommande(Commande comm){
        if (!(this.commandes.contains(comm))){
            this.commandes.add(comm);
        }
    }

    public void addMagasins(Magasin mag){
        if (!(this.magasins.contains(mag))){
            this.magasins.add(mag);
        }
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
        return this.titre.hashCode();
    }
}