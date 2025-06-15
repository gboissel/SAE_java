package modele;

import java.util.HashMap;

public class Panier extends HashMap<Livre,Integer>{
    public boolean estVide(){
        return this.keySet().size() == 0;
    }
    
}
