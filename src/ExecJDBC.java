import java.sql.*;

public class ExecJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://servinfo-maria:3306/DBaudor";
        String utilisateur = "audor";
        String motDePasse = "audor";
//"jdbc:mysql://"+"servinfo-maria"+":3306/"+"DBaudor","audor","audor"
        try (Connection conn = DriverManager.getConnection(url, utilisateur, motDePasse);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nomauteur FROM AUTEUR;")) {
            rs.next();
            String res=rs.getString(1);
		    rs.close();
            System.out.println(res);

        } catch (SQLException e) {
            System.out.println("erreur");
        }
    }
}
