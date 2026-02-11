
package controlador.patron;

import java.util.List;
import modelo.CategoriaGama;
import modelo.ItemVenta;

/*
Aca se utiliza Strategy para hacer el descuento por gama, en cada producto que pase se le va a aplicar un porcentaje
si el celular es de gama alta no tiene descuento, si es gama media tiene 3% de descuento, si es gama baja tiene 5% de descuento
*/
public class DescuentoPorGama implements EstrategiaDescuento{

    @Override
    public double calcularDescuento(List<ItemVenta> items) {
        return items.stream()
                .mapToDouble(item -> item.getSubtotal() * porcentaje(item.getCelular().getGama()))
                .sum();
    }
    
    private double porcentaje(CategoriaGama gama) {
        if (gama == CategoriaGama.ALTA) return 0.00;
        if (gama == CategoriaGama.MEDIA) return 0.03;
        return 0.05;
    }
    
}
