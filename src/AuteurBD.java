import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuteurBD{
	ConnexionMySQL laConnexion;
	Statement st;
	public AuteurBD(ConnexionMySQL laConnexion){
		this.laConnexion=laConnexion;
	}

	/**
	 * Méthode permettant de récupérer tous les auteurs de la base de données
	 * @return Une liste d'auteurs
	 * @throws SQLException
	 */
	public List<Auteur> recupererAuteurs() throws SQLException{
		List<Auteur> lesAuteurs = new ArrayList<>();
		st=laConnexion.createStatement();
		ResultSet rs=st.executeQuery("SELECT nomauteur, anneenais, anneedeces from AUTEUR");
		String nom;
		Integer naissance;
		Integer deces;
		while (!rs.isLast()) {
			rs.next();
			nom = rs.getString(1);
			naissance = rs.getInt(2);
			if (naissance.equals(0)) {naissance = null;}
			deces = rs.getInt(3);
			if (deces.equals(0)) {deces = null;}
			lesAuteurs.add(new Auteur(nom, naissance, deces));
		}
		return lesAuteurs;
	}

}
