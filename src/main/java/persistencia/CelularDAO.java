
package persistencia;

import java.util.ArrayList;
import modelo.Celular;

public interface CelularDAO {
    
   void registrar(Celular c);
   
    ArrayList<Celular> listar ();
   
   Celular buscar(int id);
   
   void eliminar (int id);
   
   void actualizar (Celular c, int id);
   
   boolean descontarStock (int idCelular, int cantidad);
    
}
