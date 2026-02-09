
package persistencia.implementar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.CategoriaGama;
import modelo.Celular;
import modelo.Marca;
import persistencia.CelularDAO;
import persistencia.ConexionDB;



public class CelularDAOimpl implements CelularDAO{
    
    ConexionDB c = new ConexionDB();

    @Override
    public void registrar(Celular cel) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into celulares(modelo, sistema_os, gama, stock, precio, id_marca) values (?,?,?,?,?,?)");
            ps.setString(1, cel.getModelo());
            ps.setString(2, cel.getSistemaOS());
            ps.setString(3, cel.getGama().name());
            ps.setInt(4, cel.getStock());
            ps.setDouble(5, cel.getPrecio());
            ps.setInt(6, cel.getId_marca().getId());
            ps.executeUpdate();
            System.out.println("REGISTRO EXITOSO!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Celular> listar() {
            ArrayList<Celular> celulares = new ArrayList<>();
        try (Connection con = c.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select c.id, c.modelo, c.sistema_os, c.gama, c.stock, c.precio, c.id_marca, m.nombre from celulares c inner join marcas m on c.id_marca = m.id");
            while (rs.next()) {
                Marca marca = new Marca(rs.getInt(7), rs.getString(8));
                celulares.add(new Celular(rs.getInt(1), rs.getString(2), rs.getString(3), CategoriaGama.valueOf(rs.getString(4)), rs.getInt(5), rs.getDouble(6), marca));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return celulares;
    }


    @Override
    public Celular buscar(int id) {
         Celular cel = new Celular(0, "", "", CategoriaGama.BAJA, 0, 0.0, new Marca(0, ""));
        try (Connection con = c.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select c.id, c.modelo, c.sistema_os, c.gama, c.stock, c.precio, c.id_marca, m.nombre from celulares c inner join marcas m on c.id_marca = m.id where c.id=" + id);
            while (rs.next()) {
                Marca marca = new Marca(rs.getInt(7), rs.getString(8));
                cel.setId(rs.getInt(1));
                cel.setModelo(rs.getString(2));
                cel.setSistemaOS(rs.getString(3));
                cel.setGama(CategoriaGama.valueOf(rs.getString(4)));
                cel.setStock(rs.getInt(5));
                cel.setPrecio(rs.getDouble(6));
                cel.setId_marca(marca);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cel;
    }

    @Override
    public void eliminar(int id) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("delete from celulares where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("ELIMINACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(Celular cel, int id) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("update celulares set modelo=?, sistema_os=?, gama=?, stock=?, precio=?, id_marca=? where id=?");
            ps.setString(1, cel.getModelo());
            ps.setString(2, cel.getSistemaOS());
            ps.setString(3, cel.getGama().name());
            ps.setInt(4, cel.getStock());
            ps.setDouble(5, cel.getPrecio());
            ps.setInt(6, cel.getId_marca().getId());
            ps.setInt(7, id);
            ps.executeUpdate();
            System.out.println("ACTUALIZACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean descontarStock(int idCelular, int cantidad) {
     try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("update celulares set stock = stock - ? where id = ? and stock >= ?");
            ps.setInt(1, cantidad);
            ps.setInt(2, idCelular);
            ps.setInt(3, cantidad);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("STOCK DESCONTADO!");
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    } 
}
