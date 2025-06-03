import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagasinBD {
    ConnexionMySQL laConnexion;
    Statement st;
    public MagasinBD(ConnexionMySQL laConnexion) {
        this.laConnexion = laConnexion;
    }

    /**
     * Méthode permettant de récupérer tous les magasins de la base de données,
     * en plus de les associer avec les livres déjà chargés
     * @param lesLivres La liste des livres
     * @return Une liste de magasins
     * @throws SQLException
     */
    public List<Magasin> recupererMagasins(List<Livre> lesLivres) throws SQLException, PasAssezDeLivre{
        List<Magasin> lesMagasins = new ArrayList<>();
        st=laConnexion.createStatement();
        ResultSet rs=st.executeQuery("SELECT nommag, villemag FROM MAGASIN");
        Magasin magasin;
        String nom;
        String ville;
        while (rs.next()) {
            nom = rs.getString("nommag");
            ville = rs.getString("villemag");
            magasin = new Magasin(nom, ville);
            lesMagasins.add(magasin);
            ajouterLivres(magasin, lesLivres);
        }
        return lesMagasins;
    }

    /**
     * Méthode permettant d'ajouter le stock de livres au magasin
     * @param magasin Le magasin
     * @param lesLivres La liste de livres
     * @throws SQLException
     */
    private void ajouterLivres(Magasin magasin, List<Livre> lesLivres) throws SQLException, PasAssezDeLivre {
        st=laConnexion.createStatement();
        ResultSet rs=st.executeQuery("SELECT idmag, isbn, qte FROM POSSEDER "+
                                    "NATURAL JOIN MAGASIN "+
                                    "WHERE nommag=" + magasin.getNom());
        int isbn;
        int qte;
        while (rs.next()) {
            isbn = Integer.parseInt(rs.getString("isbn"));
            qte = rs.getInt("qte");
            magasin.setQteLivre(lesLivres.get(lesLivres.indexOf(new Livre(isbn, "", 0, null, null))), qte);
        }
    }
}
