package connexion;

import java.sql.*;

public class ConnexionPostgre {
    public Connection getConnectionpostgre() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "Carasco20");
                System.out.println("conection POSTGRESS effectuer");
            return c;
        } 
        
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }

        return c;
    }
}