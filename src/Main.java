import gestion.GestionReceta;
import dto.Receta;
import dto.TipoReceta;
import util.ValidacionUtil;
import util.ImagenUtil;

import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GestionReceta gestion = new GestionReceta();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- RecetAppJava ---");
            System.out.println("1. Ver todas las recetas");
            System.out.println("2. Ver recetas favoritas");
            System.out.println("3. Buscar receta por nombre");
            System.out.println("4. Filtrar recetas por tipo");
            System.out.println("5. Crear nueva receta");
            System.out.println("6. Marcar/Desmarcar receta como favorita");
            System.out.println("7. Eliminar receta");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = leerInt();

            switch (opcion) {
                case 1: mostrarRecetas(gestion.obtenerReceta()); break;
                case 2: mostrarRecetas(gestion.obtenerFavoritas()); break;
                case 3: buscarReceta(); break;
                case 4: filtrarPorTipo(); break;
                case 5: crearReceta(); break;
                case 6: marcarDesmarcarFavorita(); break;
                case 7: eliminarReceta(); break;
                case 0: System.out.println("¡Hasta luego!"); break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarRecetas(List<Receta> recetas) {
        if (recetas.isEmpty()) {
            System.out.println("No hay recetas para mostrar.");
            return;
        }
        for (Receta receta : recetas) {
            System.out.println("\n--- " + receta.obtenerNombre() + " ---");
            System.out.println("ID: " + receta.ObtenerId());
            System.out.println("Tipo: " + receta.obtenerTipo());
            System.out.println("Favorita: " + (receta.obtenerEsFavorita() ? "Sí" : "No"));
            System.out.println("Tiempo de cocción: " + receta.obtenerCoccion() + " min");

            System.out.println("\nIngredientes:");
            for (String ing : receta.obtenerIngredientes()) {
                System.out.println("- " + ing);
            }
            System.out.println("\nPasos:");
            for (int i = 0; i < receta.obtenerPasos().size(); i++) {
                System.out.println((i + 1) + ". " + receta.obtenerPasos().get(i));
            }
            if (receta.obtenerFotoPath() != null && !receta.obtenerFotoPath().isEmpty()) {
                System.out.println("Foto guardada en: " + receta.obtenerFotoPath());
            }
        }
    }

    private static void buscarReceta() {
        System.out.print("Escribe el nombre a buscar: ");
        String nombre = scanner.nextLine();
        mostrarRecetas(gestion.buscarPorNombre(nombre));
    }

    private static void filtrarPorTipo() {
        System.out.println("Tipos de receta:");
        for (TipoReceta tipo : TipoReceta.values()) {
            System.out.println("- " + tipo);
        }
        System.out.print("Escribe el tipo: ");
        String tipoStr = scanner.nextLine().toUpperCase();
        try {
            TipoReceta tipo = TipoReceta.valueOf(tipoStr);
            mostrarRecetas(gestion.filtrarPorTipo(tipo));
        } catch (Exception e) {
            System.out.println("Tipo inválido.");
        }
    }

    private static void crearReceta() {
        System.out.print("Nombre de la receta: ");
        String nombre = scanner.nextLine();

        List<String> ingredientes = new ArrayList<>();
        System.out.println("Agrega ingredientes (escribe 'fin' para terminar):");
        while (true) {
            String ing = scanner.nextLine();
            if (ing.equalsIgnoreCase("fin")) break;
            if (ValidacionUtil.validarNombre(ing)) ingredientes.add(ing);
        }

        List<String> pasos = new ArrayList<>();
        System.out.println("Agrega pasos (escribe 'fin' para terminar):");
        while (true) {
            String paso = scanner.nextLine();
            if (paso.equalsIgnoreCase("fin")) break;
            if (ValidacionUtil.validarNombre(paso)) pasos.add(paso);
        }

        System.out.println("Tipos de receta:");
        for (TipoReceta tipo : TipoReceta.values()) {
            System.out.println("- " + tipo);
        }
        System.out.print("Escribe el tipo: ");
        String tipoStr = scanner.nextLine().toUpperCase();
        TipoReceta tipoReceta;
        try {
            tipoReceta = TipoReceta.valueOf(tipoStr);
        } catch (Exception e) {
            System.out.println("Tipo inválido. Usando POSTRE por defecto.");
            tipoReceta = TipoReceta.POSTRE;
        }

        System.out.print("Tiempo de cocción (en minutos): ");
        int coccion = leerInt();

        System.out.print("¿Agregar foto? (ruta de la imagen o enter para omitir): ");
        String fotoPath = scanner.nextLine();
        if (fotoPath.isEmpty()) fotoPath = null;

        Receta nueva = new Receta(
                UUID.randomUUID().toString(),
                nombre,
                tipoReceta,
                ingredientes,
                pasos,
                coccion,
                fotoPath,
                false
        );

        if (ValidacionUtil.validarReceta(nueva)) {
            gestion.agregarReceta(nueva);
            System.out.println("¡Receta creada con éxito!");
        } else {
            System.out.println("Datos de receta inválidos. No se guardó.");
        }
    }

    private static void marcarDesmarcarFavorita() {
        System.out.print("Escribe el ID de la receta: ");
        String id = scanner.nextLine();
        Receta receta = buscarRecetaPorId(id);
        if (receta == null) {
            System.out.println("Receta no encontrada.");
            return;
        }
        if (receta.obtenerEsFavorita()) {
            gestion.desmarcarFavorita(id);
            System.out.println("Receta desmarcada como favorita.");
        } else {
            gestion.marcarFavorita(id);
            System.out.println("Receta marcada como favorita.");
        }
    }

    private static void eliminarReceta() {
        System.out.print("Escribe el ID de la receta a eliminar: ");
        String id = scanner.nextLine();
        Receta receta = buscarRecetaPorId(id);
        if (receta == null) {
            System.out.println("Receta no encontrada.");
            return;
        }
        gestion.eliminarReceta(id);
        System.out.println("Receta eliminada.");
    }

    private static Receta buscarRecetaPorId(String id) {
        for (Receta receta : gestion.obtenerReceta()) {
            if (receta.ObtenerId().equals(id)) return receta;
        }
        return null;
    }

    private static int leerInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
}
