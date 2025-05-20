import java.util.Comparator;
import java.util.Map;

public class TrieMap<T extends Comparable<T>> implements Comparator<T>{
    private Map<T, Integer> dico;

    public TrieMap(Map<T, Integer> dico) {
        this.dico = dico;
    }

    @Override
    public int compare(T o1, T o2) {
        if (dico.get(o1).equals(dico.get(o2))) {
            return o1.compareTo(o2);
        }
        else{
            return dico.get(o2) - dico.get(o1);
        }
    }
}
