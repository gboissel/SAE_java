package Test;
import exception.PasAssezDeLivreException;
import modele.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


import java.util.List;
import java.util.Map;

public class TesteLivre {
    @Test
    public void TesteLivreGetteur() {
        Auteur a = new Auteur("Baudelaire", 1826, 1900);
        Auteur a2 = new Auteur("DelaCroix", 1900, 1980);
        Editeur e = new Editeur("Hachette");
        Categorie c = new Categorie("roman");
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Livre livre = new Livre("98165103", "Titre", 10.0, 100, 2020);
        Commande co = new Commande(10, "15/04/2021", true, false, cl, m);
        livre.addAuteur(a);
        livre.addAuteur(a2);
        livre.addClassification(c);
        livre.addCommande(co);
        livre.addEditeur(e);
        livre.addMagasin(m);
        assertEquals(a, livre.getAuteurs());
        assertEquals(c, livre.getClassification());
        assertEquals(co, livre.getCommandes());
        assertEquals(e, livre.getEditeurs());
        assertEquals(m, livre.getMagasins());
        assertEquals(98169321, livre.getISBN());

    }


}