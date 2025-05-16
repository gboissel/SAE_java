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
	int insererJoueur( Joueur j){
		st=laConnexion.createStatement();
		st.executeUpdate("INSERT INTO JOUEUR VALUES("+j.getIdentifiant()+","+j.getPseudo()+","+j.getMotdepasse()+","+j.getNiveau()+","+j.getMain()+","+j.isAbonne()+"),");
	}


	void effacerJoueur(int num) throws SQLException {
		st=laConnexion.createStatement();
		st.executeUpdate("DELETE FROM JOUEUR WHERE numJoueur="+num+";");	
	}

}
