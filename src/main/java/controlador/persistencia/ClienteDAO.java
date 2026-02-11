
package controlador.persistencia;

import java.util.ArrayList;

import modelo.Cliente;

//Se diseña esta interfaz para centralizar todas las acciones que podemos hacer con los clientes en la base de datos, para matener codigo organizado

public interface ClienteDAO {
    
 void registrar(Cliente c);
   
   ArrayList<Cliente> listar ();
   
   Cliente buscar(String identificacion);
   
   void eliminar (String identificacion);
   
   void actualizar (Cliente c, String identificacion);
   
 
}
