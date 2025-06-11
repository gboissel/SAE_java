import java.util.ArrayList;
import java.util.List;

public class Categorie implements Comparable<Categorie>{
    private String nom;
    private List<Livre> lesLivres;

    /**
     * Constructeur de la classe Categorie
     * @param nom Le nom de catégorie
     */
    public Categorie(String nom){
        this.nom=nom;
        this.lesLivres=new ArrayList<>();
        
    }
    /**
     * Permet d'obtenir le nom de la Categorie
     * @return String
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Permet d'obtenir la liste des lesLivres ayant cette classification
     * @return List<Livre>
     */
    public List<Livre> getLivres(){
        return this.lesLivres;
    }

    /**
     * Ajoute un livre à la liste des lesLivres contenant cette classification.
     * @param livre
     */
    public void ajouterlivre(Livre livre){
        if (!(this.lesLivres.contains(livre))){
            this.lesLivres.add(livre); 
        }
        
    }
    
    @Override
    public String toString(){
        return "Catégorie "+this.nom;
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
        Categorie edit = (Categorie)obj;
        return (edit.nom.equals(this.nom));
    }

    @Override
    public int hashCode(){
        return this.nom.hashCode();
    }

    @Override
    public int compareTo(Categorie c) {
        return this.nom.compareTo(c.nom);
    }
}
