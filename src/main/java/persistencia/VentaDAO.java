
package persistencia;

import java.util.ArrayList;
import modelo.Venta;


public interface VentaDAO {
    
   void registrar(Venta v);
   
   ArrayList<Venta> listar ();
   
   Venta buscar(int id);
   
   void eliminar (int id);
   
   void actualizar (Venta v, int id);
   
   boolean descontarStock (int idVenta, int cantidad);
    
}
