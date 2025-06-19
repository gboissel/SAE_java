package Test;
import modele.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


import exception.PasAvoirCommandeException;



public class ClientGetteursTest {
    @Test
    public void testGetRoleCli() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        assertEquals("Client", cl.getRoles());
    }
     @Test
    public void testGetAdresseCli() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        assertEquals("14 rue de la rivière", cl.getAdresse());
    }
     @Test
    public void testGetCodePostalCli() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        assertEquals("45000", cl.getCodePostal());
    }
     @Test
    public void testGetVilleCli() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        assertEquals("orleans", cl.getVille());
    }
     @Test
    public void testGetCommandeCli() throws PasAvoirCommandeException {
        try {
    Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
    Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
    Commande co = new Commande(10, "15/04/2021", true, true, cl, m);
    cl.ajouterCommande(co);
    assertEquals(co, cl.getCommande(10));
} catch (PasAvoirCommandeException e) {
    System.out.println("La commande existe pas");}
    }

    @Test
    public void testGetLivreCli() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        assertEquals("Client", cl.getRoles());
    }
}
