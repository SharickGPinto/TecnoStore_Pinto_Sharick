
package controlador.persistencia.implementar;

import controlador.persistencia.ConexionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.CategoriaGama;
import modelo.Celular;
import modelo.Marca;
import controlador.persistencia.AlertaStockDAO;


public class AlertaStockDAOimpl implements AlertaStockDAO{
    
     ConexionDB c = new ConexionDB();

    @Override
    public ArrayList<Celular> AlertaStock() {
         ArrayList<Celular> celulares = new ArrayList<>();
        try (Connection con = c.conectar()) {                          // Hacemos un INNER JOIN para no mostrar solo el ID de la marca, es para que muestre el nombre de la marca y el usuario se pueda guiar
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select c.id, c.modelo, c.sistema_os, c.gama, c.stock, c.precio, c.id_marca, m.nombre from celulares c inner join marcas m on c.id_marca = m.id WEHERE STOCK <=5");
            while (rs.next()) {
                Marca marca = new Marca(rs.getInt(7), rs.getString(8));
                celulares.add(new Celular(rs.getInt(1), rs.getString(2), rs.getString(3), CategoriaGama.valueOf(rs.getString(4)), rs.getInt(5), rs.getDouble(6), marca)); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return celulares;
    }
    
}
