
package controlador.persistencia;

import java.util.ArrayList;
import modelo.ItemVenta;
import modelo.Venta;

//Se diseña esta interfaz para centralizar todas las acciones que podemos hacer con las ventas en la base de datos, para matener codigo organizado
//Ademas funciona como interfaz para ItemVenta, para evitar codigo extra y errores a futuro

public interface VentaDAO {
    
   void registrar(Venta v, ArrayList<ItemVenta> items); //una venta no existe sin sus productos
   
   ArrayList<Venta> listar ();
   
   Venta buscar(int id);
   
   void eliminar (int id);
   
   void actualizar (Venta v, int id);
   
   boolean descontarStock (int idVenta, int cantidad);
    
}
