package com.correo.serviciodistribucion.controller;

import com.correo.serviciodistribucion.model.*;
import com.correo.serviciodistribucion.services.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/paquetes")
public class PaqueteController {
    private final PaqueteService paqueteService;

    @Autowired
    public PaqueteController(PaqueteService paqueteService) { this.paqueteService = paqueteService; }

    @GetMapping(value = "/{paqueteId}")
    public ResponseEntity<PaqueteDTO> getPaquete(@PathVariable("paqueteId") Long paqueteId) {

        PaqueteResponse paqueteResponse = paqueteService.findPaqueteById(paqueteId);

        return ResponseEntity.ok(paqueteResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createPaquete(@RequestBody PaqueteDTO paqueteDTO) {

        Long paqueteResponseId = paqueteService.createPaquete(paqueteDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/paquete/{paqueteId}")
                .buildAndExpand(paqueteResponseId).toUri();

        return ResponseEntity.created(location).body(
                new ApiResponse(true, "Paquete creado correctamente"));

    }

    @PutMapping
    public ResponseEntity<PaqueteDTO> updatePaquete(@RequestBody PaqueteDTO paqueteDTO,
                                                    @RequestParam("paqueteId") Long paqueteId) {

        paqueteService.updatePaquete(paqueteId, paqueteDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<CartaDTO> deleteDireccion(@RequestParam("paqueteId") Long paqueteId) {
        paqueteService.deletePaquete(paqueteId);
        return ResponseEntity.ok().build();
    }
}
