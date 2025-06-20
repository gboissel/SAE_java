package Test;
import modele.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.*;
public class CategorieGetteursTest {
    @Test
    public void testGetNom() {
        Categorie c = new Categorie("roman");
        assertEquals("roman", c.getNom());
    }
    @Test
    public void testGetLivreCat() {
        Categorie c = new Categorie("roman");
        Livre livre = new Livre("98169650", "Titre 3", 14.0, 150, 2022);
        c.ajouterlivre(livre);
        List<Livre> Livres = new ArrayList<>();
        Livres.add(livre);
        assertEquals(Livres, c.getLivres());
    }
}
