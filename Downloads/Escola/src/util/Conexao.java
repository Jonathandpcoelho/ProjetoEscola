
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    public static Connection conectar(){
        Connection con = null;
        String url = "jdbc:postgresql://localhost:1997/escola";
        String user = "postgres";
        String password = "442266";
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Erro ao conectar con o banco");
        }
        return con;                
    }
}
