package Test;
import exception.PasAssezDeLivreException;
import modele.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import JDBC.JDBC;

import java.util.List;
import java.util.Map;

public class TesteLibrairie {
    @Test
    public void TesteLibrairieM() {
                JDBC jdbc = new JDBC(null);
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        //Livre livre = new Livre(98169321,"L'oiseau",30.0,86, 25/8/2020);
        Administrateur admin = new Administrateur("Vant", "alex", "mdp_admin");
        Utilisateur u1 = new Client("martin", "sophie", "14 rue de la tortue", "50000", "la manche", "mdp_452");
        Librairie l = new Librairie(jdbc); 
        l.ajouterUser(u1);
        assertEquals(u1, l.getUsers());
        assertTrue(l.hasAdmin());
        assertTrue(l.authentification(u1));
    }

}