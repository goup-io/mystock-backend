package com.goup.dtos.estoque;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ReqETPeQuantidade(@NotNull Integer idEtp, @Positive Integer quantidade) {
}
