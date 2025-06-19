package Test;
import modele.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AdministrateurGetteursTest {

    @Test
    public void testGetRoles() {
        Administrateur admin = new Administrateur("Dupont", "Jean", "password123");
        assertEquals("Administrateur", admin.getRoles());
    }

    @Test
    public void testGetNom() {
        Administrateur admin = new Administrateur("Dupont", "Jean", "password123");
        assertEquals("Dupont", admin.getNom());
    }

    @Test
    public void testGetPrenom() {
        Administrateur admin = new Administrateur("Dupont", "Jean", "password123");
        assertEquals("Jean", admin.getPrenom());
    }
}