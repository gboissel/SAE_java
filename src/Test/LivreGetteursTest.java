package Test;
import modele.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;



public class LivreGetteursTest {

     @Test
    public void testGetISBN() {
        Livre livre = new Livre("98165103", "Titre", 10.0, 100, 2020);

        assertEquals("98165103", livre.getISBN());
    }

     @Test
    public void testGetTitre() {
        Livre livre = new Livre("98165790", "L'avion", 14.0, 300, 2018);
        
        assertEquals("L'avion", livre.getTitre());
    }

     @Test
    public void testGetPage() {
        Livre livre = new Livre("98169321", "LA grenouille", 15.0, 100, 2019);
        
         assertEquals(100, livre.getPages());
    }

     @Test
    public void testGetDate() {
        Livre livre = new Livre("98160125", "Titre", 20.0, 100, 2000);
        
         assertEquals(2000, (int) livre.getDate());
    }

     @Test
    public void testGetPrix() {
        Livre livre = new Livre("98166305", "Titre", 12.0, 400, 2021);
        
        assertEquals(12.0, livre.getPrix(), 0.0001);
    }


    @Test
    public void testGetAuteur() {
        Livre livre = new Livre("98169872", "Titre", 10.8, 100, 2020);
        Auteur auteur = new Auteur("Baudelaire", 1826, 1900);


        livre.addAuteur(auteur);

        assertTrue(livre.getAuteurs().contains(auteur));
        assertTrue(auteur.getLivres().contains(livre));
    }

    @Test
    public void testGetEditeur() {
        Livre livre = new Livre("98160673", "Titre 2", 12.0, 200, 2021);
        Editeur editeur = new Editeur("Gallimard");

        livre.addEditeur(editeur);

        assertTrue(livre.getEditeurs().contains(editeur));
        assertTrue(editeur.getLivres().contains(livre));
    }

    @Test
    public void testGetCategorie() {
        Livre livre = new Livre("98169650", "Titre 3", 14.0, 150, 2022);
        Categorie categorie = new Categorie("Roman");

        livre.addClassification(categorie);

        assertTrue(livre.getClassification().contains(categorie));
        assertTrue(categorie.getLivres().contains(livre));
    }

    @Test
    public void testGetCommande() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Livre livre = new Livre("98169857", "Titre 4", 16.0, 180, 2023);
        Commande commande = new Commande(10, "15/04/2021", true, true, cl,m);

        livre.addCommande(commande);

        assertTrue(livre.getCommandes().contains(commande));
    }

    @Test
    public void testGetMagasin() {
        Livre livre = new Livre("98169483", "Titre 5", 18.0, 220, 2024);
        Magasin magasin = new Magasin("Fnac", "Lyon");

        livre.addMagasin(magasin);
        assertTrue(livre.getMagasins().contains(magasin));

        livre.removeMagasin(magasin);
        assertFalse(livre.getMagasins().contains(magasin));
    }
}
