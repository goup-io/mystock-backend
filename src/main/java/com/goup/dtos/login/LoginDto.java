package com.goup.dtos.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDto(@NotNull @NotBlank String user, @NotNull @NotBlank String senha) {
}
