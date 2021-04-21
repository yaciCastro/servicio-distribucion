package com.correo.serviciodistribucion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class EnvioDTO {

    private Long remitenteUsuarioId;
    private Long destinatarioUsuarioId;
    private Long numeroDeSeguimiento;
}
