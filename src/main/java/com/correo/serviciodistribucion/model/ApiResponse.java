package com.correo.serviciodistribucion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {

    private final Boolean success;
    private final String message;
}