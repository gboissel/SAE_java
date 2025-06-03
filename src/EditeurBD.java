import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EditeurBD{
	ConnexionMySQL laConnexion;
	Statement st;
	public EditeurBD(ConnexionMySQL laConnexion){
		this.laConnexion=laConnexion;
	}

	/**
	 * Méthode permettant de récupérer tous les éditeurs de la base de données
	 * @return Une liste d'éditeurs
	 * @throws SQLException
	 */
	public List<Editeur> recupererEditeurs() throws SQLException{
		List<Editeur> lesEditeurs = new ArrayList<>();
		st=laConnexion.createStatement();
		ResultSet rs=st.executeQuery("SELECT nomedit from EDITEUR");
		String nom;
		while (rs.next()) {
			nom = rs.getString("nomedit");
			lesEditeurs.add(new Editeur(nom));
		}
		return lesEditeurs;
	}

}
