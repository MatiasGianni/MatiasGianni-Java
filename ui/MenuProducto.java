package ui;

import java.util.List;
import java.util.Scanner;

import model.Producto;
import service.ProductoService;
import util.Validador;

public class MenuProducto {
    private final Scanner sc;
    private final ProductoService service;

    public MenuProducto(Scanner sc, ProductoService service) {
        this.sc = sc;
        this.service = service;
    }

    public void mostrarMenu() {
        System.out.println("\n====== TechLab - Gestión de Productos ======");
        System.out.println("1) Agregar producto");
        System.out.println("2) Listar productos");
        System.out.println("3) Buscar producto por ID");
        System.out.println("4) Actualizar producto");
        System.out.println("5) Eliminar producto");
        System.out.println("6) Salir");
        System.out.println("==========================================");
    }

    public void agregarProducto() {
        System.out.println("\n--- Agregar Producto ---");
        String nombre = Validador.leerTexto(sc, "Ingrese nombre: ");
        double precio = Validador.leerDouble(sc, "Ingrese precio: ");
        int stock = Validador.leerEntero(sc, "Ingrese stock: ");
        String categoria = Validador.leerTexto(sc, "Ingrese categoría: ");

        Producto p = new Producto(nombre, precio, stock, categoria);
        Producto guardado = service.guardar(p);
        System.out.println("✔ Producto agregado con ID: " + guardado.getId());
    }

    public void listarProductos() {
        System.out.println("\n--- Lista de Productos ---");
        List<Producto> lista = service.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }
        for (Producto p : lista) {
            p.mostrar();
        }
    }

    public void buscarProducto() {
        System.out.println("\n--- Buscar Producto ---");
        int id = Validador.leerEntero(sc, "Ingrese el ID del producto: ");
        Producto p = service.obtenerPorId(id);
        System.out.println("Encontrado: " + p);
    }

    public void actualizarProducto() {
        System.out.println("\n--- Actualizar Producto ---");
        int id = Validador.leerEntero(sc, "Ingrese el ID a actualizar: ");
        Producto actual = service.obtenerPorId(id);
        System.out.println("Datos actuales: " + actual);

        String nuevoNombre = Validador.leerTexto(sc, "Nuevo nombre: ");
        double nuevoPrecio = Validador.leerDouble(sc, "Nuevo precio: ");
        int nuevoStock = Validador.leerEntero(sc, "Nuevo stock: ");
        String nuevaCategoria = Validador.leerTexto(sc, "Nueva categoría: ");

        Producto nuevosDatos = new Producto(nuevoNombre, nuevoPrecio, nuevoStock, nuevaCategoria);
        service.actualizar(id, nuevosDatos);
        System.out.println("✔ Producto actualizado con éxito.");
    }

    public void eliminarProducto() {
        System.out.println("\n--- Eliminar Producto ---");
        int id = Validador.leerEntero(sc, "Ingrese el ID a eliminar: ");
        service.eliminar(id);
        System.out.println("✔ Producto eliminado.");
    }
}