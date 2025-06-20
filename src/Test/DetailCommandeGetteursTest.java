package Test;
import modele.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DetailCommandeGetteursTest {
     @Test
    public void testGetQuantite() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Commande co = new Commande(10, "15/04/2021", true, true, cl,m);
        Livre livre = new Livre("98169650", "Titre 3", 14.0, 150, 2022);
        DetailCommande dc = new DetailCommande(co, livre, 5, 60);
        
        assertEquals(5, dc.getQte());
    }

    @Test
    public void testGetPrixVente() {
         Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Commande co = new Commande(10, "15/04/2021", true, true, cl,m);
        Livre livre = new Livre("98169650", "Titre 3", 14.0, 150, 2022);
        DetailCommande dc = new DetailCommande(co, livre, 5, 140);

        assertEquals(140, dc.getPrixVente(), 0.001);
    }


    @Test
    public void testGetCommande() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Commande co = new Commande(10, "15/04/2021", true, true, cl,m);
        Livre livre = new Livre("98169650", "Titre 3", 14.0, 150, 2022);
        DetailCommande dc = new DetailCommande(co, livre, 5, 140);

        assertEquals(co, dc.getCommande());
    }


    @Test
    public void testGetLivre() {
         Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Commande co = new Commande(10, "15/04/2021", true, true, cl,m);
        Livre livre = new Livre("98169650", "Titre 3", 14.0, 150, 2022);
        DetailCommande dc = new DetailCommande(co, livre, 5, 140);

        assertEquals(livre, dc.getLivre());
    }
}
