package com.example.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserRequest  {

    @Schema(description = "Имя пользователя", example = "Петров Иван")
    public String name;
    @Schema(description = "Почта пользователя", example = "ivan@gmail.com")
    public String email;
}
