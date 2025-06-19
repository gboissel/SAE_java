package Test;
import modele.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class MagasinGetteursTest {

    @Test
    public void testGetNom() {
        Magasin magasin = new Magasin("Fnac", "Lyon");
        assertEquals("Fnac", magasin.getNom());
    }

    @Test
    public void testGetVille() {
        Magasin magasin = new Magasin("Fnac", "Lyon");
        assertEquals("Lyon", magasin.getVille());
    }

    @Test
    public void testGetSetQteLivre() throws Exception {
        Magasin magasin = new Magasin("Fnac", "Lyon");
        Livre livre = new Livre("98169321","L'oiseau",30.0,86, 2020);

        assertEquals(0, magasin.getQteLivre(livre));

        magasin.setQteLivre(livre, 5);
        assertEquals(5, magasin.getQteLivre(livre));

        magasin.setQteLivre(livre, 0);
        assertEquals(0, magasin.getQteLivre(livre));
    }

    @Test
    public void testGetVendeurs() {
        Magasin magasin = new Magasin("Fnac", "Lyon");
        assertEquals(0, magasin.getVendeurs().size());
    }

    @Test
    public void testGetCommandes() {
        Magasin magasin = new Magasin("Fnac", "Lyon");
        assertEquals(0, magasin.getCommandes().size());
    }

    @Test
    public void testGetLivres() throws Exception {
        Magasin magasin = new Magasin("Fnac", "Lyon");
        Livre livre = new Livre("98146431","L'ours",45.0,60, 2022);
        magasin.setQteLivre(livre, 3);
        assertTrue(magasin.getLivres().contains(livre));
    }
}
