package com.albcas.boxify;

import com.albcas.boxify.persistencia.PersistenciaJson;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean salir = false;

        /* Inicialización y carga de datos */
        PersistenciaJson persistencia = new PersistenciaJson();
        Inventario inventario = new Inventario();

        try {
            for (Articulo articulo : persistencia.cargar()) {
                inventario.anadirArticulo(articulo);
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar el inventario: " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Bienvenido a Boxify");
            listarMenuPrincipal();

            // Pregunta opción Menú 1
            int opc = leerEntero(sc);

            switch (opc) {
                case 1:
                    if (anadirArticulo(inventario, sc)) {
                        persistencia.guardar(inventario);
                    }
                    break;
                case 2:
                    listarInventario(inventario);
                    break;
                case 3:
                    Articulo articuloSeleccionado = seleccionarArticulo(inventario, sc);

                    if (articuloSeleccionado == null) {
                        break;
                    }

                    // Infierno switch temporal en vez de usar un Strategy Pattern
                    int opc2;
                    do {
                        listarMenuDeArticulo();
                        opc2 = leerEntero(sc);

                        switch (opc2) {
                            case 1:
                                if (eliminarArticulo(inventario, articuloSeleccionado)) {
                                    persistencia.guardar(inventario);
                                }
                                opc2 = 3;
                                break;
                            case 2:
                                if (modificarArticulo(inventario, articuloSeleccionado, sc)) {
                                    persistencia.guardar(inventario);
                                }
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Seleccione una opción válida");
                                break;
                        }
                    } while (opc2 != 3);

                    break;
                case 0:
                    System.out.println("Saliendo");
                    salir = true;
                    break;

                default:
                    System.out.println("Seleccione una opción válida");
                    break;
            }

        } while (!salir);

        sc.close();
    }

    // Opciones
    static boolean anadirArticulo(Inventario inventario, Scanner sc) {
        System.out.println("Nombre del artículo");
        String nombreArt = sc.nextLine();

        System.out.println("Tamaño del artículo (1.Peq. 2.Medio. 3.Grand. 4.Enorme.)");
        int opcionTamano = leerEntero(sc);


        Articulo.Tamano[] tamanos = Articulo.Tamano.values();

        // Verificacion
        if (opcionTamano < 1 || opcionTamano > tamanos.length) {
            System.out.println("Seleccione un tamaño válido");
            return false;
        }

        // Asignación de tamaño
        Articulo.Tamano tamanoArt = tamanos[opcionTamano - 1];

        System.out.println("Anotaciones extra");
        String notasArt = sc.nextLine();

        Articulo articulo = new Articulo(tamanoArt, nombreArt, notasArt);
        inventario.anadirArticulo(articulo);

        return true;
    }

    static void listarInventario(Inventario inventario) {
        String listaInventario = inventario.listarArticulos();
        System.out.println(listaInventario);
    }

    static Articulo seleccionarArticulo(Inventario inventario, Scanner sc) {
        if (inventario.getArticulos().isEmpty()) {
            System.out.println("El inventario está vacío");
            return null;
        }

        System.out.println("Seleccione el artículo por su número");

        for (int i = 0; i < inventario.getArticulos().size(); i++) {
            System.out.println((i + 1) + ". " + inventario.getArticulos().get(i));
        }

        int articuloSelec = leerEntero(sc);

        if (articuloSelec < 1 || articuloSelec > inventario.getArticulos().size()) {
            System.out.println("Seleccione un artículo válido");
            return null;
        }

        return inventario.getArticulos().get(articuloSelec - 1);
    }

    /* Menu 2 */
    static boolean eliminarArticulo(Inventario inventario, Articulo articulo) {
        if (inventario.buscarArticulo(articulo.getId()) != null) {
            inventario.eliminarArticulo(articulo);
            return true;
        } else {
            System.out.println("El artículo no se encontró en el inventario. \nUUID: " + articulo.getId());
            return false;
        }
    }

    static boolean modificarArticulo(Inventario inventario, Articulo articulo, Scanner sc) {

        boolean cambios = false;
        if (inventario.buscarArticulo(articulo.getId()) != null) {
            System.out.println("Modificar nombre? S/N");

            if (sc.nextLine().equalsIgnoreCase("s")) {
                cambios = true;
                System.out.println("Nuevo nombre:");
                articulo.setNombre(sc.nextLine()); // cambiar nombre
            }

            System.out.println("Modificar tamaño? S/N");

            if (sc.nextLine().equalsIgnoreCase("s")) {
                cambios = true;
                System.out.println("Nuevo Tamaño (1-5):");

                int opcionTamano = leerEntero(sc);
                Articulo.Tamano[] tamanos = Articulo.Tamano.values();

                if (opcionTamano >= 1 && opcionTamano <= tamanos.length) {
                    articulo.setTamano(tamanos[opcionTamano - 1]); // cambiar tamaño
                } else {
                    System.out.println("Seleccione un tamaño válido");
                }
            }

            System.out.println("Modificar notas? S/N");

            if (sc.nextLine().equalsIgnoreCase("s")) {
                cambios = true;
                System.out.println("Nueva nota");
                String nuevaNota = sc.nextLine();

                articulo.setNota(articulo.getNota() == null || articulo.getNota().isBlank() ? nuevaNota : articulo.getNota() + "\n" + nuevaNota); // Añadir nota
            }
        } else {
            System.out.println("El artículo no se encontró en el inventario. \nUUID: " + articulo.getId());
        }
        return cambios;
    }

    // Lectura de números enteros
    static int leerEntero(Scanner sc) {
        while (true) {
            String entrada = sc.nextLine();

            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido");
            }
        }
    }

    // Menu
    static void listarMenuPrincipal() {
        System.out.println("""
                --- Elija una opción ---
                1. Añadir al inventario
                2. Listar inventario
                3. Seleccionar artículo
                0. Salir
                """);
    }

    static void listarMenuDeArticulo() {
        System.out.println("""
                --- Elija una opción ---
                1. Eliminar artículo
                2. Modificar artículo
                3. Volver al Menú Principal
                """);
    }
}
