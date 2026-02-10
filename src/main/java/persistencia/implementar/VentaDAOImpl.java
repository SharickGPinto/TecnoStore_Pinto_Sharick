package persistencia.implementar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Venta;
import persistencia.ConexionDB;
import persistencia.VentaDAO;

public class VentaDAOImpl implements VentaDAO {

    ConexionDB c = new ConexionDB();

    @Override
    public void registrar(Venta v) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into ventas(id_cliente, fecha, total_sin_iva, total_con_iva) values (?,?,?,?)");
            ps.setInt(1, v.getCliente().getId());
            ps.setString(2, v.getFecha());
            ps.setDouble(3, v.getTotalSinIva());
            ps.setDouble(4, v.getTotalConIva());
            ps.executeUpdate();
            System.out.println("REGISTRO EXITOSO!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Venta> listar() {
        ArrayList<Venta> ventas = new ArrayList<>();
        try (Connection con = c.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select v.id, v.fecha, v.total_sin_iva, v.total_con_iva, v.id_cliente, c.nombre, c.identificacion, c.correo, c.telefono from ventas v inner join clientes c on v.id_cliente = c.id");
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
                ventas.add(new Venta(rs.getInt(1), cliente, rs.getString(2), rs.getDouble(3), rs.getDouble(4)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ventas;
    }

    @Override
    public Venta buscar(int id) {
        Venta v = new Venta(0, new Cliente(0, "", "", "", ""), "", 0.0, 0.0);
        try (Connection con = c.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select v.id, v.fecha, v.total_sin_iva, v.total_con_iva, v.id_cliente, c.nombre, c.identificacion, c.correo, c.telefono from ventas v inner join clientes c on v.id_cliente = c.id where v.id=" + id);
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
                v.setId(rs.getInt(1));
                v.setCliente(cliente);
                v.setFecha(rs.getString(2));
                v.setTotalSinIva(rs.getDouble(3));
                v.setTotalConIva(rs.getDouble(4));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return v;
    }

    @Override
    public void eliminar(int id) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("delete from ventas where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("ELIMINACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(Venta v, int id) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("update ventas set id_cliente=?, fecha=?, total_sin_iva=?, total_con_iva=? where id=?");
            ps.setInt(1, v.getCliente().getId());
            ps.setString(2, v.getFecha());
            ps.setDouble(3, v.getTotalSinIva());
            ps.setDouble(4, v.getTotalConIva());
            ps.setInt(5, id);
            ps.executeUpdate();
            System.out.println("ACTUALIZACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean descontarStock(int idVenta, int cantidad) {
        // Este metodo no aplica para Ventas
        return false;
    }
}
