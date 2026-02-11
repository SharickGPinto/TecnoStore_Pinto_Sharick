
package persistencia;

import java.util.ArrayList;
import modelo.ItemVenta;
import modelo.Venta;


public interface VentaDAO {
    
   void registrarTienda(Venta v, ArrayList<ItemVenta> items);
    
   void registrar(Venta v);
   
   ArrayList<Venta> listar ();
   
   Venta buscar(int id);
   
   void eliminar (int id);
   
   void actualizar (Venta v, int id);
   
   boolean descontarStock (int idVenta, int cantidad);
    
}
