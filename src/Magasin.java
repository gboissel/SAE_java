import java.util.ArrayList;
import java.util.List;

public class Magasin {
    private String nom;
    private String ville;
    private List<Vendeur> vendeurs;
    private List<Commande> commandes;
    private List<QteLivre> qtes;

    public Magasin(String nom,String ville){
        this.nom = nom;
        this.ville = ville;
        this.vendeurs = new ArrayList<>();
        this.commandes = new ArrayList<>();
    }
    
    /**
     * Retourne le nom du magasin.
     *
     * @return Le nom.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la ville du magasin.
     *
     * @return La ville.
     */
    public String getVille() {
        return ville;
    }

    public void addCommande(Commande comm){
        if (!(this.commandes.contains(comm))){
            this.commandes.add(comm);
        }
    }

    public void addVendeur(Vendeur ven){
        if (!(this.vendeurs.contains(ven))){
            this.vendeurs.add(ven);
        }
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
    public List<QteLivre> getQtes() {
        return this.qtes;
    }

    /**
     * Retourne les commandes associés
     * @return la liste des commandes
     */
    public List<Vendeur> getVendeur() {
        return this.vendeurs;
    }


    /**
     * 
     * @return role de l'utilisateur
     */
    public String getRole(){
        return "";
    }
    /**
     * decremente de 1 la quantité du livre, et le suprime si il y en a plus 
     * @param livre a enlever
     */
    public void removeLivre(Livre livre){
        
    }

    /**
     * met a jour la quantité d'un livre ou en ajoute un 
     * @param livre
     * @param qte
     */
    public void setQteLivre(Livre livre, int qte){

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
}
