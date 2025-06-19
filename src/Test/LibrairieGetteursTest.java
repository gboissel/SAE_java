
package Test;
import modele.*;
import JDBC.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class LibrairieGetteursTest {

    @Test
    public void testGetCurUser() throws Exception {
        JDBC jdbc = new JDBC(null);
        Librairie librairie = new Librairie(jdbc);
        Utilisateur user = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");

        librairie.setCurUser(user);
        assertEquals(user, librairie.getCurUser());
    }

    @Test
    public void testGetCurMag() throws Exception {
        JDBC jdbc = new JDBC(null);
        Librairie librairie = new Librairie(jdbc);
        Magasin magasin = new Magasin("Cultura", "Toulouse");

        librairie.setCurMag(magasin);
        assertEquals(magasin, librairie.getCurMag());
    }

    @Test
    public void testGetUsers() throws Exception {
        JDBC jdbc = new JDBC(null);
        Librairie librairie = new Librairie(jdbc);
        Utilisateur user = new Client("martin", "sophie", "14 rue de la tortue", "50000", "la manche", "mdp_452");
        Utilisateur user2 = new Client("marto", "sopha", "14 rue de la tortue", "50000", "la manche", "mdp_474");
        librairie.ajouterUser(user);
        librairie.ajouterUser(user2);
        assertTrue(librairie.getUsers().contains(user));
    }

    @Test
    public void testGetMagasins() throws Exception {
        ConnexionMySQL co = new ConnexionMySQL();
        JDBC jdbc  = new JDBC(co.connexion());
        Librairie librairie = new Librairie(jdbc); 
        Magasin magasin = new Magasin("Fnac", "Paris");
        Magasin magasin2 = new Magasin("Gifi", "Paris");
        librairie.ajouteMag(magasin);
        librairie.ajouteMag(magasin2);
        assertTrue(librairie.getMagasins().contains(magasin));
    }

    @Test
    public void testGetLivres() throws Exception {
        JDBC jdbc = new JDBC(null);
        Librairie librairie = new Librairie(jdbc);
        Livre livre = new Livre("123456789", "Java Facile", 29.99, 200, 2023);

        assertTrue(librairie.getLivres().contains(livre));
    }
}
