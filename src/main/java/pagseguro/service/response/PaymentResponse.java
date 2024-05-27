package pagseguro.service.response;


/**
 * Detalhes sobre uma resposta de confirmação
 * de um pagamento de um pedido.
 *
 * @author Manoel Campos da Silva Filho
 */
public record PaymentResponse (
    String code,
    String message,
    String reference){
}