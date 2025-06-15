package tri;
import java.util.Comparator;
import modele.Livre;

/**
 * Classe permettant de trier les livres par ordre alphabétique de leur nom
 */
public class TriLivreParNom implements Comparator<Livre>{
    @Override
    public int compare(Livre l1, Livre l2) {
        return l1.getTitre().compareToIgnoreCase(l2.getTitre());
    }
}
