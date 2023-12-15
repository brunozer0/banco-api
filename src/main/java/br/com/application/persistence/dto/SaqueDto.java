package br.com.application.persistence.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SaqueDto {

    @NotBlank(message = "Valor saque é requerido")
    private double saque;


}
