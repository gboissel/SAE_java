import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class Magasin implements Comparable<Magasin>{
    private String nom;
    private String ville;
    private List<Vendeur> vendeurs;
    private List<Commande> commandes;
    private Map<Livre, Integer> stock;

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

    public List<Livre> getLivres() {
        return new ArrayList<>(this.stock.keySet());
    }

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
     */
    public void setQteLivre(Livre livre, int qte){
        if (qte > 0) {
            this.stock.put(livre, qte);
            livre.addMagasin(this);
        }
        else {
            this.stock.remove(livre);
            livre.removeMagasin(this);
        }
    }

    /**
     * Décrémente de 1 la quantité du livre, et le suprime si il y en a plus 
     * @param livre Le livre a enlever
     */
    public void removeLivre(Livre livre){
        if (this.getQteLivre(livre) > 0) {
            if (this.getQteLivre(livre) == 1) {
                this.stock.remove(livre);
                livre.removeMagasin(this);
            }
            else {
                this.stock.put(livre, this.stock.get(livre) - 1);
            }
        }
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
