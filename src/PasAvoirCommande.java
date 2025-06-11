public class PasAvoirCommande extends Exception{
    public PasAvoirCommande() {
        super("Le client n'a pas de commande à ce numéro");
    }
}
