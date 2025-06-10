import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        Livre livre1 = new Livre(123456789,"Titre1",20.0,50,"05-07-2023");
        Livre livre2 = new Livre(123456789,"Titre1",20.0,50,"05-07-2023");
        
        assertEquals(2, m.getLivres().size());
        assertEquals(5, m.getQteLivre(livre1));
        assertEquals(3, m.getQteLivre(livre2));
    }
    @Test
    public void testAjouterLivre() {
        Magasin m = new Magasin("Librairie du coin", "Paris");

        Client client = new Client("Jean", "Dupont", "1234");
        Categorie categorie = new Categorie("Fiction");
        Livre livre = new Livre(123, "Titre", 10.0, 5, "01-01-2023");
        categorie.ajouterlivre(livre);

        assertTrue(client.getLivres().contains(livre));
        assertEquals(2, m.getQteLivre(livre));
    }
    @Test
    public void testOnvousrecommande() {
        Magasin m = new Magasin("Librairie du coin", "Paris");
        Client client = new Client("Jean", "Dupont", "1234");
        
        Categorie fiction = new Categorie("Fiction");
        Categorie science = new Categorie("Science");
        
        Livre livre1 = new Livre(123, "Livre Fiction 1", 10.0, 5, "01-01-2023");
        Livre livre2 = new Livre(456, "Livre Science 1", 15.0, 3, "02-02-2023");
        
        fiction.ajouterlivre(livre1);
        science.ajouterlivre(livre2);
        
        client.ajouterLivre(livre1);
        client.ajouterLivre(livre2);
        
        Map<Categorie, Integer> dicoCat = client.categoriesPreferees();
        Map<Auteur, Integer> dicoAut = client.auteursPreferes();
        
        Map<Livre, Integer> recommandations = client.livresRecommandes(dicoCat, dicoAut);
        
        assertTrue(recommandations.containsKey(livre1));
        assertTrue(recommandations.containsKey(livre2));
    }
    @Test
    public void testOnVousRecommande() {
        // Création des objets nécessaires
        Client client1 = new Client("Jean", "Dupont", "1234");
        Client client2 = new Client("Marie", "Durand", "5678");

        Auteur auteur1 = new Auteur("Auteur1",1980,2003);
        Auteur auteur2 = new Auteur("Auteur2",1990,2024);

        Livre livre1 = new Livre(1, "Livre 1", 10.0, 5, "01-01-2023");
        Livre livre2 = new Livre(2, "Livre 2", 12.0, 3, "02-02-2023");
        Livre livre3 = new Livre(3, "Livre 3", 15.0, 2, "03-03-2023");

        livre1.addAuteur(auteur1);

        livre2.addAuteur(auteur2);

        livre3.addAuteur(auteur1);

        Commande commande1 = new Commande(1, "01-01-2023", false, false, client1, null);
        commande1.addLigne(livre1, 1, livre1.getPrix());
        commande1.addLigne(livre2, 1, livre2.getPrix());
        client1.ajouterCommande(commande1);

        Commande commande2 = new Commande(2, "02-02-2023", false, false, client2, null);
        commande2.addLigne(livre3, 1, livre3.getPrix());
        client2.ajouterCommande(commande2);

        livre3.getCommandes().add(commande2);

        List<Livre> recommandations = client1.onVousRecommande();

        assertTrue(recommandations.contains(livre3));

        assertFalse(recommandations.contains(livre1));
        assertFalse(recommandations.contains(livre2));
    }   
}
