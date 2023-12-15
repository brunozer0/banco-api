package br.com.application.persistence.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepositDto {

    @NotBlank(message = "valor deposito é requerido")
    private Double valor;

}
