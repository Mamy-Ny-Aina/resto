package resto;

import java.util.Date;
import connexion.*;
import java.sql.*;
import java.util.*;

public class Produit {
    int idproduit;
    String nomproduit;
    double prix;
    double prixIntermediaire;
    double prixRevient;

    public Produit(String nomproduit, double prix, double prixIntermediaire, double prixRevient) {
        this.nomproduit = nomproduit;
        this.prix = prix;
        this.prixIntermediaire = prixIntermediaire;
        this.prixRevient = prixRevient;
    }
    public Produit(){}

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPrixIntermediaire() {
        return prixIntermediaire;
    }

    public void setPrixIntermediaire(double prixIntermediaire) {
        this.prixIntermediaire = prixIntermediaire;
    }

    public double getPrixRevient() {
        return prixRevient;
    }

    public void setPrixRevient(double prixRevient) {
        this.prixRevient = prixRevient;
    }

     public void insertProduit(Produit produit) {
        try {
            ConnexionPostgre connexionPostgre = new ConnexionPostgre();
            Connection connection = connexionPostgre.getConnectionpostgre();
            String query = "INSERT INTO produit (nomproduit, prix, prixIntermediaire, prixRevient) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, produit.getNomproduit());
                preparedStatement.setDouble(2, produit.getPrix());
                preparedStatement.setDouble(3, produit.getPrixIntermediaire());
                preparedStatement.setDouble(4, produit.getPrixRevient());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector selectProduit() throws Exception{
            Vector rep=new Vector();
            ConnexionPostgre connexionPostgre = new ConnexionPostgre();
            Connection connection = connexionPostgre.getConnectionpostgre();
            Statement stmt = connection.createStatement();
            String requette = "select * from produit";
            ResultSet res = stmt.executeQuery(requette);
            while (res.next()) {
                Produit p=new Produit();
                p.setIdproduit(res.getInt(1));
                p.setNomproduit(res.getString(2));
                p.setPrix(res.getDouble(3));
                p.setPrixIntermediaire(res.getDouble(4));
                p.setPrixRevient(res.getDouble(5));
                rep.add(p);
            }

            res.close();
            stmt.close();
            return rep;
    }

    public Produit produit_base(int idpage)throws Exception{
        Produit leproduit=new Produit();
        Vector allproduct=this.selectProduit();
        leproduit= (Produit) allproduct.elementAt(idpage);
        return leproduit;
    }

    public Vector nom_commande(Vector v) throws Exception {
        Vector nom=new Vector();
        ConnexionPostgre connexionPostgre = new ConnexionPostgre();
        Connection connection = connexionPostgre.getConnectionpostgre();
        Statement stmt = connection.createStatement();
        for (int i=0;i<v.size() ;i++ ) {
            String requette = "select nomproduit from produit where idproduit="+((int) v.elementAt(i));
            ResultSet res = stmt.executeQuery(requette);
            while (res.next()) {
                String idd=res.getString(1);
                nom.add(idd);
            }
            res.close();   
        }

        
        stmt.close();
        return nom;
    }

}