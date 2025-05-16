import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Client extends Utilisateur{
    private List<Commande> commandes;
    /**
     * Creer une instance d'un Utilisateur qui est un Client
     * @param nom
     * @param prenom
     * @param mdp
     */
    public Client(String nom,String prenom,String mdp){
        super(nom,prenom,mdp);
        this.commandes = new ArrayList<>();
    }
    @Override
    public String getRoles() {
        return "Client";
    }

    public void commander(Map<Livre, Integer> lesLivres, boolean enLigne, boolean domicile, Magasin magasin){
        try{
            LocalDateTime dateActuelle = LocalDateTime.now();
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = dateActuelle.format(formatDate);

            // A modifier lorsque l'on mettra en place le JDBC pour attribuer un nouveau numéro
            Commande commande = new Commande(0, date, enLigne, domicile, this, magasin);

            for (Livre livre:lesLivres.keySet()) {
                magasin.setQteLivre(livre, lesLivres.get(livre));
                commande.addLigne(livre, lesLivres.get(livre), livre.getPrix());
            }
        }
        catch (PasAssezDeLivre e) {};
    }

    public void ajouterCommande(Commande commande){
        this.commandes.add(commande);
    }

    public Commande getCommande(int num) throws PasAvoirCommande{
        Commande commandeExemple = new Commande(num, "", false, false, null, null); 
        for (Commande com : this.commandes) {
            if (com.equals(commandeExemple)) {
                return com;
            }
        }
        throw new PasAvoirCommande();
    }

    public void modifierModeLivraisonCommande(int num, boolean domicile){
        try{
            Commande commande = this.getCommande(num);
            commande.setLivraison(domicile);
        }
        catch (PasAvoirCommande e) {}
    }

    public List<Commande> consulterCommandes(){  
        return this.commandes;
    }

    private Map<Categorie, Integer> categoriesPreferees() {
        Map<Categorie, Integer> dicoCat = new HashMap<>();
        TrieMapCategorie tri = new TrieMapCategorie(dicoCat);
        Map<Categorie, Integer> dicoTrie = new TreeMap<>(tri);

        for (Commande commande:this.commandes) {
            for (DetailCommande dC:commande.getDetailsCommande()) {
                for (Categorie cat:dC.getLivre().getClassification()) {
                    dicoCat.put(cat, dicoCat.getOrDefault(dicoCat, 0) + 1);
                }
            }
        }
        dicoTrie.putAll(dicoCat);
        return dicoTrie;
    }

    private Map<Auteur, Integer> auteursPreferes() {
        Map<Auteur, Integer> dicoAut = new HashMap<>();
        TrieMapAuteur tri = new TrieMapAuteur(dicoAut);
        Map<Auteur, Integer> dicoTrie = new TreeMap<>(tri);
        for (Commande commande:this.commandes) {
            for (DetailCommande dC:commande.getDetailsCommande()) {
                for (Auteur cat:dC.getLivre().getAuteurs()) {
                    dicoAut.put(cat, dicoAut.getOrDefault(dicoAut, 0) + 1);
                }
            }
        }
        dicoTrie.putAll(dicoAut);
        return dicoTrie;
    }

    public List<Livre> onVousRecommande(){
        List<Livre> res  = new ArrayList<>();
        return res;
    }
}
