package Test;
import modele.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class EditeurGetteursTest {

    @Test
    public void testGetNom() {
        Editeur editeur = new Editeur("Gallimard");
        assertEquals("Gallimard", editeur.getNom());
    }

    @Test
    public void testGetLivre() {
        Editeur editeur = new Editeur("Actes Sud");
        Livre livre = new Livre("987654321", "La Tempête", 18.5, 230, 2021);
        Livre livre2 = new Livre("987654487", "La Temp", 18.5, 230, 2021);
        editeur.ajouterlivre(livre);
        editeur.ajouterlivre(livre2);
        assertTrue(editeur.getLivres().contains(livre));
    }
}