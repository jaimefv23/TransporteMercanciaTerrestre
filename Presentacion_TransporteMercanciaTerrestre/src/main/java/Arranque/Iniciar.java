package Arranque;

import Control.ControlMercanciaTerrestre;
import Coordinador.CoordinadorAplicacion;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jaime
 */
public class Iniciar {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CoordinadorAplicacion coordinador = new CoordinadorAplicacion();
            ControlMercanciaTerrestre control = new ControlMercanciaTerrestre(coordinador);
            coordinador.setControlParaVistas(control);
            coordinador.mostrarInicioSesion();
        });
    }
}
