import java.util.Comparator;
import java.util.Map;

public class TrieMapCategorie implements Comparator<Categorie>{
    private Map<Categorie, Integer> dico;

    public TrieMapCategorie(Map<Categorie, Integer> dico) {
        this.dico = dico;
    }

    @Override
    public int compare(Categorie c1, Categorie c2) {
        if (dico.get(c2).equals(dico.get(c1))) {
            return c1.compareTo(c2);
        }
        else{
            return dico.get(c2) - dico.get(c1);
        }
    }
}
