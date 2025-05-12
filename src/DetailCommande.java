public class DetailCommande {
    private int qte;
    private double prixVente;
    private Commande commande;
    private Livre livre;

    public DetailCommande(Commande com,Livre livre,int qte,double prix){
        this.qte=qte;
        this.prixVente=prix;
        this.livre=livre;
        this.commande=com;
        livre.addCommande(com);
    }

    /**
     * Retourne la quantité commandée.
     *
     * @return La quantité.
     */
    public int getQte() {
        return this.qte;
    }

    /**
     * Retourne le prix de vente.
     *
     * @return Le prix de vente.
     */
    public double getPrixVente() {
        return this.prixVente;
    }

    /**
     * Retourne la commande associée.
     *
     * @return La commande.
     */
    public Commande getCommande() {
        return this.commande;
    }

    /**
     * Retourne le livre concerné.
     *
     * @return Le livre.
     */
    public Livre getLivre() {
        return this.livre;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Auteur)){
            return false;
        }
        DetailCommande edit = (DetailCommande)obj;
        return (edit.livre.equals(this.livre)&&this.qte==edit.qte);
    }

    @Override
    public int hashCode(){
        return this.livre.hashCode()+this.commande.hashCode();
}
}
