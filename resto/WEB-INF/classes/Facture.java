package resto;

import connexion.*;
import java.util.Date;
import java.sql.*;
import java.util.*;


public class Facture {
    int idfacture;
    int idproduit;
    Date dateFacture;
    int idintermediaire;
    int quantite;

    public Facture(int id, int idproduit,int idintermediaire,int quantite,Date dateFacture) {
        this.idfacture=id;
        this.idproduit = idproduit;
        this.dateFacture = dateFacture;
        this.idintermediaire=idintermediaire;
        this.quantite=quantite;
    }

    public Facture(){}

    public int getIdfacture() {
        return idfacture;
    }

    public void setIdfacture(int idfacture) {
        this.idfacture = idfacture;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public int getquantite(){
        return this.quantite;
    } 

    public void setquantite(int id){
        this.quantite=id;
    } 

    public int getidintermediaire() {
        return this.idintermediaire;
    }

    public void setidintermediaire(int intermediaire) {
        this.idintermediaire = intermediaire;
    }

    public void insertFacture(Facture facture) {
        try {
            ConnexionPostgre connexionPostgre = new ConnexionPostgre();
            Connection connection = connexionPostgre.getConnectionpostgre();
            String query = "INSERT INTO facture (idfacture,idproduit,idintermediaire,dateFacture,quantite) VALUES (?,?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, facture.getIdfacture());
                preparedStatement.setInt(2, facture.getIdproduit());
                preparedStatement.setInt(3, facture.getidintermediaire());
                preparedStatement.setDate(4, new java.sql.Date(facture.getDateFacture().getTime()));
                preparedStatement.setInt(5, facture.getquantite());

                preparedStatement.executeUpdate(); 
                System.out.println("out.print('mandeha')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int last_facture_id() throws Exception {
        int id=0;
        ConnexionPostgre connexionPostgre = new ConnexionPostgre();
        Connection connection = connexionPostgre.getConnectionpostgre();
        Statement stmt = connection.createStatement();
        String requette = "select max(idfacture) from facture";
        ResultSet res = stmt.executeQuery(requette);
        while (res.next()) {
           id=res.getInt(1);
        }

        res.close();
        stmt.close();
        return id;
    }

    public Date datezao(){
        Date d=new Date(System.currentTimeMillis());
        return d;
    }

    public Vector id_commande(int idfacture) throws Exception {
        Vector id=new Vector();
        ConnexionPostgre connexionPostgre = new ConnexionPostgre();
        Connection connection = connexionPostgre.getConnectionpostgre();
        Statement stmt = connection.createStatement();
        String requette = "select idproduit from facture where idfacture="+idfacture;
        ResultSet res = stmt.executeQuery(requette);
        while (res.next()) {
          int idd=res.getInt(1);
          id.add(idd);
        }

        res.close();
        stmt.close();
        return id;
    }

    public Vector selectFacture(int idfacture) throws Exception{
            Vector rep=new Vector();
            ConnexionPostgre connexionPostgre = new ConnexionPostgre();
            Connection connection = connexionPostgre.getConnectionpostgre();
            Statement stmt = connection.createStatement();
            String requette = "select * from facture where idfacture="+idfacture;
            ResultSet res = stmt.executeQuery(requette);
            while (res.next()) {
                Facture f=new Facture();
                f.setIdfacture(res.getInt(1));
                f.setIdproduit(res.getInt(2));
                f.setidintermediaire(res.getInt(3));
                f.setDateFacture(res.getDate(4));
                f.setquantite(res.getInt(5));
                rep.add(f);
            }

            res.close();
            stmt.close();
            return rep;
    }

     public double vola_total_facture(int idfacture) {
        double totalFacture = 0;

        try {
            ConnexionPostgre connexionPostgre = new ConnexionPostgre();
            Connection connection = connexionPostgre.getConnectionpostgre();

            String query = "SELECT SUM(p.prix * f.quantite) AS total_facture " +
                           "FROM facture f " +
                           "JOIN produit p ON f.idproduit = p.idproduit " +
                           "WHERE f.idfacture = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idfacture);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    totalFacture = resultSet.getDouble("total_facture");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalFacture;
    }

    public void moin_quantite(int idfacture, int idproduit) {
        try {
            ConnexionPostgre connexionPostgre = new ConnexionPostgre();
            Connection connection = connexionPostgre.getConnectionpostgre();

            String query = "UPDATE facture SET quantite = quantite - 1 WHERE idfacture = ? AND idproduit = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idfacture);
                preparedStatement.setInt(2, idproduit);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void plus_quantite(int idfacture, int idproduit) {
        try {
            ConnexionPostgre connexionPostgre = new ConnexionPostgre();
            Connection connection = connexionPostgre.getConnectionpostgre();

            String query = "UPDATE facture SET quantite = quantite + 1 WHERE idfacture = ? AND idproduit = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idfacture);
                preparedStatement.setInt(2, idproduit);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double benefice(Date startDate, Date endDate) throws Exception {
        double totalProfit = 0;
        try {
            ConnexionPostgre connexionPostgre = new ConnexionPostgre();
            Connection connection = connexionPostgre.getConnectionpostgre();

            String query = "SELECT " +
                           "SUM(f.quantite*(p.prix - p.prixRevient) AS benefice" +
                           "FROM" +
                           "facture f" +
                           "JOIN produit p ON f.idproduit = p.idproduit " +
                           "WHERE f.dateFacture >= ? AND f.dateFacture <= ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
                preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    totalProfit = resultSet.getDouble("benefice");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalProfit;
    }

    public Vector statistique_detaille(Date startDate, Date endDate) throws Exception {
        Vector grand = new Vector();
        Vector produit = new Vector();
        Vector quantite = new Vector();
        Vector prixv = new Vector();
        Vector prixr = new Vector();
        Vector prixi = new Vector();
        try {
            ConnexionPostgre connexionPostgre = new ConnexionPostgre();
            Connection connection = connexionPostgre.getConnectionpostgre();

            String query = "SELECT " +
               "p.nomproduit AS produit, " +
               "SUM(f.quantite) AS quantite_vendue, " +
               "SUM(f.quantite * p.prix) AS prix_vendu, " +
               "SUM(f.quantite * p.prixRevient) AS prix_revient, " +
               "SUM(f.quantite * (p.prix - p.prixIntermediaire)) AS prix_intermediaire " +
               "FROM " +
               "facture f " +
               "JOIN produit p ON f.idproduit = p.idproduit " +
               "WHERE " +
               "f.dateFacture >= ? AND f.dateFacture <= ? " +
               "GROUP BY " +
               "p.nomproduit";

            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
                preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String nomp = resultSet.getString("produit");
                    produit.add(nomp);
                    int q = resultSet.getInt("quantite_vendue");
                    quantite.add(q);
                    double pv=resultSet.getDouble("prix_vendu");
                    prixv.add(pv);
                    double pr=resultSet.getDouble("prix_revient");
                    prixr.add(pr);
                    double pi=resultSet.getDouble("prix_intermediaire");
                    prixi.add(pi);
                }
                grand.add(produit);
                grand.add(quantite);
                grand.add(prixv);
                grand.add(prixr);
                grand.add(prixi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grand;
    }

 public Vector<Vector<Object>> calculerBeneficeBrutParMois() throws SQLException {
        ConnexionPostgre connexionPostgre = new ConnexionPostgre();
        Connection connection = connexionPostgre.getConnectionpostgre();
        
        Vector<Vector<Object>> resultats = new Vector<>();

        String query = "SELECT " +
                "EXTRACT(MONTH FROM f.dateFacture) AS mois, " +
                "EXTRACT(YEAR FROM f.dateFacture) AS annee, " +
                "SUM((p.prix - p.prixRevient) * f.quantite) AS benefice_brut " +
                "FROM " +
                "facture f " +
                "JOIN produit p ON f.idproduit = p.idproduit " +
                "WHERE " +
                "f.statut = 0 " +
                "GROUP BY " +
                "EXTRACT(MONTH FROM f.dateFacture), " +
                "EXTRACT(YEAR FROM f.dateFacture) " +
                "ORDER BY " +
                "annee, mois";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int mois = resultSet.getInt("mois");
                int annee = resultSet.getInt("annee");
                double beneficeBrut = resultSet.getDouble("benefice_brut");

                Vector<Object> ligne = new Vector<>();
                ligne.add(mois);
                ligne.add(annee);
                ligne.add(beneficeBrut);

                resultats.add(ligne);
            }
        }

        return resultats;
    }





}
