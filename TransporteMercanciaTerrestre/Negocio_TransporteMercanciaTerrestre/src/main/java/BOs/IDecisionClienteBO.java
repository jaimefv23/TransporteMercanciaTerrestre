/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs;

import DTOS.DecisionClienteDTO;

/**
 *
 * @author Jaime
 */
public interface IDecisionClienteBO {
    
    public abstract String generarId();

    public abstract DecisionClienteDTO guardar(DecisionClienteDTO decision);

}
