import java.util.Comparator;
import java.util.Map;

public class TrieMapAuteur implements Comparator<Auteur>{
    private Map<Auteur, Integer> dico;

    public TrieMapAuteur(Map<Auteur, Integer> dico) {
        this.dico = dico;
    }

    @Override
    public int compare(Auteur a1, Auteur a2) {
        if (dico.get(a2).equals(dico.get(a1))) {
            return a1.compareTo(a2);
        }
        else{
            return dico.get(a2) - dico.get(a1);
        }
    }
}
