
package persistencia.implementar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Cliente;
import persistencia.ClienteDAO;
import persistencia.ConexionDB;


public class ClienteDAOImple implements ClienteDAO{
    
    ConexionDB c = new ConexionDB();

    @Override 
    public void registrar(Cliente cl) {
        try (Connection con = c.conectar()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO clientes(nombre, identificacion, correo, telefono) VALUES (?,?,?,?)");
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getIdentificacion());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getTelefono());
            ps.executeUpdate();
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Cliente> listar() {
      ArrayList<Cliente> clientes = new ArrayList<>();
        try (Connection con = c.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM clientes")) {
            while (rs.next()) {
                clientes.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clientes;
    }
    @Override
    public Cliente buscar(String identificacion) {
        Cliente cl = new Cliente(0, "", "", "", "");
         try (Connection con = c.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from clientes where identificacion='" + identificacion + "'");
            while (rs.next()) {
                cl.setId(rs.getInt(1));
                cl.setNombre(rs.getString(2));
                cl.setIdentificacion(rs.getString(3));
                cl.setCorreo(rs.getString(4));
                cl.setTelefono(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cl;
    }

    @Override
    public void eliminar(String identificacion) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("delete from clientes where identificacion=?");
            ps.setString(1, identificacion);
            ps.executeUpdate();
            System.out.println("ELIMINACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void actualizar(Cliente cl, String identificacion) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("update clientes set nombre=?, identificacion=?, correo=?, telefono=? where identificacion=?");
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getIdentificacion());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getTelefono());
            ps.setString(5, identificacion);
            ps.executeUpdate();
            System.out.println("ACTUALIZACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
}
