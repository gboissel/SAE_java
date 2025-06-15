package test;
import modele.*;
import JDBC.*;

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
    public void testClientInsertion() throws SQLException {
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
    public void testMagasinInsertion() throws SQLException {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT INTO MAGASIN(idmag, nommag, villemag) " +
                "VALUES (4,Librairie jungle,Paris)");

        ResultSet rs = stmt.executeQuery("SELECT * FROM MAGASIN WHERE idmag = 4");
        Assert.assertTrue(rs.next());
        Assert.assertEquals("Librairie jungle", rs.getString("nommag"));
        Assert.assertEquals("Paris", rs.getString("villemag"));
        stmt.close();
    }
    @Test
    public void testEditeurInsertion() throws SQLException {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT INTO EDITEUR(nomedit,idedit) " +
                "VALUES (Hachette,10)");

        ResultSet rs = stmt.executeQuery("SELECT * FROM EDITEUR WHERE idedit = 10");
        Assert.assertTrue(rs.next());
        Assert.assertEquals("Hachette", rs.getString("nomedit"));
        stmt.close();
    }
     @Test
    public void testAuteurInsertion() throws SQLException {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT INTO AUTEUR(idauteur, nomauteur,anneenais,anneedeces " +
                "VALUES ('OL625587', 'Jean Michel', 1802, 1898)");

        ResultSet rs = stmt.executeQuery("SELECT * FROM AUTEUR WHERE idauteur = OL625587");
        Assert.assertTrue(rs.next());
        Assert.assertEquals("Jean Miche", rs.getString("nomauteur"));
        Assert.assertEquals("1802", rs.getInt("anneenais"));
        Assert.assertEquals("1898", rs.getInt("anneedeces"));
        stmt.close();
        
    }
     @Test
    public void testLivreInsertion() throws SQLException {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT INTO LIVRE(isbn, titre,nbpages,datepubli,prix) " +
                "VALUES (978436055252, Escale, 65, 2010, 40.76)");

        ResultSet rs = stmt.executeQuery("SELECT * FROM LIVRE WHERE isbn = 978436055252");
        Assert.assertTrue(rs.next());
        Assert.assertEquals("Escale", rs.getString("titre"));
        Assert.assertEquals("65", rs.getInt("nbpages"));
        Assert.assertEquals("2010", rs.getString("datepubli"));
        stmt.close();
    }
    @Test
    public void testAdministrateurInsertion() throws SQLException {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT INTO ADMINISTRATEUR (idadmin,nomadmin,prenomadmin,mdpadmin) " +
                "VALUES (4, Tuj, Claire, mdp_Tuj)");

        ResultSet rs = stmt.executeQuery("SELECT * FROM ADMINISTRATEUR  WHERE idadmin = 4");
        Assert.assertTrue(rs.next());
        Assert.assertEquals("Tuj", rs.getString("nomadmin"));
        Assert.assertEquals("Claire", rs.getString("prenomadmin"));

        stmt.close();
    }
    @Test
    public void testVendeurInsertion() throws SQLException {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT INTO VENDEUR (idven, nomven, prenomven, mdpven, idmag)" +
                "VALUES (12, POL, Laure, mdp_LP, 4)");

        ResultSet rs = stmt.executeQuery("SELECT * FROM VENDEUR WHERE idedit = 12");
        Assert.assertTrue(rs.next());
        Assert.assertEquals("POL", rs.getString("nomven"));
        Assert.assertEquals("Laure", rs.getString("prenomven"));

        stmt.close();
    }
    @Test
    public void testCommandeInsertion() throws SQLException {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT INTO COMMANDE(numcom, datecom, enligne, livraison, idcli, idmag) " +
                "VALUES (6,1/8/2022,'N','M',2,4)");

        ResultSet rs = stmt.executeQuery("SELECT * FROM COMMANDE WHERE numcom = 6");
        Assert.assertTrue(rs.next());
        Assert.assertEquals("N", rs.getString("enligne"));
        Assert.assertEquals("1/8/2022", rs.getString("datecom"));
        stmt.close();
    }
   
}
