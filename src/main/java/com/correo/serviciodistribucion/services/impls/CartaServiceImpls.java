package com.correo.serviciodistribucion.services.impls;

import com.correo.serviciodistribucion.exceptions.ResourceNotFoundException;
import com.correo.serviciodistribucion.model.CartaDTO;
import com.correo.serviciodistribucion.model.CartaResponse;
import com.correo.serviciodistribucion.services.CartaService;
import com.correo.serviciodistribucionmodel.model.dbentities.Carta;
import com.correo.serviciodistribucionmodel.repositories.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaServiceImpls implements CartaService {

    private final CartaRepository cartaRepository;

    @Autowired
    public CartaServiceImpls(CartaRepository cartaRepository) {
        this.cartaRepository = cartaRepository;
    }

    @Override
    public CartaResponse findCartaById(Long cartaId) {
        Carta carta = cartaRepository.findById(cartaId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro la carta con id: " + cartaId));

        return CartaResponse.builder()
                .id(carta.getId())
                .remitenteUsuarioId(carta.getRemitenteUsuarioId())
                .destinatarioUsuarioId(carta.getDestinatarioUsuarioId())
                .numeroDeSeguimiento(carta.getNumeroDeSeguimiento())
                .build();
    }

    @Override
    public Long createCarta(CartaDTO cartaDTO) {

        Carta carta = Carta.builder()
                .remitenteUsuarioId(cartaDTO.getRemitenteUsuarioId())
                .destinatarioUsuarioId(cartaDTO.getDestinatarioUsuarioId())
                .numeroDeSeguimiento(cartaDTO.getNumeroDeSeguimiento())
                .build();
        Carta cartaResponse = cartaRepository.save(carta);

        return cartaResponse.getId();
    }

    @Override
    public void updateCarta(Long cartaId, CartaDTO cartaDTO) {
        Carta carta = cartaRepository.findById(cartaId)
                .orElseThrow(() -> new ResourceNotFoundException("Carta no encontrada con id: " + cartaId));

        if (cartaDTO.getDestinatarioUsuarioId() != null) {
            carta.setDestinatarioUsuarioId(cartaDTO.getDestinatarioUsuarioId());
        }

        cartaRepository.save(carta);
    }

    @Override
    public void deleteCarta(Long cartaId) {
        cartaRepository.deleteById(cartaId);
    }
}
