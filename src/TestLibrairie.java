import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class TestLibrairie {
    @Test
    public void TestAutentification(){
        Librairie lb = new Librairie();
        assertEquals(lb.authentification("Doo", "Samie", "scooby", "Client"),lb.authentification("jarl", "Sam", "scooby", "Vendeur"));
    }
}
