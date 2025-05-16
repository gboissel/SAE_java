import java.util.Comparator;
import java.util.Map;

public class TrieMapAuteur implements Comparator<Auteur>{
    private Map<Auteur, Integer> dico;

    public TrieMapAuteur(Map<Auteur, Integer> dico) {
        this.dico = dico;
    }

    @Override
    public int compare(Auteur a1, Auteur a2) {
        return dico.get(a1) - dico.get(a2);
    }
}
