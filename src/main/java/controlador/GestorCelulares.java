package controlador;

import java.util.ArrayList;
import modelo.Celular;


public class GestorCelulares {

    private ArrayList<Celular> celulares = new ArrayList<>();

    /*
    Aca se empieza a utilizar Stream API
    para registrar, lambda para listar, buscar por id Stream API 
    eliminar, actualizar y descontar stock
     */
    
    
    // Si cumple la condicion de registrar tiene que verificar si el id existe, si existe genera error, si no existe se registra dentro del ArrayList de celulares.
    public void registrar(Celular c) {
        boolean existe = celulares.stream().anyMatch(x -> x.getId() == c.getId());
        if (existe) {
            System.out.println("Error: ya existe un celular con ese ID. " + c.getId());
            return;
        }
        celulares.add(c);
        System.out.println("Celular registrado.");
    }
    
    
//Se verifica si hay celulares, si no hay se muestra un mensaje, pero si existe muestra los celulares que existen.
    public void listar() {
        if (celulares.isEmpty()) {
            System.out.println("NO HAY CELULARES");
            return;
        }

        celulares.forEach(System.out::println);
    }
    
    
    // Se filtra por el id y debe devolver el celular que tenga ese id, si no existe un celular que le pertenezca ese id, retorna en nulo.
    public Celular buscarPorId(int id) {
        return celulares.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    //Se busca el id del celular, si existe se elimina, si no existe un celular con ese id no hace nada.
    public void eliminar(int id) {

        boolean eliminado = celulares.removeIf(c -> c.getId() == id);

        if (eliminado) {
            System.out.println("Celular eliminado.");
        } else {
            System.out.println("No existe un celular con ese ID.");
        }
    }
    
    //Aca se busca el id, si no existe se termina el proceso de buscar id, pero si existe se empieza a actualizar el celular obteniendo sus referencias
    public void actualizar(Celular c) {
        Celular existe = buscarPorId(c.getId());
        if (existe == null) {
            System.out.println("No existe un celular con ese ID");
            return;
        }
        existe.setMarca(c.getMarca());
        existe.setModelo(c.getModelo());
        existe.setSistemaOS(c.getSistemaOS());
        existe.setGama(c.getGama());
        existe.setStock(c.getStock());
        existe.setPrecio(c.getPrecio());
        System.out.println("Celular Actualizado");
    }
    
    // Aca se busca el celular mediante el id, si el celular no existe no se descuenta, pero si no hay stock suficiente se cancela y deja el stock como esta, pero si hay venta se descuenta del stock
    public boolean descontarStock(int idCelular, int cantidad) {
        Celular c = buscarPorId(idCelular);
        if (c == null) {
            return false;
        }
        if (cantidad <= 0) {
            return false;
        }
        if (c.getStock() < cantidad) {
            return false;
        }

        c.setStock(c.getStock() - cantidad);
        return true;
    }

}
