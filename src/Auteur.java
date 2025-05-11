
import java.util.ArrayList;
import java.util.List;

public class Auteur implements Comparable<Auteur>{
    private String nom;
    private Integer naissance;
    private Integer deces;
    private List<Livre> lesLivres;

    /**
     * Constructeur de la classe Auteur.
     *
     */
    public Auteur(String nom,Integer naissance, Integer deces){
        this.nom=nom;
        this.naissance=naissance;
        this.deces=deces;
        this.lesLivres = new ArrayList<>();

    }

    /**
     * Constructeur de la class Auteur si l'auteur est toujours en vie.
     *
     */
    public Auteur(String nom,Integer naissance){
        this.nom=nom;
        this.naissance=naissance;
        this.deces=null;
        this.lesLivres = new ArrayList<>();
    }

    /**
     * Retourne le nom de l'auteur.
     *
     * @return Le nom.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Modifie le nom de l'auteur.
     *
     * @param nom Le nouveau nom.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne l'année de naissance de l'auteur.
     *
     * @return L'année de naissance.
     */
    public Integer getNaissance() {
        return this.naissance;
    }

    /**
     * Modifie l'année de naissance de l'auteur.
     *
     * @param naissance La nouvelle année de naissance.
     */
    public void setNaissance(Integer naissance) {
        this.naissance = naissance;
    }

    /**
     * Retourne l'année de décès de l'auteur (ou null s'il est toujours en vie).
     *
     * @return L'année de décès ou null.
     */
    public Integer getDeces() {
        return this.deces;
    }

    /**
     * Modifie l'année de décès de l'auteur.
     *
     * @param deces La nouvelle année de décès.
     */
    public void setDeces(Integer deces) {
        this.deces = deces;
    }

    /**
     * Ajoute un livre à la liste contenant les livres écris par cet auteur
     */
    public void ajouterLivre(Livre livre){
        if (!(this.lesLivres.contains(livre))){
            this.lesLivres.add(livre);
        }
    }

    public List<Livre> getLivre() {
        return this.lesLivres;
    }

    @Override
    public String toString() {
        String texte = "Auteur " + this.nom;
        if (this.naissance != null) {
            texte = texte + " né(e) en " + this.naissance;
        }
        if (this.deces != null) {
            texte = texte + " décédé(e) en " + this.deces;
        }
        return texte;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Auteur)){
            return false;
        }
        Auteur auteur = (Auteur)obj;
        return auteur.nom.equals(this.nom);
    }

    @Override 
    public int hashCode() {
        return this.nom.hashCode();
    }

    @Override
    public int compareTo(Auteur a) {
        return this.nom.compareTo(a.nom);
    }
}   

