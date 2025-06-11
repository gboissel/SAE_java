public abstract class Utilisateur{
    protected String nom;
    protected String prenom;
    protected String mdp;

    protected Utilisateur(String nom,String prenom,String mdp){
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
    }
    /**
     * Permet d'obtenir le nom de l'utilisateur.
     * @return String
     */
    public String getNom() {
        return nom;
    }
    /**
     * Permet d'obtenir le prenom de l'utilisateur.
     * @return String
     */
    public String  getPrenom(){
        return this.nom;
    }
    /**
     * Permet d'obtenir le rôle de l'utlisateur soit Administrateur ou Client ou Vendeur.
     * @return String
     */
    abstract String getRoles();

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if (obj == this) return true;
        if(!(obj instanceof Utilisateur)) return false;
        
        Utilisateur util = (Utilisateur) obj;
        return this.nom.equals(util.nom)  && this.prenom.equals(util.prenom) && this.mdp.equals(util.mdp) && this.getRoles().equals(util.getRoles());

    
    }
    @Override
    public int hashCode(){
        return this.prenom.hashCode()*2027 + this.nom.hashCode()*2081 + this.getRoles().hashCode()*10069;
    }
    @Override
    public String toString(){
        return "M.me "+this.nom +" "+this.prenom;
    }

}