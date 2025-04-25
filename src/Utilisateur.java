public abstract class Utilisateur{
    protected String nom;
    protected String prenom;
    protected String mdp;

    protected Utilisateur(String nom,String prenom,String mdp){
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp
    }
    public String getNom() {
        return nom;
    }
    public String  getPrenom(){
        return this.nom;
    }
    abstract String getRoles();
    @Override
    public boolean equals(Object obj) {
        if(this == null) return false;

        if(!(obj instanceof Utilisateur)) return false;
        
        Utilisateur util = (Utilisateur) obj;
        if(this.nom == util.nom && this.prenom == util.nom && this.mdp == util.mdp){
            return true;
        }return false;
    
    }

}