import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieBD{
	ConnexionMySQL laConnexion;
	Statement st;
	public CategorieBD(ConnexionMySQL laConnexion){
		this.laConnexion=laConnexion;
	}

	/**
	 * Méthode permettant de récupérer toutes les catégories de la base de données
	 * @return Une liste de catégories
	 * @throws SQLException
	 */
	public List<Categorie> recupererCategories() throws SQLException{
		List<Categorie> lesCategories = new ArrayList<>();
		st=laConnexion.createStatement();
		ResultSet rs=st.executeQuery("SELECT nomclass from CLASSIFICATION");
		String nom;
		while (!rs.isLast()) {
			rs.next();
			nom = rs.getString(1);
			lesCategories.add(new Categorie(nom));
		}
		return lesCategories;
	}

}
