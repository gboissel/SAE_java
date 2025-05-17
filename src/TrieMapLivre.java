import java.util.Comparator;
import java.util.Map;

public class TrieMapLivre implements Comparator<Livre>{
    private Map<Livre, Integer> dico;

    public TrieMapLivre(Map<Livre, Integer> dico) {
        this.dico = dico;
    }

    @Override
    public int compare(Livre l1, Livre l2) {
        if (dico.get(l2).equals(dico.get(l1))) {
            return l1.compareTo(l2);
        }
        else{
            return dico.get(l2) - dico.get(l1);
        }
    }
}
