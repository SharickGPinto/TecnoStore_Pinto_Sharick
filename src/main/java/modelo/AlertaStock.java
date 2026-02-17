package modelo;

public class AlertaStock extends Celular {

    public AlertaStock(int id, String modelo, String sistemaOS, CategoriaGama gama, int stock, double precio, Marca id_marca) {
        super(id, modelo, sistemaOS, gama, stock, precio, id_marca);
    }

    @Override
    public String toString() {
         return """
               ID: %d
               Nombre: %s
               Identificacion: %s
               Correo: %s
               Telefono: %s
               """.formatted(getId(), getModelo(), getGama(), getStock(), getPrecio());
    }
    
    

}
