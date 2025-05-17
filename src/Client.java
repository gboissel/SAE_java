import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
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
            Commande commande = new Commande(new Random().nextInt(10000), date, enLigne, domicile, this, magasin);

            for (Livre livre:lesLivres.keySet()) {
                magasin.setQteLivre(livre, lesLivres.get(livre));
                commande.addLigne(livre, lesLivres.get(livre), livre.getPrix());
            }

            this.commandes.add(commande);
        }
        catch (PasAssezDeLivre e) {}
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

    public Set<Livre> getLivres() {
        Set<Livre> lesLivres = new HashSet<>();
        for (Commande com : this.commandes) {
            lesLivres.addAll(com.getLivres().keySet());
        }
        return lesLivres;
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
                    dicoCat.put(cat, dicoCat.getOrDefault(cat, 0) + dC.getQte());
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
                for (Auteur auteur:dC.getLivre().getAuteurs()) {
                    dicoAut.put(auteur, dicoAut.getOrDefault(auteur, 0) + dC.getQte());
                }
            }
        }
        dicoTrie.putAll(dicoAut);
        return dicoTrie;
    }

    private Map<Livre, Integer> livresPreferes() {
        Map<Livre, Integer> dicoLivre = new HashMap<>();
        TrieMapLivre tri = new TrieMapLivre(dicoLivre);
        Map<Livre, Integer> dicoTrie = new TreeMap<>(tri);
        Map<Categorie, Integer> dicoCat = this.categoriesPreferees();
        Map<Auteur, Integer> dicoAut = this.auteursPreferes();
        for (Livre livre:this.getLivres()) {
            for (Categorie cat:dicoCat.keySet()) {
                if (livre.getClassification().contains(cat)) {
                    dicoLivre.put(livre, dicoLivre.getOrDefault(livre, 0) + dicoCat.get(cat));
                }
            }
            for (Auteur aut:dicoAut.keySet()) {
                if (livre.getAuteurs().contains(aut)) {
                    dicoLivre.put(livre, dicoLivre.getOrDefault(livre, 0) + dicoAut.get(aut));
                }
            }
        }
        dicoTrie.putAll(dicoLivre);
        return dicoTrie;
    }

    public List<Livre> onVousRecommande(){
        Map<Livre, Integer> livresPref = this.livresPreferes();
        Set<Livre> setLivres = new HashSet<>(livresPref.keySet());
        Map<Livre, Integer> livresPrefClient;
        Map<Livre, Integer> recommandation = new HashMap<>();
        for (Livre livre:livresPref.keySet()) {
            for (Commande com:livre.getCommandes()) {
                if (!this.equals(com.getClient())) {
                    livresPrefClient = com.getClient().livresPreferes();
                    for (Livre livreReco:livresPrefClient.keySet()) {
                        if (!setLivres.contains(livreReco)) {
                            recommandation.put(livreReco, recommandation.getOrDefault(livreReco, 0) + livresPrefClient.get(livreReco));
                            setLivres.add(livreReco);
                        }
                    }
                }
            }
        }
        TrieMapLivre tri = new TrieMapLivre(recommandation);
        Map<Livre, Integer> recoTrie = new TreeMap<>(tri);
        recoTrie.putAll(recommandation);
        return new ArrayList<>(recoTrie.keySet());
    }

    @Override
    public String toString() {
        return "Client : " + this.prenom  + " " + this.nom;
    }
}
