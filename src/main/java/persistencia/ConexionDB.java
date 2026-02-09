
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {
    
//   private String ip="localhost";
//    private String database="campusan";
//    private String user="root";
//    private String password="123";
    
    public Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tecnostore_db", "root", "sharick.pinto");
            System.out.println("Conexion exitosa");
//            c=DriverManager.getConnection("jdbc:mysql://"+ip+"/"+database,user,password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
}

  

