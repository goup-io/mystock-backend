package pagseguro.data;

import lombok.NonNull;

/**
 * Informações de entrega de uma venda (pedido) para um cliente
 * @author Manoel Campos da Silva Filho
 * @see pagseguro.service.PixOrder
 */
public record Shipping(@NonNull Address address) {
    public static Shipping empty(){
        return new Shipping(Address.empty());
    }
}
