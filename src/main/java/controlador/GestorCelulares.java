package controlador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelo.Celular;
import java.io.File;

public class GestorCelulares {

    private ArrayList<Celular> celulares = new ArrayList<>();
    private final String ARCHIVO = "Celulares.dat";

    public GestorCelulares() {
        cargar();
    }

    /*
    En esta parte es para guardar .dat = binario
    
     */
    public void cargar() {
        File f = new File(ARCHIVO);
        if (!f.exists()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            celulares = (ArrayList<Celular>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(celulares);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Aca se empieza a utilizar Stream API
    para registrar, lambda para listar, buscar por id Stream API 
    eliminar, actualizar y descontar stock
     */
    
    public void registrar(Celular c) {
        boolean existe = celulares.stream().anyMatch(x -> x.getId() == c.getId());
        if (existe) {
            System.out.println("Error: ya existe un celular con ese ID. " + c.getId());
            return;
        }
        celulares.add(c);
        guardar();
        System.out.println("Celular registrado.");
    }

    public void listar() {
        if (celulares.isEmpty()) {
            System.out.println("NO HAY CELULARES");
            return;
        }

        celulares.forEach(System.out::println);
    }

    public Celular buscarPorId(int id) {
        return celulares.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void eliminar(int id) {

        boolean eliminado = celulares.removeIf(c -> c.getId() == id);

        if (eliminado) {
            guardar();
            System.out.println("Celular eliminado.");
        } else {
            System.out.println("No existe un celular con ese ID.");
        }
    }

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

        guardar();
        System.out.println("Celular Actualizado");
    }

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
        guardar();
        return true;
    }

}
