
package com.albcas.boxify;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean salir = false;
        Inventario inventario = new Inventario();
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Bienvenido a Boxify");
            listarMenuPrincipal();

            // Pregunta opción Menú 1
            int opc = leerEntero(sc);

            switch (opc) {
                case 1:
                    anadirArticulo(inventario, sc);
                    break;
                case 2:
                    listarInventario(inventario);
                    break;
                case 3:
                    Articulo articuloSeleccionado =
                            seleccionarArticulo(inventario, sc);

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
                                eliminarArticulo(inventario, articuloSeleccionado);
                                opc2 = 3;
                                break;
                            case 2:
                                modificarArticulo(inventario, articuloSeleccionado, sc);
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
    static void anadirArticulo(Inventario inventario, Scanner sc) {
        System.out.println("Nombre del artículo");
        String nombreArt = sc.nextLine();

        System.out.println("Tamaño del artículo (1.Peq. 2.Medio. 3.Grand. 4.Volum.)");
        int opcionTamano = leerEntero(sc);

        Articulo.Tamano[] tamanos = Articulo.Tamano.values();

        if (opcionTamano < 1 || opcionTamano > tamanos.length) {
            System.out.println("Seleccione un tamaño válido");
            return;
        }

        Articulo.Tamano tamanoArt = tamanos[opcionTamano - 1];

        System.out.println("Anotaciones extra");
        String notasArt = sc.nextLine();

        Articulo articulo = new Articulo(tamanoArt, nombreArt, notasArt);
        inventario.anadirArticulo(articulo);
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
            System.out.println((i + 1) + ". "
                    + inventario.getArticulos().get(i));
        }

        int articuloSelec = leerEntero(sc);

        if (articuloSelec < 1
                || articuloSelec > inventario.getArticulos().size()) {
            System.out.println("Seleccione un artículo válido");
            return null;
        }

        return inventario.getArticulos().get(articuloSelec - 1);
    }

    /* Menu 2 */
    static void eliminarArticulo(Inventario inventario, Articulo articulo) {
        if (inventario.buscarArticulo(articulo.getId()) != null) {
            inventario.eliminarArticulo(articulo);
        } else {
            System.out.println("El artículo no se encontró en el inventario. \nUUID: "
                    + articulo.getId());
        }
    }

    static void modificarArticulo(
            Inventario inventario, Articulo articulo, Scanner sc) {

        if (inventario.buscarArticulo(articulo.getId()) != null) {
            System.out.println("Modificar nombre? S/N");

            if (sc.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Nuevo nombre:");
                articulo.setNombre(sc.nextLine()); // cambiar nombre
            }

            System.out.println("Modificar tamaño? S/N");

            if (sc.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Nuevo Tamaño (1-4):");

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
                System.out.println("Nueva nota");
                String nuevaNota = sc.nextLine();

                articulo.setNota(
                        articulo.getNota() == null
                                || articulo.getNota().isBlank()
                                ? nuevaNota
                                : articulo.getNota() + "\n" + nuevaNota
                ); // Añadir nota
            }
        } else {
            System.out.println("El artículo no se encontró en el inventario. \nUUID: "
                    + articulo.getId());
        }
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
