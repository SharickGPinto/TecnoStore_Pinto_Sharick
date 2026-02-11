
package servicios;

import java.util.ArrayList;
import modelo.Venta;
import persistencia.VentaDAO;
import persistencia.implementar.VentaDAOImpl;


public class GestorVentas {
    
   private final VentaDAO ventaDAO= new VentaDAOImpl();
    
    // Si cumple la condicion de registrar tiene que verificar si el id existe, si existe genera error, si no existe dentro de la base de datos lo registra.
    public void registrar(Venta v) {
        Venta existe = ventaDAO.buscar(v.getId());
        if (existe != null && existe.getId() != 0)  {
            System.out.println("Error: ya existe una venta con ese ID. " + v.getId());
            return;
        }
        ventaDAO.registrar(v);
        System.out.println("Venta registrada.");
    }
   
//Se consulta dentro de la base de datos si existen registros de la venta
    public void listar() {
       ArrayList<Venta> lista = ventaDAO.listar();
        if (lista.isEmpty()) {
            System.out.println("NO HAY VENTAS");
            return;
        }

        lista.forEach(System.out::println);
    }
    
    // Se hace una consulta dentro de la base de datos, por la llave primaria
    public Venta buscarPorId(int id) {
        return ventaDAO.buscar(id);
    }

    //Se verifica si existe en la base de datos, si no existe no se puede eliminar, pero si existe se elimina
    public void eliminar(int id) {

        Venta existe = ventaDAO.buscar(id);

        if (existe == null) {
            System.out.println("No existe una venta con ese ID.");
        return;
        }
        ventaDAO.eliminar(id);
            System.out.println("Venta eliminada.");
    }
    
    // Se valida la existencia de la venta, si no existe sale mensaje, si existe se sobreescribe los datos del registro 
    public void actualizar(Venta v) {
        Venta existe = buscarPorId(v.getId());
        if (existe == null) {
            System.out.println("No existe una veta con ese ID");
            return;
        }
       ventaDAO.actualizar(v, v.getId());
        System.out.println("Celular Actualizado");
    }

}
