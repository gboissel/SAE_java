import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreBD {
    ConnexionMySQL laConnexion;
    Statement st;
    public LivreBD(ConnexionMySQL laConnexion) {
        this.laConnexion=laConnexion;
    }

    /**
     * Méthode permettant de récupérer tous les livres de la bases de données,
     * en plus de les associés avec les auteurs, editeurs et catégories déjà chargés
     * @param lesAuteurs La liste des auteurs
     * @param lesEditeurs La liste des éditeurs
     * @param lesCategories La liste des catégories
     * @return Une liste de livres
     * @throws SQLException
     */
    public List<Livre> recupererLivres(List<Auteur> lesAuteurs, List<Editeur> lesEditeurs, List<Categorie> lesCategories) throws SQLException{
        List<Livre> lesLivres = new ArrayList<>();
        st=laConnexion.createStatement();
        ResultSet rs=st.executeQuery("SELECT * FROM LIVRE");
        Livre livre;
        int isbn;
        String titre;
        double prix;
        Integer pages;
        Integer datePubli;
        while (rs.next()) {
            isbn = rs.getInt("isbn");
            titre = rs.getString("titre");
            prix = rs.getDouble("prix");
            pages = rs.getInt("nbpages");
            if (pages.equals(0)) {pages = null;}
            datePubli = rs.getInt("datepubli");
            if (datePubli.equals(0)) {pages = null;}
            livre = new Livre(isbn, titre, prix, pages, datePubli);
            lesLivres.add(livre);
            ajouterAuteurs(livre, lesAuteurs);
            ajouterEditeurs(livre, lesEditeurs);
            ajouterClassification(livre, lesCategories);
        }
        return lesLivres;
    }

    /**
     * Méthode permettant d'associer un livre avec les auteurs déjà chargés
     * @param livre Le livre
     * @param lesAuteurs La liste des auteurs
     * @throws SQLException
     */
    private void ajouterAuteurs(Livre livre, List<Auteur> lesAuteurs) throws SQLException{
        st=laConnexion.createStatement();
        ResultSet rs=st.executeQuery("SELECT isbn, nomauteur FROM ECRIRE " +
                                    "NATURAL JOIN LIVRE " +
                                    "WHERE isbn = " + livre.getISBN());
        String nomauteur;
        while (rs.next()) {
            nomauteur = rs.getString("nomauteur");
            livre.addAuteur(lesAuteurs.get(lesAuteurs.indexOf(new Auteur(nomauteur, null))));
        }
    }

    /**
     * Méthode permettant d'associer un livre avec les éditeurs déjà chargés
     * @param livre Le livre
     * @param lesEditeurs La liste des éditeurs
     * @throws SQLException
     */
    private void ajouterEditeurs(Livre livre, List<Editeur> lesEditeurs) throws SQLException{
        st=laConnexion.createStatement();
        ResultSet rs=st.executeQuery("SELECT isbn, nomedit FROM EDITER " +
                                    "NATURAL JOIN EDITEUR " +
                                    "WHERE isbn = " + livre.getISBN());
        String nomediteur;
        while (rs.next()) {
            nomediteur = rs.getString("nomedit");
            livre.addEditeur(lesEditeurs.get(lesEditeurs.indexOf(new Editeur(nomediteur))));
        }
    }

    /**
     * Méthode permettant de classifier un livre en l'associant avec les catégories déjà chargées
     * @param livre Le livre
     * @param lesCategories La liste des catégories
     * @throws SQLException
     */
    private void ajouterClassification(Livre livre, List<Categorie> lesCategories) throws SQLException{
        st=laConnexion.createStatement();
        ResultSet rs=st.executeQuery("SELECT isbn, nomclass FROM THEMES " +
                                    "NATURAL JOIN CLASSIFICATION " +
                                    "WHERE isbn = " + livre.getISBN());
        String nomcategorie;
        while (rs.next()) {
            nomcategorie = rs.getString("nomclass");
            livre.addClassification(lesCategories.get(lesCategories.indexOf(new Categorie(nomcategorie))));
        }
    }
}
