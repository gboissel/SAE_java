package test;
import modele.*;

import exception.PasAssezDeLivreException;

import org.junit.jupiter.api.Test;
import  org.junit.jupiter.api.Assertions.assertEquals;
import  org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

public class TesteCommande {
    @Test
    public void TesteCommandeM() {
        Client cl = new Client("michel", "jean", "14 rue de la rivière", "45000", "orleans", "mdp_452");
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Livre livre = new Livre(98169321,"L'oiseau",30.0,86, 25/8/2020);
        Commmande co = new Commande(10, "15/04/2021", true, true, cl,m);
        DetailCommande dc = new DetailCommande(co, livre, 40, 150.0);
        co.addLigne(livre,10,50.0);
        co.setLivraison(true);
        dc.getQte();
        dc.getLivre();
        assertTrue(co.estEnLigne());
        assertTrue(co.livreDomicile());
        assertEquals(6000, co.prixTotal());
    }

}