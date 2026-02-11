package controlador.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import modelo.Venta;


/*
public class ArchivoUtils {

    private ArrayList<Venta> ventas = new ArrayList<>();
    private final String ARCHIVO = "ventas.dat";

    public void cargar() {
        File f = new File(ARCHIVO);
        if (!f.exists()) {
            return;
        }
        try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            ventas = (ArrayList<Venta>) ois.replaceObject();
        } catch (Exception e) {

        }
    }

    public void exportarBackup() {
        JFileChooser j = new JFileChooser();
        j.setDialogTitle("Escoja la ruta a guardar");
        int op = j.showSaveDialog(j);
        if (op == JFileChooser.APPROVE_OPTION) {
            File destino = j.getSelectedFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
                oos.writeObject(ventas);
                System.out.println("BASE DE DATOS EXPORTADA CORRECTAMENTE");
            } catch (Exception e) {
            }
        }

    }
*/