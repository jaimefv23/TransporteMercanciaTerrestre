/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import BOs.ClienteBO;
import BOs.CotizacionBO;
import BOs.DecisionClienteBO;
import BOs.EvaluacionTrasladoBO;
import BOs.IClienteBO;
import BOs.ICotizacionBO;
import BOs.IDecisionClienteBO;
import BOs.IEvaluacionTrasladoBO;
import BOs.IMercanciaBO;
import BOs.IPropuestaOperativaBO;
import BOs.ISolicitudTransporteBO;
import BOs.ITipoUnidadBO;
import BOs.IUsuarioBO;
import BOs.MercanciaBO;
import BOs.PropuestaOperativaBO;
import BOs.SolicitudTransporteBO;
import BOs.TipoUnidadBO;
import BOs.UsuarioBO;

/**
 *
 * @author Jaime
 */
public class FabricaBO {
    
    private FabricaBO() {
    }

    public static IUsuarioBO crearUsuarioBO() {
        return new UsuarioBO();
    }

    public static IClienteBO crearClienteBO() {
        return new ClienteBO();
    }

    public static ISolicitudTransporteBO crearSolicitudTransporteBO() {
        return new SolicitudTransporteBO();
    }

    public static IMercanciaBO crearMercanciaBO() {
        return new MercanciaBO();
    }

    public static IEvaluacionTrasladoBO crearEvaluacionTrasladoBO() {
        return new EvaluacionTrasladoBO();
    }

    public static ITipoUnidadBO crearTipoUnidadBO() {
        return new TipoUnidadBO();
    }

    public static IPropuestaOperativaBO crearPropuestaOperativaBO() {
        return new PropuestaOperativaBO();
    }

    public static ICotizacionBO crearCotizacionBO() {
        return new CotizacionBO();
    }

    public static IDecisionClienteBO crearDecisionClienteBO() {
        return new DecisionClienteBO();
    }

}
