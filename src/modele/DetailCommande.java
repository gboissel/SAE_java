package modele;

public class DetailCommande {
    private int qte;
    private double prixVente;
    private Commande commande;
    private Livre livre;

    /**
     * Constructeur de la classe DetailCommande, représentant les informations en rapport avec un livre dans une commande
     * @param com La commande auquelle est reliée cette instance
     * @param livre Le livre dont est question cette instance
     * @param qte La quantité de livre vendue dans cette commande
     * @param prix Le prix à l'unité auquel a été vendu le livre dans cette commande
     */
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
        if (!(obj instanceof DetailCommande)){
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
