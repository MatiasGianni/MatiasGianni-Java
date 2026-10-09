import java.util.Scanner;

import exception.ProductoNoEncontradoException;
import exception.StockInsuficienteException;
import model.Producto;
import service.ProductoService;
import ui.MenuProducto;
import util.Validador;

public class Main {

    public static void main(String[] args) {
        ProductoService service = new ProductoService();
        Scanner sc = new Scanner(System.in);
        MenuProducto menu = new MenuProducto(sc, service);

        cargarDatosDePrueba(service);

        int opcion = 0;

        do {
            menu.mostrarMenu();
            opcion = Validador.leerEntero(sc, "Elija una opción: ");

            try {
                switch (opcion) {
                    case 1 -> menu.agregarProducto();
                    case 2 -> menu.listarProductos();
                    case 3 -> menu.buscarProducto();
                    case 4 -> menu.actualizarProducto();
                    case 5 -> menu.eliminarProducto();
                    case 6 -> System.out.println("¡Hasta luego!");
                    default -> System.out.println("Opción inválida. Elija un número del 1 al 6.");
                }
            } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
                System.out.println("⚠️ " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ Error en datos: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("⚠️ Error inesperado: " + e.getMessage());
            }

        } while (opcion != 6);

        sc.close();
    }

    private static void cargarDatosDePrueba(ProductoService service) {
        try {
            service.guardar(new Producto("Café molido 500g", 4500.0, 30, "Bebidas"));
            service.guardar(new Producto("Yerba mate 1kg", 3200.0, 50, "Bebidas"));
            service.guardar(new Producto("Galletitas dulces", 1850.0, 100, "Almacén"));
            service.guardar(new Producto("Aceite de oliva 500ml", 6700.0, 20, "Almacén"));
            service.guardar(new Producto("Chocolate amargo 70%", 2900.0, 15, "Golosinas"));
            System.out.println("✔ Se cargaron 5 productos de prueba.");
        } catch (Exception e) {
            System.out.println("Error al cargar datos de prueba: " + e.getMessage());
        }
    }
}