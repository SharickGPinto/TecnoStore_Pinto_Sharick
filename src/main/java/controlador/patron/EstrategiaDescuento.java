
package controlador.patron;

import java.util.List;
import modelo.ItemVenta;

// Aca se crea Strategy se crea una interfaz, cualquier nueva forma de calcular descuentos se debe implementar esta interfaz
public interface EstrategiaDescuento {
   double calcularDescuento(List <ItemVenta> items);
    
    
}
