package Test;
import modele.*;

import exception.PasAssezDeLivreException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TesteCommande {
    @Test
    public void TesteCommandeM() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Livre livre = new Livre("98165103", "Titre", 10.0, 100, 2020);
        Commande co = new Commande(10, "15/04/2021", true, true, cl,m);
        DetailCommande dc = new DetailCommande(co, livre, 40, 150.0);
        co.addLigne(livre,10,50.0);
        dc.getQte();
        dc.getLivre();
        assertTrue(co.estEnLigne());
        assertTrue(co.livreDomicile());
        assertEquals(6000, co.prixTotal());
    }

}