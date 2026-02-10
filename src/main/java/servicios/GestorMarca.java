package servicios;

import java.util.ArrayList;
import modelo.Marca;
import persistencia.MarcaDAO;
import persistencia.implementar.MarcaDAOImpl;

public class GestorMarca {

    private final MarcaDAO marcaDAO = new MarcaDAOImpl();

    // Verifica en la base de datos si existe con ese id, si no existe lo registra
    public void registrar(Marca m) {
        Marca existe = marcaDAO.buscar(m.getId());
        if (existe != null && existe.getId() != 0) {
            System.out.println("Error: ya existe una marca con ese ID. " + m.getId());
            return;
        }
        marcaDAO.registrar(m);
        System.out.println("Marca registrada.");
    }

    // Mira internamente si hay marcas, si existen las muestra, si no muestra mensaje
    public void listar() {
        ArrayList<Marca> lista = marcaDAO.listar();
        if (lista.isEmpty()) {
            System.out.println("NO HAY MARCAS");
            return;
        }
        lista.forEach(System.out::println);
    }

    // Busca dentro de la base de datos si existe la marca por id
    public Marca buscarPorId(int id) {
        return marcaDAO.buscar(id);
    }

    // Busca la marca en la base de datos y si existe la elimina, si no existe envía mensaje
    public void eliminar(int id) {
        Marca existe = marcaDAO.buscar(id);
        if (existe != null && existe.getId() != 0) {
            marcaDAO.eliminar(id);
            System.out.println("Marca eliminada.");
        } else {
            System.out.println("No existe una marca con ese ID.");
        }
    }

    // Valida la existencia de la marca para poder actualizar
    public void actualizar(Marca m) {
        Marca existe = buscarPorId(m.getId());
        if (existe == null || existe.getId() == 0) {
            System.out.println("No existe una marca con ese ID.");
            return;
        }
        marcaDAO.actualizar(m, m.getId());
        System.out.println("Marca Actualizada.");
    }
}