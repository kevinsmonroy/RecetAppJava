package util;
import java.io.*;

public class ImagenUtil {
    public static void guardarImagen(byte[] imagen, String rutaArchivo){
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
            fos.write(imagen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static byte[] leerImagen(String rutaArchivo){
        File file = new File(rutaArchivo);
        if (!file.exists()) return null;
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] datos = new byte[(int) file.length()];
            int leidos = fis.read(datos);
            if (leidos == datos.length) {
                return datos;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}