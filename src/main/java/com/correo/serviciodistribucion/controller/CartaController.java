package com.correo.serviciodistribucion.controller;

import com.correo.serviciodistribucion.model.ApiResponse;
import com.correo.serviciodistribucion.model.CartaDTO;
import com.correo.serviciodistribucion.model.CartaResponse;
import com.correo.serviciodistribucion.services.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cartas")
public class CartaController {

    private final CartaService cartaService;

    @Autowired
    public CartaController(CartaService cartaService) { this.cartaService = cartaService; }

    @GetMapping(value = "/{cartaid}")
    public ResponseEntity<CartaDTO> getCarta(@PathVariable("cartaid") Long cartaId) {

        CartaResponse cartaResponse = cartaService.findCartaById(cartaId);

        return ResponseEntity.ok(cartaResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createDireccion(@RequestBody CartaDTO cartaDTO) {

        Long cartaResponseId = cartaService.createCarta(cartaDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/carta/{cartaId}")
                .buildAndExpand(cartaResponseId).toUri();

        return ResponseEntity.created(location).body(
                new ApiResponse(true, "Carta creada correctamente"));

    }

    @PutMapping
    public ResponseEntity<CartaDTO> updateDireccion(@RequestBody CartaDTO cartaDTO,
                                                        @RequestParam("cartaId") Long cartaId) {

        cartaService.updateCarta(cartaId, cartaDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<CartaDTO> deleteDireccion(@RequestParam("cartaId") Long cartaId) {
        cartaService.deleteCarta(cartaId);
        return ResponseEntity.ok().build();
    }
}
