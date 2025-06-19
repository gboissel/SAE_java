package modele;

import java.util.HashMap;

public class Panier extends HashMap<Livre,Integer>{
    public boolean estVide(){
        return this.keySet().size() == 0;
    }
    
    /**
     * Méthode permettant d'ajouter un livre au panier, si le livre est déjà présent cela ajoute la nouvelle quantité à la quantité précédente
     * @param livre Le livre
     * @param qte La qte à ajouter au panier
     */
    public void ajouter(Livre livre, int qte) {
        this.put(livre, this.getOrDefault(livre, 0) + qte);
    }

    /**
     * Méthode permettant d'enlever un livre du panier
     * @param livre Le livre
     */
    public void supprimer(Livre livre) {
        this.remove(livre);
    }

    /**
     * Méthode permettant de retirer des exemplaires du livre dans le panier, sans le supprimer, sans aller dans une valeur negative
     * @param livre Le livre
     * @param qte La quantité
     */
    public void retirerQte(Livre livre, int qte) {
        if(!(this.get(livre) - qte<0)){
            this.put(livre, this.get(livre) - qte);
        }
    }

    /**
     * Méthode permettant de connaître le prix total du panier
     * @return Le prix total
     */
    public double prixTotal() {
        double total = 0;
        for (Livre livre:this.keySet()) {
            System.out.println(livre.getPrix());
            System.out.println(this.get(livre));
            total = total + livre.getPrix() * this.get(livre);
        }
        return total;
    }

    /**
     * Méthode permettant de connaître le prix total d'un livre de la commande
     * @param livre Le livre
     * @return Le prix total du livre, 0 si il n'est pas dans le panier
     */
    public double prixTotalLivre(Livre livre) {
        return this.getOrDefault(livre, 0) * livre.getPrix();
    }
    
    @Override
    public String toString() {
        String texte = "Panier :";
        String espacement = "";
        for(Livre livre:this.keySet()) {
            texte = texte + "\n- " + livre.getTitre();
            for(int i = 0; i<40-livre.getTitre().length(); ++i) {
                espacement = espacement + " ";
            }
            texte = texte + espacement + "Qte : " + this.get(livre);
            espacement = "";
        }
        texte = texte + "\nTotal : " + this.prixTotal() + "€";
        return texte;
    }
}
