package exception;

public class RechercheSansResultatException extends Exception {
    public RechercheSansResultatException() {
        super("Aucun résultat ne correspond à vore recherche");
    }
}
