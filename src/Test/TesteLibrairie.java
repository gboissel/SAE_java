package test;
import exception.PasAssezDeLivreException;
import modele.*;

import  org.junit.jupiter.api.Test;
import  org.junit.jupiter.api.Assertions.assertEquals;
import  org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

public class TesteLibrairie {
    @Test
    public void TesteLibrairieM() {
        Magasin m = new Magasin("Librairie Toulouse", "Toulouse");
        Livre livre = new Livre(98169321,"L'oiseau",30.0,86, 25/8/2020);
        Administrateur admin = new Administrateur("Vant", "alex", "mdp_admin");
        Utilisateur u1 = new Utilisateur("pol","didi","mdp_util1");
        Librairie l = new Librairie(admin);
        l.ajouterUser(u1);
        assertEquals(u1, l.getUsers());
        assertTrue(l.hasAdmin());
        assertTrue(l.authentification(u1));
        assertEquals(u1, l.reccupUser(u1));
    }

}