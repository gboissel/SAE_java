public class DetailCommande {
    private int qte;
    private double prixvente;
    private Commande commande;
    private Livre livre;

    public DetailCommande(int qte,double prixvente,Commande commande,Livre livre){
        this.qte=qte;
        this.prixvente=prixvente;
        this.commande=commande;
        this.livre=livre;
    }

    /**
     * Retourne la quantité commandée.
     *
     * @return La quantité.
     */
    public int getQte() {
        return qte;
    }

    /**
     * Retourne le prix de vente.
     *
     * @return Le prix de vente.
     */
    public double getPrixvente() {
        return prixvente;
    }

    /**
     * Retourne la commande associée.
     *
     * @return La commande.
     */
    public Commande getCommande() {
        return commande;
    }

    /**
     * Retourne le livre concerné.
     *
     * @return Le livre.
     */
    public Livre getLivre() {
        return livre;
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
        return this.livre.hashCode()+this.qte*31+this.commande.hashCode();
}
}
