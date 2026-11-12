/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import Almacen.AlmacenTemporal;
import DTOS.DecisionClienteDTO;
import java.time.LocalDate;

/**
 *
 * @author Jaime
 */
public class DecisionClienteBO implements IDecisionClienteBO {
    
    private final AlmacenTemporal almacen;

    DecisionClienteBO() {
        this.almacen = AlmacenTemporal.getInstancia();
    }

    @Override
    public String generarId() {
        return almacen.generarIdDecision();
    }

    @Override
    public DecisionClienteDTO guardar(DecisionClienteDTO decision) {
        if (decision == null) {
            throw new IllegalArgumentException("La decisión no puede ser nula");
        }
        if (decision.getTipoDecision() == null || decision.getTipoDecision().isBlank()) {
            throw new IllegalArgumentException("La decisión debe tener tipoDecision");
        }
        if (decision.getIdDecision() == null || decision.getIdDecision().isBlank()) {
            decision.setIdDecision(generarId());
        }
        if (decision.getFechaDecision() == null) {
            decision.setFechaDecision(LocalDate.now());
        }
        return almacen.registrarDecision(decision);
    }

}
