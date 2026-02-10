package vista;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op;
        
        do {
            System.out.println("""
            ==========================================
                SISTEMA TECNOSTORE - MENU PRINCIPAL
            ==========================================
            1. Gestionar Celulares
            2. Gestionar Clientes
            3. Gestionar Ventas
            4. Salir
            ==========================================
            """);
            System.out.print("Seleccione una opcion: ");
            op = sc.nextInt();
            
            while (op < 1 || op > 4) {
                System.out.println("Error, opcion no valida");
                System.out.print("Seleccione una opcion: ");
                op = sc.nextInt();
            }
            
            switch (op) {
                case 1:
                    Menucelular menuCel = new Menucelular();
                    menuCel.menu();
                    break;
                case 2:
                    Menucliente menuCli = new Menucliente();
                    menuCli.menu();
                    break;
                case 3:
                    Menuventa menuVen = new Menuventa();
                    menuVen.menu();
                    break;
                case 4:
                    System.out.println("Gracias por usar el sistema TecnoStore c:");
                    break;
            }
        } while (op != 4);
        
        sc.close();
    }
}
