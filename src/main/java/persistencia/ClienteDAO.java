
package persistencia;

import java.util.ArrayList;

import modelo.Cliente;


public interface ClienteDAO {
    
 void registrar(Cliente c);
   
   ArrayList<Cliente> listar ();
   
   Cliente buscar(String identificacion);
   
   void eliminar (String identificacion);
   
   void actualizar (Cliente c, String identificacion);
   
 
}
