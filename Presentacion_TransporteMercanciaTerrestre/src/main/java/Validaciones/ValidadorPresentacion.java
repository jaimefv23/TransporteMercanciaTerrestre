package Validaciones;

import java.util.Date;
import java.util.regex.Pattern;
import java.time.LocalDate;

/**
 *
 * @author alex
 */
public class ValidadorPresentacion {

    private static final Pattern CORREO = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern TELEFONO = Pattern.compile("^\\d{10}$");
    private static final Pattern CODIGO_POSTAL = Pattern.compile("^\\d{5}$");
    private static final Pattern RFC = Pattern.compile("^[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern NOMBRE = Pattern.compile("^[\\p{L}][\\p{L} .'-]*$");
    private static final Pattern DECIMAL_POSITIVO = Pattern.compile("^(\\d+(\\.\\d+)?|\\.\\d+)$");

    public boolean esTextoVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public boolean esCorreoValido(String correo) {
        return !esTextoVacio(correo) && CORREO.matcher(correo.trim()).matches();
    }

    public boolean esTelefonoValido(String telefono) {
        return !esTextoVacio(telefono) && TELEFONO.matcher(telefono.trim()).matches();
    }

    public boolean esCodigoPostalValido(String cp) {
        return !esTextoVacio(cp) && CODIGO_POSTAL.matcher(cp.trim()).matches();
    }

    public boolean esRfcValido(String rfc) {
        return !esTextoVacio(rfc) && RFC.matcher(rfc.trim()).matches();
    }

    public boolean esNombreValido(String nombre) {
        return !esTextoVacio(nombre) && NOMBRE.matcher(nombre.trim()).matches();
    }

    public boolean esNumeroEntero(String texto) {
        if (esTextoVacio(texto)) {
            return false;
        }
        try {
            Integer.parseInt(texto.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean esNumeroDecimal(String texto) {
        if (esTextoVacio(texto)) {
            return false;
        }
        try {
            double d = Double.parseDouble(texto.trim());
            return !Double.isNaN(d) && !Double.isInfinite(d);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean esPositivo(double valor) {
        return valor > 0;
    }

    public boolean esFechaValida(Date fecha) {
        return fecha != null;
    }

    public boolean esSeleccionValida(Object seleccion) {
        return seleccion != null && !seleccion.toString().trim().isEmpty();
    }

    public boolean esIndiceSeleccionado(int indice) {
        return indice >= 0;
    }

    public boolean esDecimalPositivo(String texto) {
        return !esTextoVacio(texto) && DECIMAL_POSITIVO.matcher(texto.trim()).matches() && Double.parseDouble(texto.trim()) > 0;
    }

    public boolean esFechaNoPasada(LocalDate fecha) {
        return fecha != null && !fecha.isBefore(LocalDate.now());
    }

    public String limpiarNumero(String texto) {
        return texto == null ? "" : texto.replaceAll("[^0-9.]", "");
    }
}
