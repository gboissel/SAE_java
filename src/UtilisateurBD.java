import java.sql.*;

public class UtilisateurBD{
	ConnexionMySQL laConnexion;
	Statement st;
	public UtilisateurBD(ConnexionMySQL laConnexion){
		this.laConnexion=laConnexion;
	}
	public int maxNumJoueur() throws SQLException{
		st=laConnexion.createStatement();
		ResultSet rs=st.executeQuery("select IFNULL(max(numjoueur),0) lemax from joueur;");
		rs.next();
		int res=rs.getInt(1);
		rs.close();
		return res;
	}


}
