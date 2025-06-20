package Test;
import modele.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendeurGetteursTest {

    @Test
    public void testGetRoles() {
        Magasin magasin = new Magasin("MagasinTest", "Paris");
        Vendeur vendeur = new Vendeur("Martin", "Luc", "pass123", magasin);
        assertEquals("Vendeur", vendeur.getRoles());
    }

    @Test
    public void testGetMagasin() {
        Magasin magasin = new Magasin("MagasinTest", "Paris");
        Vendeur vendeur = new Vendeur("Martin", "Luc", "pass123", magasin);
        assertEquals(magasin, vendeur.getMagasin());
    }

    @Test
    public void testGetNom() {
        Magasin magasin = new Magasin("MagasinTest", "Paris");
        Vendeur vendeur = new Vendeur("Martin", "Luc", "pass123", magasin);
        assertEquals("Martin", vendeur.getNom());
    }

    @Test
    public void testGetPrenom() {
        Magasin magasin = new Magasin("MagasinTest", "Paris");
        Vendeur vendeur = new Vendeur("Martin", "Luc", "pass123", magasin);
        assertEquals("Luc", vendeur.getPrenom());
    }
}