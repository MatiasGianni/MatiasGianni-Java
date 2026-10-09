package util;

import java.util.InputMismatchException;
import java.util.Scanner;

import exception.StockInsuficienteException;

/*
   Clase con métodos de validación reutilizables.
   Todos los métodos son estáticos: no necesitamos crear una instancia de Validador.
*/
public class Validador {

    // Validaciones de datos del producto
    public static void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
    }

    public static void validarPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
    }

    public static void validarStock(int stock) {
        if (stock < 0) {
            throw new StockInsuficienteException("El stock no puede ser un número negativo.");
        }
    }

    public static void validarCategoria(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }
    }

    // Lectura por consola
    public static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // Limpiar el salto de línea
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("❌ Error: Debe ingresar un número entero válido.");
                sc.nextLine(); // Limpiar entrada incorrecta
            }
        }
    }

    public static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = sc.nextDouble();
                sc.nextLine(); // Limpiar el salto de línea
                return valor;
            } catch (Exception e) {
                System.out.println("❌ Error: Debe ingresar un número decimal.");
                sc.nextLine();
            }
        }
    }

    public static String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }
}