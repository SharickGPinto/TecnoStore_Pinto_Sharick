package controlador.persistencia.implementar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Marca;
import controlador.persistencia.ConexionDB;
import controlador.persistencia.MarcaDAO;

public class MarcaDAOImpl implements MarcaDAO {

    ConexionDB c = new ConexionDB();

    @Override
    //metodo para poder registrar una nueva marca
    public void registrar(Marca m) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into marcas(nombre) values (?)");
            ps.setString(1, m.getNombre());
            ps.executeUpdate();
            System.out.println("REGISTRO EXITOSO!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //metodo para poder actualizar la marca 
    @Override
    public void actualizar(Marca m, int id) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("update marcas set nombre=? where id=?");
            ps.setString(1, m.getNombre());
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("ACTUALIZACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
  //metodo para poder eliminar una marca 
    @Override
    public void eliminar(int id) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("delete from marcas where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("ELIMINACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //metodo para poder ver las marcas existentes  
    @Override
    public ArrayList<Marca> listar() {
         // lista vacia donde se guardaran las marcas
        ArrayList<Marca> marcas = new ArrayList<>();
        try (Connection con = c.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from marcas");
            while (rs.next()) {
                marcas.add(new Marca(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return marcas;
    }
    //metodo para poder buscar la marca mediante le id
    @Override
    public Marca buscar(int id) {
        Marca m = new Marca(0, "");
        try (Connection con = c.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from marcas where id=" + id);
            while (rs.next()) {
                m.setId(rs.getInt(1));
                m.setNombre(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return m;
    }
}


