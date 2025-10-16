package util;
import dto.Receta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoUtil {
    public static void guardarRecetas(List<Receta> recetas, String rutaArchivo){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(recetas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Receta> leerRecetas(String rutaArchivo){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (List<Receta>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Si el archivo no existe o está vacío, retorna una lista vacía
            return new ArrayList<>();
        }
    }
}
