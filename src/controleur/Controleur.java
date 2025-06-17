package controleur;

import modele.*;
import view.*;
import exception.*;
import tri.*;
import JDBC.*;

public class Controleur {
    private Librairie modele;
    private LivreExpress vue;

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

}
