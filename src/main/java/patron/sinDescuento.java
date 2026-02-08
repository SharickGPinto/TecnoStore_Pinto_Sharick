
package patron;

import java.util.List;
import modelo.ItemVenta;

//Se implementa Strategy para q retorne sin descuento 
public class sinDescuento implements EstrategiaDescuento{

    @Override
    public double calcularDescuento(List<ItemVenta> items) {
        return 0;
    }
    
}
