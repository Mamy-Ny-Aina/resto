package olona;

import connexion.*;
import resto.*;
import java.util.Date;
import java.util.*;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Intermediaire {
    int idintermediaire;
    int idfacture;
    double plafond;
    double seuil;
    double vola;
    String nom;
    String mdp;

    public Intermediaire(double plafond, double seuil, double vola,String name,String mdp) {
        this.plafond = plafond;
        this.seuil = seuil;
        this.vola = vola;
        this.nom=name;
        this.mdp=mdp;
    }

    public Intermediaire(){}

    public int getIdintermediaire() {
        return idintermediaire;
    }

    public void setIdintermediaire(int idintermediaire) {
        this.idintermediaire = idintermediaire;
    }

    public int getIdfacture() {
        return idfacture;
    }

    public void setIdfacture(int idfacture) {
        this.idfacture = idfacture;
    }

    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public String getmdp() {
        return mdp;
    }

    public void setmdp(String mdp) {
        this.mdp = mdp;
    }

    public double getPlafond() {
        return plafond;
    }

    public void setPlafond(double plafond) {
        this.plafond = plafond;
    }

    public double getSeuil() {
        return seuil;
    }

    public void setSeuil(double seuil) {
        this.seuil = seuil;
    }

    public double getVola() {
        return vola;
    }

    public void setVola(double vola) {
        this.vola = vola;
    }

     public void insertIntermediaire(Intermediaire intermediaire) {
        try {
            ConnexionPostgre connexionPostgre = new ConnexionPostgre();
            Connection connection = connexionPostgre.getConnectionpostgre();
            String query = "INSERT INTO intermediaire (plafond, seuil, vola,nom,mdp) VALUES (?, ?, ?, ?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDouble(1, intermediaire.getPlafond());
                preparedStatement.setDouble(2, intermediaire.getSeuil());
                preparedStatement.setDouble(3, intermediaire.getVola());
                preparedStatement.setString(4, intermediaire.getnom());
                preparedStatement.setString(5, intermediaire.getmdp());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double vola_intermediaire(int intermediaryId, Date startDate, Date endDate) throws Exception {
        double intermediaryRevenue = 0;
        try {
            ConnexionPostgre connexionPostgre = new ConnexionPostgre();
            Connection connection = connexionPostgre.getConnectionpostgre();

            String query = "SELECT " +
               "SUM(f.quantite * (p.prix - p.prixIntermediaire)) AS argent_obtenu_entre_dates " +
               "FROM facture f " +
               "JOIN produit p ON f.idproduit = p.idproduit " +
               "WHERE f.dateFacture >= ? AND f.dateFacture <= ? AND f.idintermediaire = ? AND f.statut=0";

            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
                preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));
                preparedStatement.setInt(3, intermediaryId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    intermediaryRevenue = resultSet.getDouble("argent_obtenu_entre_dates");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return intermediaryRevenue;
    }

    public boolean areSameMonthAndYear(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.setTime(date1);
        cal2.setTime(date2);

        int year1 = cal1.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH);

        int year2 = cal2.get(Calendar.YEAR);
        int month2 = cal2.get(Calendar.MONTH);

        return year1 == year2 && month1 == month2;
    }

    public Vector facture_lie_intermediaire(int intermediaryId, Date startDate, Date endDate) throws Exception {
        Vector idfact = new Vector();
        try {
            ConnexionPostgre connexionPostgre = new ConnexionPostgre();
            Connection connection = connexionPostgre.getConnectionpostgre();

            String query = "SELECT " +
                           "idfacture " +
                           "FROM facture " +
                           "where idintermediaire=? " +
                           "and dateFacture >= ? AND dateFacture <= ? "+
                           "group by idFacture";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, intermediaryId);
                preparedStatement.setDate(2, new java.sql.Date(startDate.getTime()));
                preparedStatement.setDate(3, new java.sql.Date(endDate.getTime()));

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                  int  idfacture = resultSet.getInt("idfacture");
                  idfact.add(idfacture);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idfact;
    }

    public void updateFactureStatut(int idFacture, int newStatut) throws SQLException {
    String updateQuery = "UPDATE facture SET statut = ? WHERE idfacture = ?";

    ConnexionPostgre connexionPostgre = new ConnexionPostgre();
    Connection connection = connexionPostgre.getConnectionpostgre();

   PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setInt(1, newStatut);
        preparedStatement.setInt(2, idFacture);

        int rowsUpdated = preparedStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("La facture a été mise à jour avec succès.");
        } else {
            System.out.println("Aucune facture mise à jour. Vérifiez l'ID de la facture.");
        }
}


public void ajouterVola(int idIntermediaire, double montant) {
    try {
        ConnexionPostgre connexionPostgre = new ConnexionPostgre();
        Connection connection = connexionPostgre.getConnectionpostgre();

            String updateQuery = "UPDATE intermediaire SET vola = vola + ? WHERE idintermediaire = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setDouble(1, montant);
                preparedStatement.setInt(2, idIntermediaire);

                preparedStatement.executeUpdate();
                System.out.println("Vola ajoute avec succes.");
            }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public double calculerArgentTotal(int idIntermediaire, Date startDate, Date endDate) throws SQLException {
    double argentTotal = 0;

    ConnexionPostgre connexionPostgre = new ConnexionPostgre();
    Connection connection = connexionPostgre.getConnectionpostgre();

    String query = "SELECT " +
                   "SUM(p.prix * f.quantite) AS argent_total " +
                   "FROM facture f " +
                   "JOIN produit p ON f.idproduit = p.idproduit " +
                   "WHERE f.idintermediaire = ? AND f.dateFacture >= ? AND f.dateFacture <= ? " +
                   "GROUP BY f.idintermediaire";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idIntermediaire);
        preparedStatement.setDate(2, new java.sql.Date(startDate.getTime()));
        preparedStatement.setDate(3, new java.sql.Date(endDate.getTime()));

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            argentTotal = resultSet.getDouble("argent_total");
        }

    return argentTotal;
}


