package pagseguro.data;

/**
 * Informações sobre o titular de um método de pagamento
 * utilizado para pagar um determinado pedido.
 *
 * @author Manoel Campos da Silva Filho
 */
public record Holder(String name, String tax_id) {
}
