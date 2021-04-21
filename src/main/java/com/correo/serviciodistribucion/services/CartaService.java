package com.correo.serviciodistribucion.services;

import com.correo.serviciodistribucion.model.CartaDTO;
import com.correo.serviciodistribucion.model.CartaResponse;

public interface CartaService {

    CartaResponse findCartaById(Long cartaId);


    Long createCarta(CartaDTO cartaDTO);


    void updateCarta(Long cartaId, CartaDTO cartaDTO);


    void deleteCarta(Long cartaId);

}
