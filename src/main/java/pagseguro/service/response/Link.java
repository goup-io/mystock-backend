package pagseguro.service.response;

import lombok.NonNull;

/**
 * @author Manoel Campos da Silva Filho
 * @see pagseguro.service.QrCode
 */
public record Link(@NonNull String rel, @NonNull String href, @NonNull String media, @NonNull String type) {
}
