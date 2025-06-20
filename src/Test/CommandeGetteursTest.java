package Test;
import modele.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.*;

public class CommandeGetteursTest {
    @Test
    public void testGetNumero() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Commande co = new Commande(10, "15/04/2021", true, true, cl,m);
        assertEquals(10, co.getNum());
    }

    @Test
    public void testGetDate() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Commande co = new Commande(10, "15/04/2021", true, true, cl,m);
        
        assertEquals("15/04/2021", co.getDate());
    }

    @Test
    public void testGetEnLigne() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Commande co = new Commande(10, "15/04/2021", true, true, cl,m);        
        assertEquals(true, co.estEnLigne());
    }

    @Test
    public void testGetDomicile() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Commande co = new Commande(10, "15/04/2021", true, true, cl,m);
        
        assertEquals(true, co.livreDomicile());
    }

    @Test
    public void testGetClient() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Commande co = new Commande(10, "15/04/2021", true, true, cl,m);
        
        assertEquals(cl,co.getClient());
    }

    @Test
    public void testGetMagasin() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Commande co = new Commande(10, "15/04/2021", true, true, cl,m);
        
        assertEquals(m, co.getMagasin());
    }

    @Test
    public void testGetDetailCom() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Commande co = new Commande(10, "15/04/2021", true, true, cl,m);
        Livre livre = new Livre("98169650", "Titre 3", 14.0, 150, 2022);
        DetailCommande dc = new DetailCommande(co, livre, 5, 60);
        assertEquals(List.of(), co.getDetailsCommande());
    }
    @Test
    public void testGetLivre() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Commande co = new Commande(10, "15/04/2021", true, true, cl,m);
        Livre livre = new Livre("98169650", "Titre 3", 14.0, 150, 2022);
        Map<Livre, Integer> livreMap= new HashMap<>();
        livreMap.put(livre, 1);
        assertEquals(Collections.emptyMap(), co.getLivres());

    }
}
