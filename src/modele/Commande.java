package modele;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import JDBC.JDBC;
import exception.CommandeEnMagasinException;

public class Commande implements Comparable<Commande>{
    private int num;
    private String date;
    private boolean enLigne;
    private boolean domicile;
    private Client client;
    private Magasin magasin;
    private List<DetailCommande> lesLignes;

    public Commande(int num, String date, boolean enLigne, boolean domicile, Client client, Magasin magasin) {
        this.num = num;
        this.date = date;
        this.enLigne = enLigne;
        this.domicile = domicile;
        this.client = client;
        this.magasin = magasin;
        this.lesLignes = new ArrayList<>();
    }

    /**
     * Permet d'obtenir le numéro de commande
     * @return Le numéro
     */
    public int getNum() {
        return this.num;
    }

    /**
     * Permet de récupérer la date de la commande
     * @return La date sous le format String
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Permet de savoir si la commande a été réalisée en ligne ou non
     * @return Si true alors la commande a été réalisée en ligne
     * @return Si false elle a été réalisée en magasin
     */
    public boolean estEnLigne() {
        return this.enLigne;
    }

    /**
     * Permet de savoir si, pour une commande en ligne, elle est livrée à domicile
     * @return Si true si la commande est en ligne et livrée à domicile
     * @return Sinon false
     */
    public boolean livreDomicile() {
        return this.domicile;
    }

    /**
     * Permet de savoir pour qui la commande est destinée
     * @return Le client
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * Permet de savoir dans quel magasin la commande a été réalisée
     * @return le Magasin
     */
    public Magasin getMagasin() {
        return this.magasin;
    }

    /**
     * Permet d'obtenir les détails de la commande
     * @return Une liste de DetailCommande
     */
    public List<DetailCommande> getDetailsCommande() {
        return this.lesLignes;
    }

    /**
     * Permet de connaître les livres commandés et en quelle quantité
     * @return Un dictionnaire de Livre avec pour valeurs un entier représentant sa quantité
     */
    public Map<Livre, Integer> getLivres() {
        Map<Livre, Integer> lesLivres = new HashMap<>();
        for (DetailCommande dC:this.lesLignes) {
            lesLivres.put(dC.getLivre(), dC.getQte());
        }
        return lesLivres;
    }

    /**
     * Permet de connaître le prix total de la commande
     * @return Le prix de la commande
     */
    public double prixTotal() {
        double res = 0;
        for (DetailCommande dC:this.lesLignes) {
            res+=dC.getPrixVente() * dC.getQte();
        }
        return res;
    }

    /**
     * Permet de modifier le type de livraison de la commande
     * @param livraison Le nouveau type de livraison, true si elle est à domicile, false pour le reste
     * @param jdbc Une instance de la classe permettant d'intéragir avec la base de données
     * @throws CommandeEnMagasinException Arrive lorsque l'on tente de modifier le mode de livraison d'une commande en magasin
     */
    public void setLivraison(boolean domicile, JDBC jdbc) throws CommandeEnMagasinException{
        if (this.enLigne) {
            try{
                this.domicile = domicile;
                jdbc.modifierLivraisonCommande(this);
            }
            catch (SQLException e) {}
        }
        else {
            throw new CommandeEnMagasinException();
        }
    }

    /**
     * Permet d'ajouter une ligne, ce qui inclut le livre, sa quantité et son prix de vente à l'unité, à la commande
     * @param livre Le livre
     * @param qte La quantité acheté pour ce livre
     * @param prix Le prix unitaire
     */
    public void addLigne(Livre livre, int qte, double prix) {
        this.lesLignes.add(new DetailCommande(this, livre, qte, prix));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {return false;}
        if (o == this) {return true;}
        if (!(o instanceof Commande)) {return false;}
        Commande c = (Commande) o;
        return this.num == c.num;
    }

    @Override
    public int hashCode() {
        return this.num * 97;
    }

    @Override
    public int compareTo(Commande c) {
        return this.num - c.num;
    }
}