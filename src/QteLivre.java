public class QteLivre {
    private int qte;
    private Livre livre;
    private Magasin mag;

    public QteLivre(int qte,Livre livre,Magasin mag){
        this.livre=livre;
        this.qte=qte;
        this.mag=mag;
    }

    /**
     * Retourne la quantité du livre.
     *
     * @return La quantité.
     */
    public int getQte() {
        return qte;
    }

    /**
     * Modifie la quantité du livre.
     *
     * @param qte La nouvelle quantité.
     */
    public void setQte(int qte) {
        this.qte = qte;
    }

    /**
     * Retourne le livre concerné.
     *
     * @return Le livre.
     */
    public Livre getLivre() {
        return livre;
    }

    /**
     * Retourne le magasin concerné.
     *
     * @return Le magasin.
     */
    public Magasin getMag() {
        return mag;
    }
}
