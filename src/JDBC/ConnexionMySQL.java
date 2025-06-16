package JDBC;
import java.sql.*;

public class ConnexionMySQL {
	private Connection mysql=null;
	private boolean connecte=false;
	public ConnexionMySQL() throws ClassNotFoundException{
		this.mysql=null;
		this.connecte=false;	
		Class.forName("org.mariadb.jdbc.Driver");
		}

	public void connecter(String Driver,String nomBase, String nomLogin, String motDePasse) throws SQLException {
		// si tout s'est bien passé la connexion n'est plus nulle
		this.mysql=null;
		this.connecte=false;
		this.mysql = DriverManager.getConnection(
			"jdbc:mariadb://"+Driver+":3306/"+nomBase,nomLogin,motDePasse);//servinfo-maria pour l'IUT  
		this.connecte=true;// 3306  est le port par défaut pas besoin de le modifier
	}
	
	public void close() throws SQLException {
		// fermer la connexion
		this.mysql.close();
		this.connecte=false;
	}

    public boolean isConnecte() { 
		return this.connecte;
	}

	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
}
