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

        if(!(obj instanceof Utilisateur)) return false;
        
        Utilisateur util = (Utilisateur) obj;
        if(this.nom == util.nom && this.prenom == util.nom && this.mdp == util.mdp && this.getRoles()==util.getRoles()){
            return true;
        }return false;
    
    }

}