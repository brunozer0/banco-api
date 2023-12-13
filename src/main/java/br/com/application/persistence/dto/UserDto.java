package br.com.application.persistence.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDto {

    @NotBlank(message = "name cannot be null")


    String name;
    Integer age;
    private String phone;
    private String adress;

}
