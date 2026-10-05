package com.salesianostriana.dam.monumentos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Monumento {

    @Id
    private Long id;
    private String codigoPais;
    private String nombrePais;
    private String ciudad;
    private Double latitud;
    private Double longitud;
    private String nombre;
    private String descripcion;
    private String urlFoto;
}
