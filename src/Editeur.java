import java.util.ArrayList;
import java.util.List;

public class Editeur {
    private String nom;
    private List<Livre> livres;
    public Editeur(String nom){
        this.nom=nom;
        this.livres=new ArrayList<>();
        
    }
    /**
     * Permet d'obtenir le nom de l'Editeur du livre
     * @return String
     */
    public String getEdition(){
        return this.nom;
    }
    /**
     * Ajoute un livre fais par cette éditeur à la List<Livre> qui correspond à la liste des livre fait par cette éditeur.
     * @param unLivre
     */
    public void ajouterlivre(Livre unLivre){
        this.livres.add(unLivre);
    }
    
    @Override
    public String toString(){
        return "Edition "+this.nom;
    }
    
}
