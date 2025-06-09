public class CommandeEnMagasinException extends Exception {
    public CommandeEnMagasinException() {
        super("La commande ici présente a été réalisée en magasin, il est donc impossible de modifier son mode de livraison");
    }
}