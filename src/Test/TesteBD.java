
import modele.*;



import java.sql.*;
import java.nio.file.*;
import java.io.*;

import  org.junit.jupiter.api.Test;
import  org.junit.jupiter.api.Assertions.assertEquals;
import  org.junit.jupiter.api.Assertions.assertTrue;


public class TesteBD {
    
    private static Connection conn;

    public static void Connecter() throws Exception {
        conn = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/testeBD",
            "admin",
            "adminpass"
        );
    }
    public static void Deconnecter() throws Exception {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
    @Test
    public void testClientInsertion() throws Exception {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT INTO CLIENT (idcli, nomcli, prenomcli, adressecli, codepostal, villecli, mdpcli) " 
        + "VALUES (2, 'Garcia', 'Hugo', '167 avenue de la Forêt', '60000', 'Nice', 'mdp_hug')");

        ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENT WHERE idcli = 2");
        Assert.assertTrue(rs.next());
        Assert.assertEquals("Garcia", rs.getString("nomcli"));
        Assert.assertEquals("Hugo", rs.getString("prenomcli"));
        Assert.assertEquals("Nice", rs.getString("villecli"));

        stmt.close();
    }
     @Test
    public void testMagasinInsertion() throws Exception {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT MAGASIN(idmag, nommag, villemag) " +
                "VALUES (4,Librairie jungle,Paris)");

        ResultSet rs = stmt.executeQuery("SELECT * FROM MAGASIN WHERE idmag = 4");
        Assert.assertTrue(rs.next());
        Assert.assertEquals("Librairie jungle", rs.getString("nommag"));
        Assert.assertEquals("Paris", rs.getString("villemag"));
        stmt.close();
    }
   
}
