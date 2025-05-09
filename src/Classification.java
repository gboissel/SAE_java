import java.util.ArrayList;
import java.util.List;

public class Classification {
    private String nom;
    private List<Livre> livres;


    public Classification(String nom){
        this.nom=nom;
        this.livres=new ArrayList<>();
        
    }
    /**
     * Permet d'obtenir le nom de l'Editeur du livre
     * @return String
     */
    public String getClassi(){
        return this.nom;
    }

    /**
     * Permet d'obtenir le nom de l'Editeur du livre
     * @return String
     */
    public List<Livre> getLivre(){
        return this.livres;
    }

    /**
     * Ajoute un livre fais par cette éditeur à la List<Livre> qui correspond à la liste des livre fait par cette éditeur.
     * @param unLivre
     */
    public void ajouterlivre(Livre unLivre){
        if (!(this.livres.contains(unLivre))){
            this.livres.add(unLivre); 
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
        if (!(obj instanceof Auteur)){
            return false;
        }
        Classification edit = (Classification)obj;
        return (edit.nom.equals(this.nom));
    }

    @Override
    public int hashCode(){
        return this.nom.hashCode();
    }
}