public Intermediaire getIntermediaireById(int intermediaireId) {
    Intermediaire intermediaire = null;

    try {
        ConnexionPostgre connexionPostgre = new ConnexionPostgre();
        Connection connection = connexionPostgre.getConnectionpostgre();

        String query = "SELECT * FROM intermediaire WHERE idintermediaire = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, intermediaireId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                intermediaire = new Intermediaire();
                intermediaire.setIdintermediaire(resultSet.getInt("idintermediaire"));
                intermediaire.setnom(resultSet.getString("nom"));
                intermediaire.setPlafond(resultSet.getDouble("plafond"));
                intermediaire.setSeuil(resultSet.getDouble("seuil"));
                intermediaire.setVola(resultSet.getDouble("vola"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return intermediaire;
}

public Vector getallIntermediaire() {
    Vector intermediaire = new Vector();

    try {
        ConnexionPostgre connexionPostgre = new ConnexionPostgre();
        Connection connection = connexionPostgre.getConnectionpostgre();

        String query = "SELECT * FROM intermediaire";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Intermediaire inter = new Intermediaire();
                inter.setIdintermediaire(resultSet.getInt("idintermediaire"));
                inter.setnom(resultSet.getString("nom"));
                inter.setPlafond(resultSet.getDouble("plafond"));
                inter.setSeuil(resultSet.getDouble("seuil"));
                inter.setVola(resultSet.getDouble("vola"));

                intermediaire.add(inter);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return intermediaire;
}

    public void insert_vola_intermediaire(int idintermediaire,Date debut,Date fin) throws Exception {
        if (areSameMonthAndYear(debut,fin)) {
            Intermediaire inter=getIntermediaireById(idintermediaire);
            double seuilint=inter.getSeuil();
            double plafondint=inter.getPlafond();
            System.out.println("MITOVYY MOIS SY ANNE");
            double sommefacture=this.calculerArgentTotal(idintermediaire,debut,fin);
            System.out.println("sommefacture :"+sommefacture);

            if (sommefacture>=seuilint && sommefacture<=plafondint) {
            System.out.println("sommefacture :"+sommefacture);
                Double soldeactuel=this.vola_intermediaire(idintermediaire,debut,fin);
            System.out.println("soldeactuel :"+soldeactuel);

                Vector idfacture=this.facture_lie_intermediaire(idintermediaire,debut,fin);
                this.ajouterVola(idintermediaire,soldeactuel);
                for (int i=0;i<idfacture.size() ;i++ ) {
            System.out.println("id facture lie :"+idfacture.elementAt(i));

                    int id= (int) idfacture.elementAt(i);
                    this.updateFactureStatut(id,1);
                    System.out.println("Vola intermeddiaire ajoute avec succe.");

                }
            }
        }
        
    }

    public Vector<Vector<Object>> getIntermediaireDetails(int idIntermediaire, Date startDate, Date endDate) {
    Vector<Vector<Object>> result = new Vector<>();

    try {
        ConnexionPostgre connexionPostgre = new ConnexionPostgre();
        Connection connection = connexionPostgre.getConnectionpostgre();

        String query = "SELECT " +
                "f.idfacture, " +
                "f.datefacture, " +
                "p.nomproduit, " +
                "f.quantite, " +
                "f.statut, " +
                "((p.prix - p.prixIntermediaire) * f.quantite) AS benefice " +
                "FROM facture f " +
                "JOIN produit p ON f.idproduit = p.idproduit " +
                "WHERE f.idintermediaire = ? AND f.datefacture BETWEEN ? AND ? " +
                "ORDER BY f.idfacture";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idIntermediaire);
            preparedStatement.setDate(2, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(3, new java.sql.Date(endDate.getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("idfacture"));
                row.add(resultSet.getDate("datefacture"));
                row.add(resultSet.getString("nomproduit"));
                row.add(resultSet.getInt("quantite"));
                row.add(resultSet.getInt("statut"));
                row.add(resultSet.getDouble("benefice"));

                result.add(row);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}


}
