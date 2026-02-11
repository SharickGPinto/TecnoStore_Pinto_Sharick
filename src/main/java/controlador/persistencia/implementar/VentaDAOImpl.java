package controlador.persistencia.implementar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.ItemVenta;
import modelo.Venta;
import controlador.persistencia.ConexionDB;
import controlador.persistencia.VentaDAO;

public class VentaDAOImpl implements VentaDAO {

    ConexionDB c = new ConexionDB();

    @Override //ACa es donde se registra una venta que pide el cliente, el iva se activa con un trigger en la base de datos
    public void registrar(Venta v, ArrayList<ItemVenta> items) {

        if (v == null || v.getCliente() == null) {
            System.out.println("Error: venta o cliente nulo");
            return;
        }
        if (items == null || items.isEmpty()) {
            System.out.println("Error: no hay items para registrar");
            return;
        }

        try (Connection con = c.conectar()) {

            //  Validar stock antes de registrar
            for (ItemVenta it : items) {
                int idCelular = it.getCelular().getId();
                int cantidad = it.getCantidad();

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select stock from celulares where id=" + idCelular);

                int stock = 0;
                if (rs.next()) {
                    stock = rs.getInt(1);
                }

                if (stock < cantidad) {
                    System.out.println("Error: stock insuficiente para el celular ID " + idCelular + " (stock " + stock + ")");
                    return;
                }
            }

            //  Insertar venta (IVA lo calcula el trigger)
            PreparedStatement ps = con.prepareStatement(
                    "insert into ventas(id_cliente, fecha, total_sin_iva, total_con_iva) values (?,?,?,?)"
            );
            ps.setInt(1, v.getCliente().getId());
            ps.setString(2, v.getFecha());
            ps.setDouble(3, v.getTotalSinIva());
            ps.setDouble(4, 0.0);
            ps.executeUpdate();

            //  Obtener ID de la venta 
            Statement stId = con.createStatement();
            ResultSet rsId = stId.executeQuery("select max(id) from ventas");
            int idVenta = 0;
            if (rsId.next()) {
                idVenta = rsId.getInt(1);
            }
            v.setId(idVenta);

            //  Insertar items y descontar stock
            PreparedStatement psItem = con.prepareStatement(
                    "insert into items_venta(id_venta, id_celular, cantidad, subtotal) values (?,?,?,?)"
            );
            PreparedStatement psStock = con.prepareStatement(
                    "update celulares set stock = stock - ? where id = ? and stock >= ?"
            );

            for (ItemVenta it : items) {
                int idCelular = it.getCelular().getId();
                int cantidad = it.getCantidad();

                psStock.setInt(1, cantidad);
                psStock.setInt(2, idCelular);
                psStock.setInt(3, cantidad);

                int filas = psStock.executeUpdate();
                if (filas == 0) {
                    System.out.println("Error: no se pudo descontar stock del celular ID " + idCelular);
                    return;
                }

                psItem.setInt(1, idVenta);
                psItem.setInt(2, idCelular);
                psItem.setInt(3, cantidad);
                psItem.setDouble(4, it.getSubtotal());
                psItem.executeUpdate();
            }

            // 5) Leer el total_con_iva ya calculado por el trigger
            Statement stTotal = con.createStatement();
            ResultSet rsTotal = stTotal.executeQuery("select total_con_iva from ventas where id=" + idVenta);
            if (rsTotal.next()) {
                v.setTotalConIva(rsTotal.getDouble(1));
            }

            System.out.println("REGISTRO EXITOSO! Venta #" + v.getId());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    

    //metodo para listar, se trae cliente y ventas para mostrar el resultado de las compras de los clientes
    @Override
    public ArrayList<Venta> listar() {
        ArrayList<Venta> ventas = new ArrayList<>();
        // lista vacia donde se guardaran las ventas
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

    //metodo para buscar venta mediante el id de la venta
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

    @Override   //metodo para eliminar venta
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

    @Override //metodo para actualizar la venta 
    public void actualizar(Venta v, int id) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("update ventas set id_cliente=?, fecha=?, total_sin_iva=?, total_con_iva=? where id=?");
            ps.setInt(1, v.getCliente().getId());
            ps.setString(2, v.getFecha());
            ps.setDouble(3, v.getTotalSinIva());
            ps.setDouble(4, 0.0); // trigger recalcula
            ps.setInt(5, id);
            ps.executeUpdate();
            System.out.println("ACTUALIZACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean descontarStock(int idVenta, int cantidad) {
        return false;
    }
}
