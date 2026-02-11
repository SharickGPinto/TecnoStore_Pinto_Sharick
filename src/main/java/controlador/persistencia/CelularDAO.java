
package controlador.persistencia;

import java.util.ArrayList;
import modelo.Celular;
//Se diseña esta interfaz para centralizar todas las acciones que podemos hacer con los celulares en la base de datos, para matener codigo organizado
public interface CelularDAO {
    
   void registrar(Celular c);
   
    ArrayList<Celular> listar ();
   
   Celular buscar(int id);
   
   void eliminar (int id);
   
   void actualizar (Celular c, int id);
   
   boolean descontarStock (int idCelular, int cantidad);
    
}
