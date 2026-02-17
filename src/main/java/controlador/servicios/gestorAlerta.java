
package controlador.servicios;

import controlador.persistencia.AlertaStockDAO;
import controlador.persistencia.implementar.AlertaStockDAOimpl;



public class gestorAlerta {
    
    private final AlertaStockDAO alertaDAO = new AlertaStockDAOimpl();
    
        public boolean descontarStock(int idCelular, int cantidad) {
        if (cantidad <= 0) return false;
        return; alertaDAO
        
    }
}
