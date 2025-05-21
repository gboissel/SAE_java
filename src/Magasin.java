import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Magasin implements Comparable<Magasin>{
    private String nom;
    private String ville;
    private List<Vendeur> vendeurs;
    private List<Commande> commandes;
    private Map<Livre, Integer> stock;

    /**
     * Constructeur de la classe Magasin
     * @param nom Le nom du magasin
     * @param ville La ville où se situe le magasin
     */
    public Magasin(String nom,String ville){
        this.nom = nom;
        this.ville = ville;
        this.vendeurs = new ArrayList<>();
        this.commandes = new ArrayList<>();
        this.stock = new HashMap<>();
    }
    
    /**
     * Retourne le nom du magasin.
     *
     * @return Le nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Retourne la ville du magasin.
     *
     * @return La ville
     */
    public String getVille() {
        return this.ville;
    }

    /**
     * Récupère la liste des livres trouvable avec un stock supérieur à 0 dans ce magasin
     * @return Une liste de livres
     */
    public List<Livre> getLivres() {
        return new ArrayList<>(this.stock.keySet());
    }

    /**
     * Récupère le stock en magasin du livre donné en paramètre
     * @param livre Le livre
     * @return La quantité disponible du livre
     */
    public int getQteLivre(Livre livre) {
        if (this.stock.containsKey(livre)) {
            return this.stock.get(livre);
        }
        return 0;
    }

    /**
     * Retourne les commandes associés
     * @return la liste des commandes
     */
    public List<Commande> getCommandes() {
        return this.commandes;
    }

    /**
     * Retourne les commandes associés
     * @return la liste des commandes
     */
    public List<Vendeur> getVendeurs() {
        return this.vendeurs;
    }

     /**
     * Met à jour la quantité d'un livre, en ajoute un, ou bien en supprime un
     * @param livre
     * @param qte
     * @exception PasAssezDeLivre arrive lorsque la quantité de livre est strictement inférieure à 0
     */
    public void setQteLivre(Livre livre, int qte) throws PasAssezDeLivre{
        if (qte > 0) {
            this.stock.put(livre, qte);
            livre.addMagasin(this);
        }
        else if (qte == 0) {
            this.stock.remove(livre);
            livre.removeMagasin(this);
        }
        else {
            throw new PasAssezDeLivre();
        }
    }

    /**
     * Décrémente de 1 la quantité du livre, et le supprime si il y en a plus 
     * @param livre Le livre a enlever
     */
    public void removeLivre(Livre livre){
        try {
            this.setQteLivre(livre, this.getQteLivre(livre) - 1);
        }
        catch (PasAssezDeLivre e) {}
    }

    /**
     * Ajoute une commande parmis la liste des commandes
     * @param comm Une commande
     */
    public void addCommande(Commande comm){
        if (!(this.commandes.contains(comm))){
            this.commandes.add(comm);
        }
    }

    /**
     * Ajoute un vendeur parmis la liste des vendeurs
     */
    public void addVendeur(Vendeur ven){
        if (!(this.vendeurs.contains(ven))){
            this.vendeurs.add(ven);
        }
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Magasin)){
            return false;
        }
        Magasin auteur = (Magasin)obj;
        return (auteur.nom.equals(this.nom)&&auteur.ville.equals(this.ville));
    }

    @Override
    public int hashCode(){
        return this.nom.hashCode()*503+this.ville.hashCode()*7;
    }

    @Override
    public int compareTo(Magasin m) {
        return this.nom.compareTo(m.nom);
    }
}
