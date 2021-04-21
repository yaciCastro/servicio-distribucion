package com.correo.serviciodistribucion.services;

import com.correo.serviciodistribucion.model.PaqueteDTO;
import com.correo.serviciodistribucion.model.PaqueteResponse;

public interface PaqueteService {

    PaqueteResponse findPaqueteById(Long paqueteId);


    Long createPaquete(PaqueteDTO paqueteDTO);


    void updatePaquete(Long paqueteId, PaqueteDTO paqueteDTO);


    void deletePaquete(Long paqueteId);
}
