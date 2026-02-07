
package modelo;

public class Cliente extends Persona{

    public Cliente(int id, String nombre, String identificacion, String correo, String telefono) {
        super(id, nombre, identificacion, correo, telefono);
    }
    
    @Override
    public String toString() {
        return """
               ID: %d
               Nombre: %s
               Identificacion: %s
               Correo: %s
               Telefono: %s
               """.formatted(getId(), getNombre(), getIdentificacion(), getCorreo(), getTelefono());
    }

}
