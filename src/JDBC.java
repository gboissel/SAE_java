import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
    ConnexionMySQL laConnexion;
    Statement st;
    public JDBC(ConnexionMySQL laConnexion) {
        this.laConnexion = laConnexion;
    }

    /**
     * Méthode permettant de récupérer tous les livres de la base de données,
     * en plus de les associer avec les auteurs, editeurs et catégories déjà chargés
     * @param lesAuteurs La liste des auteurs
     * @param lesEditeurs La liste des éditeurs
     * @param lesCategories La liste des catégories
     * @return Une liste de livres
     * @throws SQLException
     */
    public List<Livre> recupererLivres(List<Auteur> lesAuteurs, List<Editeur> lesEditeurs, List<Categorie> lesCategories) throws SQLException{
        List<Livre> lesLivres = new ArrayList<>();
        st=laConnexion.createStatement();
        ResultSet rs=st.executeQuery("SELECT * FROM LIVRE");
        Livre livre;
        int isbn;
        String titre;
        double prix;
        Integer pages;
        Integer datePubli;
        while (rs.next()) {
            isbn = Integer.parseInt(rs.getString("isbn"));
            titre = rs.getString("titre");
            prix = rs.getDouble("prix");
            pages = rs.getInt("nbpages");
            if (pages.equals(0)) {pages = null;}
            datePubli = rs.getInt("datepubli");
            if (datePubli.equals(0)) {pages = null;}
            livre = new Livre(isbn, titre, prix, pages, datePubli);
            lesLivres.add(livre);
            ajouterAuteurs(livre, lesAuteurs);
            ajouterEditeurs(livre, lesEditeurs);
            ajouterClassification(livre, lesCategories);
        }
        return lesLivres;
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
		while (rs.next()) {
			nom = rs.getString("nomauteur");
			naissance = rs.getInt("anneenais");
			if (naissance.equals(0)) {naissance = null;}
			deces = rs.getInt("anneedeces");
			if (deces.equals(0)) {deces = null;}
			lesAuteurs.add(new Auteur(nom, naissance, deces));
		}
		return lesAuteurs;
	}

    /**
     * Méthode permettant d'associer un livre avec les auteurs déjà chargés
     * @param livre Le livre
     * @param lesAuteurs La liste des auteurs
     * @throws SQLException
     */
    private void ajouterAuteurs(Livre livre, List<Auteur> lesAuteurs) throws SQLException{
        PreparedStatement ps = laConnexion.prepareStatement("SELECT isbn, nomauteur FROM ECRIRE " +
                                    "NATURAL JOIN LIVRE " +
                                    "WHERE isbn = ?");
        ps.setString(1, "" + livre.getISBN());
        ResultSet rs=ps.executeQuery();
        String nomauteur;
        while (rs.next()) {
            nomauteur = rs.getString("nomauteur");
            livre.addAuteur(lesAuteurs.get(lesAuteurs.indexOf(new Auteur(nomauteur, null))));
        }
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

    /**
     * Méthode permettant d'associer un livre avec les éditeurs déjà chargés
     * @param livre Le livre
     * @param lesEditeurs La liste des éditeurs
     * @throws SQLException
     */
    private void ajouterEditeurs(Livre livre, List<Editeur> lesEditeurs) throws SQLException{
        PreparedStatement ps = laConnexion.prepareStatement("SELECT isbn, nomedit FROM EDITER " +
                                    "NATURAL JOIN EDITEUR " +
                                    "WHERE isbn = ?");
        ps.setString(1, "" + livre.getISBN());
        ResultSet rs=ps.executeQuery();
        String nomediteur;
        while (rs.next()) {
            nomediteur = rs.getString("nomedit");
            livre.addEditeur(lesEditeurs.get(lesEditeurs.indexOf(new Editeur(nomediteur))));
        }
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
		while (rs.next()) {
			nom = rs.getString("nomclass");
			lesCategories.add(new Categorie(nom));
		}
		return lesCategories;
	}

    /**
     * Méthode permettant de classifier un livre en l'associant avec les catégories déjà chargées
     * @param livre Le livre
     * @param lesCategories La liste des catégories
     * @throws SQLException
     */
    private void ajouterClassification(Livre livre, List<Categorie> lesCategories) throws SQLException{

        PreparedStatement ps=laConnexion.prepareStatement("SELECT isbn, nomclass FROM THEMES " +
                                    "NATURAL JOIN CLASSIFICATION " +
                                    "WHERE isbn = ?");
        ps.setString(1, "" + livre.getISBN());
        ResultSet rs=ps.executeQuery();
        String nomcategorie;
        while (rs.next()) {
            nomcategorie = rs.getString("nomclass");
            livre.addClassification(lesCategories.get(lesCategories.indexOf(new Categorie(nomcategorie))));
        }
    }

    /**
     * Méthode permettant de récupérer tous les magasins de la base de données,
     * en plus de les associer avec les livres déjà chargés
     * @param lesLivres La liste des livres
     * @return Une liste de magasins
     * @throws SQLException
     * @throws PasAssezDeLivreException
     */
    public List<Magasin> recupererMagasins(List<Livre> lesLivres) throws SQLException, PasAssezDeLivreException{
        List<Magasin> lesMagasins = new ArrayList<>();
        st=laConnexion.createStatement();
        ResultSet rs=st.executeQuery("SELECT nommag, villemag FROM MAGASIN");
        Magasin magasin;
        String nom;
        String ville;
        while (rs.next()) {
            nom = rs.getString("nommag");
            ville = rs.getString("villemag");
            magasin = new Magasin(nom, ville);
            lesMagasins.add(magasin);
            ajouterLivres(magasin, lesLivres);
        }
        return lesMagasins;
    }

    /**
     * Méthode permettant d'ajouter le stock de livres au magasin
     * @param magasin Le magasin
     * @param lesLivres La liste de livres
     * @throws SQLException
     * @throws PasAssezDeLivreException
     */
    private void ajouterLivres(Magasin magasin, List<Livre> lesLivres) throws SQLException, PasAssezDeLivreException {
        st=laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("SELECT idmag, isbn, qte FROM POSSEDER "+
                                    "NATURAL JOIN MAGASIN "+
                                    "WHERE nommag=?");
        ps.setString(1, magasin.getNom());
        ResultSet rs=ps.executeQuery();
        int isbn;
        int qte;
        while (rs.next()) {
            isbn = Integer.parseInt(rs.getString("isbn"));
            qte = rs.getInt("qte");
            magasin.setQteLivre(lesLivres.get(lesLivres.indexOf(new Livre(isbn, "", 0, null, null))), qte);
        }
    }

    /**
	 * Méthode permettant de récupérer tous les clients, vendeurs et administarteurs de la base de données
	 * @param lesMagasins Les magasins
	 * @param lesLivres Les livres
	 * @return Une liste d'utilisateurs
	 * @throws SQLException
	 */
	public List<Utilisateur> recupererUtilisateurs(List<Magasin> lesMagasins, List<Livre> lesLivres) throws SQLException {
		List<Utilisateur> lesUtilisateurs = new ArrayList<>();
		st=laConnexion.createStatement();
		ResultSet rs=st.executeQuery("SELECT nomcli, prenomcli, adressecli, codepostal, villecli, mdpcli FROM ADMIN");
		String nom;
		String prenom;
		String adresse;
		String cp;
		String ville;
		String mdp;
		Client client;
		while (rs.next()) {
			nom = rs.getString("nomcli");
			prenom = rs.getString("prenomcli");
			adresse = rs.getString("adressecli");
			cp = rs.getString("codepostal");
			ville = rs.getString("villecli");
			mdp = rs.getString("mdpcli");
			client = new Client(nom, prenom, adresse, cp, ville, mdp);
			lesUtilisateurs.add(client);
			ajouterCommandes(client, lesMagasins, lesLivres);
		}
		st=laConnexion.createStatement();
		rs=st.executeQuery("SELECT nomven, prenomven, mdpven, nommag, villemag FROM VENDEUR "+
							"NATURAL JOIN MAGASIN");
		Vendeur vendeur;
		Magasin magasin;
		while (rs.next()) {
			nom = rs.getString("nomven");
			prenom = rs.getString("prenomven");
			mdp = rs.getString("mdpven");
			magasin = lesMagasins.get(lesMagasins.indexOf(new Magasin(rs.getString("nommag"), rs.getString("villemag"))));
			vendeur = new Vendeur(nom, prenom, mdp, magasin);
			magasin.addVendeur(vendeur);
			lesUtilisateurs.add(vendeur);
		}
		st=laConnexion.createStatement();
		rs = st.executeQuery("SELECT nomadmin, prenomadmin, mdpadmin FROM ADMIN");
		while (rs.next()) {
			nom = rs.getString("nomadmin");
			prenom = rs.getString("prenomadmin");
			mdp = rs.getString("mdpadmin");
			lesUtilisateurs.add(new Administrateur(nom, prenom, mdp));
		}
		return lesUtilisateurs;
	}

	/**
     * Méthode permettant de récupérer toutes les commandes d'un client donné depuis la base de données,
     * en plus d'associer ses commandes avec le magasin et les livres respectifs
     * @param client Le client qui a réalisé cette commande
     * @param lesMagasins La liste des magasins
     * @param lesLivres la liste des Livres
     * @throws SQLException
     */
    private void ajouterCommandes(Client client, List<Magasin> lesMagasins, List<Livre> lesLivres) throws SQLException{
        st=laConnexion.createStatement();
        PreparedStatement ps=laConnexion.prepareStatement("SELECT numcom, date DATE_FORMAT(datecom, '%d/%m/%Y'), enligne, livraison, nommag, villemag " +
                                                        "FROM CLIENT NATURAL JOIN COMMANDE NATURAL JOIN MAGASIN "+
                                                        "WHERE nomcli=? AND prenomcli=? AND adressecli=? AND codepostal=? AND villecli=?");
        ps.setString(1, client.getNom());
        ps.setString(2, client.getPrenom());
        ps.setString(3, client.getAdresse());
        ps.setString(4, client.getCodePostal());
        ps.setString(5, client.getVille());
        ResultSet rs=ps.executeQuery();
        int num;
        String date;
        boolean enLigne;
        boolean domicile;
        Magasin magasin;
        Commande commande;
        while(rs.next()) {
            num = rs.getInt("numcom");
            date = rs.getString("date");
            enLigne = rs.getString("enligne").charAt(0) == 'O';
            domicile = rs.getString("livraison").charAt(0) == 'C';
            magasin = lesMagasins.get(lesMagasins.indexOf(new Magasin(rs.getString("nommag"), rs.getString("villemag"))));
            commande = new Commande(num, date, enLigne, domicile, client, magasin);
            magasin.addCommande(commande);
            client.ajouterCommande(commande);
            ajouterLignesCommandes(commande, lesLivres);
        }
    }

    /**
     * Méthode permettant de récupérer toutes les lignes d'une commande donnée, et de les associer avec les livres correspondants
     * @param commande La commande
     * @param lesLivres Les livres
     * @throws SQLException
     */
    private void ajouterLignesCommandes(Commande commande, List<Livre> lesLivres) throws SQLException{
        PreparedStatement ps=laConnexion.prepareStatement("SELECT numlig, qte, prixvente, isbn FROM DETAILCOMMANDE "+
                                                            "WHERE numcom=? ORDER BY numlig");
        ps.setInt(1, commande.getNum());
        ResultSet rs=ps.executeQuery();
        int qte;
        double prix;
        int isbn;
        Livre livre;
        while (rs.next()) {
            qte = rs.getInt("qte");
            prix = rs.getDouble("prixvente");
            isbn = Integer.parseInt(rs.getString("isbn"));
            livre = lesLivres.get(lesLivres.indexOf(new Livre(isbn, null, prix, null, null)));
            commande.addLigne(livre, qte, prix);
            livre.addCommande(commande);
        }
    }

    /**
     * Méthode permettant de récupérer le numéro de commande le plus haut de la base de donnée
     * @return Le numéro de commande max
     * @throws SQLException
     */
    public int maxNumeroCommande() throws SQLException{
        st=laConnexion.createStatement();
        ResultSet rs=st.executeQuery("SELECT max MAX(numcom) FROM COMMANDE");
        rs.next();
        return rs.getInt("max");
    }

    /**
     * Méthode permettant de modifier le mode de livraison d'un commande dans la base de donnée
     * @param commande La commande
     * @throws SQLException
     */
    public void modifierLivraisonCommande(Commande commande) throws SQLException{
        PreparedStatement ps=laConnexion.prepareStatement("UPDATE COMMANDE SET livraison=? WHERE numcom=?");
        String livraison;
        if (commande.livreDomicile()) {livraison="C";}
        else {livraison="M";}
        ps.setString(1, livraison);
        ps.setInt(2, commande.getNum());
        ps.executeUpdate();
    }

    /**
     * Méthode permettant d'insérer une nouvelle commande dans la base de donnée
     * @param commande La commande
     * @throws SQLException
     */
    public void insererCommande(Commande commande) throws SQLException{
        PreparedStatement ps=laConnexion.prepareStatement("SELECT idmag FROM MAGASIN WHERE nommag=? AND villemag=?");
        ps.setString(1, commande.getMagasin().getNom());
        ps.setString(2, commande.getMagasin().getVille());
        ResultSet rs=ps.executeQuery();
        rs.next();
        int idMagasin = rs.getInt("idmag");

        ps=laConnexion.prepareStatement("SELECT idcli FROM CLIENT WHERE nomcli=? AND prenomcli=? AND adressecli=? AND codepostal=? AND villecli=?");
        ps.setString(1, commande.getClient().getNom());
        ps.setString(2, commande.getClient().getPrenom());
        ps.setString(3, commande.getClient().getAdresse());
        ps.setString(4, commande.getClient().getCodePostal());
        ps.setString(5, commande.getClient().getVille());
        rs=ps.executeQuery();
        rs.next();
        int idClient = rs.getInt("idcli");

        ps=laConnexion.prepareStatement("INSERT INTO COMMANDE VALUES(?, str_to_date(?, '%d/%m/%Y'), ?, ?, ?, ?)");
        ps.setInt(1, commande.getNum());
        ps.setString(2, commande.getDate());
        String enLigne;
        if (commande.estEnLigne()) {enLigne="O";}
        else {enLigne="N";}
        ps.setString(3, enLigne);
        String livraison;
        if (commande.livreDomicile()) {livraison="C";}
        else {livraison="M";}
        ps.setString(4, livraison);
        ps.setInt(5, idClient);
        ps.setInt(6, idMagasin);
        ps.executeUpdate();

        for (int i=0; i<commande.getDetailsCommande().size(); ++i) {
            ps=laConnexion.prepareStatement("INSERT INTO DETAILCOMMANDE VALUES(?, ?, ?, ?, ?)");
            ps.setInt(1, commande.getNum());
            ps.setInt(2, i+1);
            ps.setInt(3, commande.getDetailsCommande().get(i).getQte());
            ps.setDouble(4, commande.getDetailsCommande().get(i).getPrixVente());
            ps.setString(5, String.valueOf(commande.getDetailsCommande().get(i).getLivre().getISBN()));
            ps.executeUpdate();
        }
    }

    public void updateStock(Magasin magasin, Livre livre) throws SQLException{
        PreparedStatement ps=laConnexion.prepareStatement("SELECT idmag FROM MAGASIN WHERE nommag=? AND villemag=?");
        ps.setString(1, magasin.getNom());
        ps.setString(2, magasin.getVille());
        ResultSet rs=ps.executeQuery();
        rs.next();
        int idMagasin = rs.getInt("idmag");

        ps=laConnexion.prepareStatement("SELECT nb COUNT(idmag) FROM POSSEDER WHERE idmag=? AND isbn=?");
        ps.setInt(1, idMagasin);
        ps.setString(2, String.valueOf(livre.getISBN()));
        rs=ps.executeQuery();
        rs.next();
        if (rs.getInt("nb")==0) {
            ps=laConnexion.prepareStatement("INSERT INTO POSSEDER VALUES (?, ?, ?)");
            ps.setInt(1, idMagasin);
            ps.setString(2, String.valueOf(livre.getISBN()));
            ps.setInt(3, magasin.getQteLivre(livre));
            ps.executeUpdate();
        }
        else {
            ps=laConnexion.prepareStatement("UPDATE POSSEDER SET qte=? WHERE idmag=? AND isbn=?");
            ps.setInt(1, magasin.getQteLivre(livre));
            ps.setInt(2, idMagasin);
            ps.setString(3, String.valueOf(livre.getISBN()));
        }
    }
}
