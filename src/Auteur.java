public class Auteur {
    private String nom;
    private Integer datenaissance;
    private Integer datemort;

    /**
     * RConstructeur de la class Auteur.
     *
     */
    public Auteur(String nom,Integer datenaissance, Integer datemort){
        this.nom=nom;
        this.datenaissance=datenaissance;
        this.datemort=datemort;
    }

    /**
     * Constructeur de la class Auteur si l'auteur est toujours en vie.
     *
     */
    public Auteur(String nom,Integer datenaissance){
        this.nom=nom;
        this.datenaissance=datenaissance;
        this.datemort=null;
    }

    /**
     * Retourne le nom de l'auteur.
     *
     * @return Le nom.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom de l'auteur.
     *
     * @param nom Le nouveau nom.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne l'année de naissance de l'auteur.
     *
     * @return L'année de naissance.
     */
    public Integer getDatenaissance() {
        return datenaissance;
    }

    /**
     * Modifie l'année de naissance de l'auteur.
     *
     * @param datenaissance La nouvelle année de naissance.
     */
    public void setDatenaissance(Integer datenaissance) {
        this.datenaissance = datenaissance;
    }

    /**
     * Retourne l'année de décès de l'auteur (ou null s'il est toujours en vie).
     *
     * @return L'année de décès ou null.
     */
    public Integer getDatemort() {
        return datemort;
    }

    /**
     * Modifie l'année de décès de l'auteur.
     *
     * @param datemort La nouvelle année de décès.
     */
    public void setDatemort(Integer datemort) {
        this.datemort = datemort;
    }
}

