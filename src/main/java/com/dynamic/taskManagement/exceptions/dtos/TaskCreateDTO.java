package com.dynamic.taskManagement.exceptions.dtos;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskCreateDTO {
    @NotBlank(message = "Este campo é Obrigatório.")
    @Length(message = "O titulo deve conter pelo menos 5 Caracteres.")
    private String title;

    @NotBlank(message = "Este Campo é Obrigatório.")
    @Length(min = 5, message = "O status deve conter pelo menos 5 Caracteres.")
    private String status;

    @NotBlank(message = "Este Campo é Obrigatório.")
    @Length(min = 5, message = "O descrição deve conter pelo menos 5 Caracteres.")
    private String description;

}
