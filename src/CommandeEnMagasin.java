public class CommandeEnMagasin extends Exception {
    public CommandeEnMagasin() {
        super("La commande ici présente a été réalisée en magasin, il est donc impossible de modifier son mode de livraison");
    }
}