public class PasAvoirCommandeException extends Exception{
    public PasAvoirCommandeException() {
        super("Le client n'a pas de commande à ce numéro");
    }
}
