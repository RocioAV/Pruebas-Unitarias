package ar.edu.unju.fi.poo.constantes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Constantes {
    public static double ADICIONAL_TITULO;
    public static double VALOR_ANTIGUEDAD;
    public static double VALOR_HIJO;
    public static final double SUELDO_BASICO = 150000.0;
    public static final double PORCENTAJE_DESCUENTOS = 0.15;
    public static final Map<Integer, Double> CATEGORIAS = new HashMap<>();

    static {
        leerDatosDesdeArchivo();
    }
    private static void leerDatosDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/ar/edu/unju/fi/poo/constantes/empleados-info.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.startsWith("ADICIONAL_TITULO")) {
                    String[] partes = linea.split(":");
                    ADICIONAL_TITULO = Double.parseDouble(partes[1].trim());
                } else if (linea.startsWith("CATEGORIA")) {
                    String[] partes = linea.split(":");
                    int categoria = Integer.parseInt(partes[1].trim());
                    double valor = Double.parseDouble(partes[2].trim());
                    CATEGORIAS.put(categoria, valor);
                } else if (linea.startsWith("VALOR_ANTIGUEDAD")) {
                    String[] partes = linea.split(":");
                    VALOR_ANTIGUEDAD = Double.parseDouble(partes[1].trim());
                } else if (linea.startsWith("VALOR_HIJO")) {
                    String[] partes = linea.split(":");
                    VALOR_HIJO = Double.parseDouble(partes[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo empleados_info.txt: " + e.getMessage());
        }
    }
    public static double getValorCategoria(int categoria) {
        return CATEGORIAS.getOrDefault(categoria, 0.0);
    }
}
