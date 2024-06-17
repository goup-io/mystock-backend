package com.goup.dtos.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateLoginReq(@NotNull @NotBlank String username,@NotNull @NotBlank String senha) {
}
