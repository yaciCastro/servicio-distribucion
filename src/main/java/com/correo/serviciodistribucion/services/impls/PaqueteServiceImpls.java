package com.correo.serviciodistribucion.services.impls;

import com.correo.serviciodistribucion.exceptions.ResourceNotFoundException;
import com.correo.serviciodistribucion.model.PaqueteDTO;
import com.correo.serviciodistribucion.model.PaqueteResponse;
import com.correo.serviciodistribucion.services.PaqueteService;
import com.correo.serviciodistribucionmodel.model.dbentities.Paquete;
import com.correo.serviciodistribucionmodel.repositories.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaqueteServiceImpls implements PaqueteService {

    private final PaqueteRepository paqueteRepository;

    @Autowired
    public PaqueteServiceImpls(PaqueteRepository paqueteRepository) {
        this.paqueteRepository = paqueteRepository;
    }

    @Override
    public PaqueteResponse findPaqueteById(Long paqueteId) {
        Paquete paquete = paqueteRepository.findById(paqueteId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el paquete con id: " + paqueteId));

        return PaqueteResponse.builder()
                .id(paquete.getId())
                .pesoEnGramos(paquete.getPesoEnGramos())
                .anchoEnCentimetros(paquete.getAnchoEnCentimentros())
                .altoEnCentimetros(paquete.getAltoEnCentimetros())
                .remitenteUsuarioId(paquete.getRemitenteUsuarioId())
                .destinatarioUsuarioId(paquete.getDestinatarioUsuarioId())
                .numeroDeSeguimiento(paquete.getNumeroDeSeguimiento())
                .build();
    }

    @Override
    public Long createPaquete(PaqueteDTO paqueteDTO) {

        Paquete paquete1 = Paquete.builder()
                .pesoEnGramos(paqueteDTO.getPesoEnGramos())
                .anchoEnCentimentros(paqueteDTO.getAnchoEnCentimetros())
                .altoEnCentimetros(paqueteDTO.getAltoEnCentimetros())
                .remitenteUsuarioId(paqueteDTO.getRemitenteUsuarioId())
                .destinatarioUsuarioId(paqueteDTO.getDestinatarioUsuarioId())
                .numeroDeSeguimiento(paqueteDTO.getNumeroDeSeguimiento())
                .build();
        Paquete paqueteResponse = paqueteRepository.save(paquete1);

        return paqueteResponse.getId();
    }

    @Override
    public void updatePaquete(Long paqueteId, PaqueteDTO paqueteDTO) {

        Paquete paquete = paqueteRepository.findById(paqueteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paquete no encontrado con id: " + paqueteId));

        if (paqueteDTO.getPesoEnGramos() != null) {
            paquete.setPesoEnGramos(paqueteDTO.getPesoEnGramos());
        }

        if (paqueteDTO.getAnchoEnCentimetros() != null) {
            paquete.setAnchoEnCentimentros(paqueteDTO.getAnchoEnCentimetros());
        }

        if (paqueteDTO.getAltoEnCentimetros() != null) {
            paquete.setAltoEnCentimetros(paqueteDTO.getAltoEnCentimetros());
        }

        if (paqueteDTO.getDestinatarioUsuarioId() != null) {
            paquete.setDestinatarioUsuarioId(paqueteDTO.getDestinatarioUsuarioId());
        }

        paqueteRepository.save(paquete);

    }

    @Override
    public void deletePaquete(Long paqueteId) { paqueteRepository.deleteById(paqueteId); }

}
