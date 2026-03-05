package com.example.app.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Schema(description = "Модель пользователя")
public class User {

    @Schema(description = "Уникальный идентификатор пользователя", example = "123")
    private Long id;

    @Schema(description = "Имя пользователя", example = "Петров Иван")
    private String name;

    @Schema(description = "Почта пользователя", example = "ivan@gmail.com")
    private String email;

}