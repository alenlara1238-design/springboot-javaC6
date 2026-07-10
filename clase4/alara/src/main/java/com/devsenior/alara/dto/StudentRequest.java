package com.devsenior.alara.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentRequest(
    @NotBlank(message = "El nombre es obligatorio")
    @Size(
        min = 2,
        max = 50,
        message = "El nombre debe tener entre 2 y 50 caracteres"
    )
    String name,

    @Min(
        value = 12,
        message = "La edad minima es de 12 años"
    )
    @Max(
        value = 160,
        message ="La edad maxima es de 160 años"
    )
    Integer age,

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ingrear un correo válido")
    String email
) {
}
