
package vista;

import java.util.ArrayList;
import java.util.Scanner;
import modelo.Celular;
import modelo.Cliente;
import modelo.ItemVenta;
import modelo.Venta;
import servicios.GestorCelulares;
import servicios.GestorClientes;
import servicios.GestorVentas;


public class Menuventa {
    GestorVentas gv = new GestorVentas();
    GestorClientes gcli = new GestorClientes();
    GestorCelulares gcel = new GestorCelulares();
    Scanner sc = new Scanner(System.in);

    private void registrarVentaTienda() {

        System.out.println("\n--- REGISTRAR VENTA (TIENDA) ---");

        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        String fecha = sc.nextLine();

        System.out.println("\n--- CLIENTES ---");
        gcli.listar();
        System.out.print("Ingrese la identificacion del cliente: ");
        String identificacion = sc.nextLine();

        Cliente cliente = gcli.buscarPorIdentificacion(identificacion);
        if (cliente == null || cliente.getId() == 0) {
            System.out.println("No existe un cliente con esa identificacion.");
            return;
        }

        ArrayList<ItemVenta> items = new ArrayList<>();

        int op = 1;
        while (op == 1) {

            System.out.println("\n--- CELULARES DISPONIBLES ---");
            gcel.listar();

            System.out.print("Ingrese el ID del celular: ");
            int idCelular = new Scanner(System.in).nextInt();

            Celular cel = gcel.buscarPorId(idCelular);
            if (cel == null || cel.getId() == 0) {
                System.out.println("No existe un celular con ese ID.");
            } else {
                System.out.print("Cantidad: ");
                int cantidad = new Scanner(System.in).nextInt();

                while (cantidad <= 0 || cantidad > cel.getStock()) {
                    System.out.println("Cantidad no valida. Stock disponible: " + cel.getStock());
                    System.out.print("Cantidad: ");
                    cantidad = new Scanner(System.in).nextInt();
                }

                double subtotal = cel.getPrecio() * cantidad;

                ItemVenta it = new ItemVenta(0, cel, cantidad, subtotal, null);
                items.add(it);

                System.out.println("Item agregado. Subtotal: " + subtotal);
            }

            System.out.println("\n¿Desea agregar otro celular?");
            System.out.println("1. SI");
            System.out.println("2. NO");
            op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 2) {
                System.out.println("Opcion no valida");
                op = new Scanner(System.in).nextInt();
            }

            sc.nextLine();
        }

        if (items.isEmpty()) {
            System.out.println("No se registro la venta porque no hay items.");
            return;
        }

        double totalSinIva = 0.0;
        for (ItemVenta it : items) {
            totalSinIva += it.getSubtotal();
        }

        Venta venta = new Venta(0, cliente, fecha, totalSinIva, 0.0);

        gv.registrarTienda(venta, items);

        if (venta.getId() != 0) {
            System.out.println("\n--- RESUMEN DE VENTA ---");
            System.out.println("ID Venta: " + venta.getId());
            System.out.println("Cliente: " + cliente.getNombre() + " (" + cliente.getIdentificacion() + ")");
            System.out.println("Fecha: " + venta.getFecha());
            System.out.println("\nItems:");
            for (ItemVenta it : items) {
                System.out.println("- " + it.getCelular().getModelo() + " x" + it.getCantidad() + " = " + it.getSubtotal());
            }
            System.out.println("\nTotal sin IVA : " + venta.getTotalSinIva());
            System.out.println("Total con IVA : " + venta.getTotalConIva());
        }
    }

    private void listar() {
        System.out.println("\n--- LISTADO DE VENTAS ---");

    ArrayList<Venta> ventas = gv.obtenerVentas();

    if (ventas.isEmpty()) {
        System.out.println("No hay ventas registradas.");
        return;
    }

    System.out.println("""
    =====================================================================================
    | ID |   FECHA       | ID CLIENTE | NOMBRE CLIENTE        |        TOTAL          |
    =====================================================================================
    """);

    for (Venta v : ventas) {
        System.out.printf(
                "| %-2d | %-12s | %-10d | %-20s | $ %-15.2f |\n",
                v.getId(),
                v.getFecha(),
                v.getCliente().getId(),
                v.getCliente().getNombre(),
                v.getTotalConIva()
        );
    }

    System.out.println("=====================================================================================");
}
    

    private void buscar() {
        System.out.print("\nIngrese el ID de la venta a buscar: ");
        int id = new Scanner(System.in).nextInt();

        Venta v = gv.buscarPorId(id);
        if (v != null && v.getId() != 0) {
            System.out.println("\nVENTA ENCONTRADA:");
            System.out.println(v);
        } else {
            System.out.println("No existe una venta con ese ID.");
        }
    }

    private void eliminar() {
        System.out.print("\nIngrese el ID de la venta a eliminar: ");
        int id = new Scanner(System.in).nextInt();
        gv.eliminar(id);
    }

    public void menu() {
        int op;
        do {
            System.out.println("""
                                ==========================================
                                            GESTION DE VENTAS
                                ==========================================
                               1.   Registrar 
                               2.   Eliminar
                               3.   Listar
                               4.   Buscar
                               5.   Regresar
                               """);
            op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 5) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    registrarVentaTienda();
                    break;
                case 2:
                    eliminar();
                    break;
                case 3:
                    listar();
                    break;
                case 4:
                    buscar();
                    break;
            }
        } while (op != 5);
    }
}
    


