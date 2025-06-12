package Test;
import modele.*;
import org.junit.*;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class TestLibrairie {
    @Test
    public void TestAutentification(){
        Librairie lb = new Librairie();
        lb.createClient("Doo", "Samie", "scooby");
        lb.createVendeur("Jarl", "Sam", "vendeur123");

        // Cas où l'authentification réussit
        assertTrue(lb.authentification("D1oo", "Samie", "scooby", "Client"));
        assertTrue(lb.authentification("Jarl", "Sam", "vendeur123", "Vendeur"));
    }
    
}
