package controlador.servicios;

import java.util.ArrayList;
import modelo.Celular;
import controlador.persistencia.CelularDAO;
import controlador.persistencia.implementar.CelularDAOimpl;

public class GestorCelulares {

    private final CelularDAO celularDAO= new CelularDAOimpl();
    
    // Si cumple la condicion de registrar tiene que verificar si el id existe, si existe genera error, si no existe dentro de la base de datos lo registra.
    public void registrar(Celular c) {
        Celular existe = celularDAO.buscar(c.getId());
        if (existe != null && existe.getId() != 0)  {
            System.out.println("Error: ya existe un celular con ese ID. " + c.getId());
            return;
        }
        celularDAO.registrar(c);
        System.out.println("Celular registrado.");
    }
   
//Se consulta dentro de la base de datos si existen registros de celulares
    public void listar() {
       ArrayList<Celular> lista = celularDAO.listar();
        if (lista.isEmpty()) {
            System.out.println("NO HAY CELULARES");
            return;
        }

        lista.forEach(System.out::println);
    }
    
    // Se hace una consulta dentro de la base de datos, por la llave primaria
    public Celular buscarPorId(int id) {
        return celularDAO.buscar(id);
    }

    //Se verifica si existe en la base de datos, si no existe no se puede eliminar, pero si existe se elimina
    public void eliminar(int id) {

        Celular existe = celularDAO.buscar(id);

        if (existe == null) {
            System.out.println("No existe un celular con ese ID.");
        return;
        }
        celularDAO.eliminar(id);
            System.out.println("Celular eliminado.");
    }
    
    // Se valida la existencia del celular, si no existe sale mensaje, si existe se sobreescribe los datos del registro 
    public void actualizar(Celular c) {
        Celular existe = buscarPorId(c.getId());
        if (existe != null && existe.getId() != 0) {
            System.out.println("No existe un celular con ese ID");
            return;
        }
       celularDAO.actualizar(c, c.getId());
        System.out.println("Celular Actualizado");
    }
    
    // Aca se  gestiona el stock mediante una actualizacion automatica en la base de datos
    public boolean descontarStock(int idCelular, int cantidad) {
        if (cantidad <= 0) return false;
        return celularDAO.descontarStock(idCelular, cantidad);
    }
}
