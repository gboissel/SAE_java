import java.sql.*;
import java.util.ArrayList;
import java.util.AbstractMap;
import java.util.Map;

public class UtilisateurBD {
	ConnexionMySQL laConnexion;
	Statement st;
	private Utilisateur user;
	UtilisateurBD(ConnexionMySQL laConnexion,Utilisateur user){
		this.laConnexion=laConnexion;
		this.user=user;
	}

	int maxNumJoueur() throws SQLException{
		st=laConnexion.createStatement();
		ResultSet rs=st.executeQuery("select IFNULL(max(numjoueur),0) lemax from joueur;");
		rs.next();
		int res=rs.getInt(1);
		rs.close();
		return res;
	}


	int insererJoueur( Joueur j){
		st=laConnexion.createStatement();
		st.executeUpdate("INSERT INTO JOUEUR VALUES("+j.getIdentifiant()+","+j.getPseudo()+","+j.getMotdepasse()+","+j.getNiveau()+","+j.getMain()+","+j.isAbonne()+"),");
	}


	void effacerJoueur(int num) throws SQLException {
		st=laConnexion.createStatement();
		st.executeUpdate("DELETE FROM JOUEUR WHERE numJoueur="+num+";");	
	}

    void majJoueur(Joueur j)throws SQLException{
		throw new SQLException("méthode majJoueur à implémenter");
    }

    Joueur rechercherJoueurParNum(int num)throws SQLException{
		if(num>6 || num<0){
			throw new SQLException("joueur nom trouvé");
		}
		st=laConnexion.createStatement();
		st.executeUpdate("select * FROM JOUEUR WHERE numJoueur="+num+";");
    }

	ArrayList<Joueur> listeDesJoueurs() throws SQLException{
		throw new SQLException("méthode listeDesJoueurs à implémenter");
	}
	
	String rapportMessage() throws SQLException{
		return "rapportMessage A faire";
	}
	
	String rapportMessageComplet() throws SQLException{
		return "rapportMessageComplet A faire";	
	}

	ArrayList<Map.Entry<String, Integer>> nbMsgParJour() throws SQLException{
		// Pour créer une valeur pouvant être ajoutée à l'ArrayList<Map.Entry<String, Integer>>
		// faire un new AbstractMap.SimpleEntry<>("coucou",45)
		throw new SQLException("méthode nbMsgParJour à implémenter");
	}
	ArrayList<Map.Entry<String, Integer>> nbMain() throws SQLException{
		// Pour créer une valeur pouvant être ajoutée à l'ArrayList<Map.Entry<String, Integer>>
		// faire un new AbstractMap.SimpleEntry<>("coucou",45)
		throw new SQLException("méthode nbMain à implémenter");
	}	
}
