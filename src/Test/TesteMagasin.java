package Test;
import exception.PasAssezDeLivreException;
import modele.*;


import exception.PasAssezDeLivreException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TesteMagasin {
    @Test
    public void TesteMagasingetNom() {
        Magasin m = new Magasin("Librairie du coin", "Paris");
        assertEquals("Librairie du coin", m.getNom());
        assertEquals("Paris", m.getVille());
    }
    @Test
    public void TesteMagasingetLivres() {
        Magasin m = new Magasin("Librairie du coin", "Paris");
        Livre livre1 = new Livre("98165103", "Titre", 10.0, 100, 2020);
        Livre livre2 = new Livre("98165103", "Titre", 10.0, 100, 2020);
        Livre livre3 = new Livre("98165103", "Titre", 10.0, 100, 2020);
        try {
            m.setQteLivre(livre3, 0);
            m.setQteLivre(livre1, 5);
            m.setQteLivre(livre2, 3);
        } catch (PasAssezDeLivreException ex) {
        }
        
        assertEquals(3, m.getLivres().size());
        assertEquals(5, m.getQteLivre(livre1));
        assertEquals(3, m.getQteLivre(livre2));
    }
    @Test
    public void testmagasingetteurs() {
        Magasin m = new Magasin("Librairie du coin", "Paris");
        Vendeur v = new Vendeur("Jean","Michel","mdp124",m);
        Client cl = new Client(null, null, null, null, null, null);
        Commande c = new Commande(2, "12/09/2024", false, false, cl, m);
        m.addVendeur(v);
        m.addCommande(c);
        assertEquals(1,m.getVendeurs());
        assertEquals(1,m.getCommandes());
    }
    
}
