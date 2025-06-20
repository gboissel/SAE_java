package Test;
import modele.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AuteurGetteursTest {
     @Test
    public void testGetNom() {
        Auteur a = new Auteur("Baudelaire", 1826, 1900);
        a.setNom("Baudelaire");
        assertEquals("Baudelaire", a.getNom());
    }
    @Test
    public void testGetNaissance() {
        Auteur a = new Auteur("Baudelaire", 1780, 1900);
        a.setNaissance(1800);
        assertEquals(1800, (int) a.getNaissance());
    }
    @Test
    public void testGetDeces() {
        Auteur a = new Auteur("Baudelaire", 1826, 1900);
        a.setDeces(1950);
        assertEquals(1950, (int) a.getDeces());
    }
    @Test
    public void testGetLivreAuteurs() {
        Auteur a = new Auteur("Baudelaire", 1826, 1900);
        Livre livre = new Livre("98169650", "Titre 3", 14.0, 150, 2022);
        Livre livre2 = new Livre("98167843", "Titre 7", 14.0, 150, 2022);
        a.ajouterLivre(livre);
        a.ajouterLivre(livre2);
        assertEquals(livre, a.getLivres());
    }
}
