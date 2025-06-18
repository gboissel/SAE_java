package controleur;

import modele.*;
import view.*;

public class Controleur {
    protected Librairie modele;
    protected LivreExpress vue;

    public Librairie getModele() {
        return modele;
    }
    public LivreExpress getVue() {
        return vue;
    }

    public void setModele(Librairie modele) {
        this.modele = modele;
    }

    public void setVue(LivreExpress vue) {
        this.vue = vue;
    }

    public void chargerPage(){
        
    }
}
