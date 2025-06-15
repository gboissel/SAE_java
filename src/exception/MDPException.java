package exception;
public class MDPException extends Exception{
    public MDPException(){
        super("Identifiant ou Mots de pas inconnus");
    }
}
