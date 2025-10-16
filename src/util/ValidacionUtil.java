package util;
import dto.Receta;

public class ValidacionUtil {
    public static boolean validarReceta(Receta receta){
        if (receta == null) return false;
        if (receta.obtenerNombre() == null || receta.obtenerNombre().trim().isEmpty()) return false;
        if (receta.obtenerIngredientes() == null || receta.obtenerIngredientes().isEmpty()) return false;
        if (receta.obtenerPasos() == null || receta.obtenerPasos().isEmpty()) return false;
        return true;
    }
    public static boolean validarNombre(String nombre){
        return nombre != null && !nombre.trim().isEmpty();
    }
}