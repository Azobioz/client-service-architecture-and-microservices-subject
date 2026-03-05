package com.example.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "Необходимо указать имя")
    private String name;

    @Email(message = "Email должен быть валидным")
    @NotBlank(message = "Необходимо указать email")
    private String email;

    @NotNull(message = "Необходимо указать возраст")
    private Integer age;
}