package modele;
import java.util.ArrayList;
import java.util.List;

public class Editeur implements Comparable<Editeur>{
    private String nom;
    private List<Livre> lesLivres;

    /**
     * Contructeur de la classe Editeur
     * @param nom Le nom de l'éditeur
     */
    public Editeur(String nom){
        this.nom=nom;
        this.lesLivres=new ArrayList<>();
        
    }
    /**
     * Permet d'obtenir le nom de l'éditeur du livre
     * @return String
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Permet d'obtenir la liste des lesLivres ayant été édité par cet éditeur
     * @return List<Livre>
     */
    public List<Livre> getLivres(){
        return this.lesLivres;
    }

    /**
     * Ajoute un livre fais par cette éditeur à la List<Livre> qui correspond à la liste des livre fait par cette éditeur.
     * @param livre
     */
    public void ajouterlivre(Livre livre){
        if (!(this.lesLivres.contains(livre))){
            this.lesLivres.add(livre); 
        }
        
    }
    
    @Override
    public String toString(){
        return "Edition "+this.nom;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Editeur)){
            return false;
        }
        Editeur edit = (Editeur)obj;
        return (this.nom.equals(edit.nom));
    }

    @Override
    public int hashCode(){
        return this.nom.hashCode();
    }

    @Override
    public int compareTo(Editeur e) {
        return this.nom.compareTo(e.nom);
    }
}
