import java.util.Comparator;
import java.util.Map;

public class TrieMapCategorie implements Comparator<Categorie>{
    private Map<Categorie, Integer> dico;

    public TrieMapCategorie(Map<Categorie, Integer> dico) {
        this.dico = dico;
    }

    @Override
    public int compare(Categorie c1, Categorie c2) {
        return dico.get(c1) - dico.get(c2);
    }
}
