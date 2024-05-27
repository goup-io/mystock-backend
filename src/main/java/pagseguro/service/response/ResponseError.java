package pagseguro.service.response;


import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

/**
 * Erro no processamento de uma requisição à API do PagSeguro.
 * @author Manoel Campos da Silva Filho
 * @see #getError_messages()
 * @see pagseguro.data.Customer
 * @see pagseguro.service.PixOrderService
 */
@Getter @Setter
public class ResponseError extends RuntimeException {
    private List<ErrorMessage> error_messages = new LinkedList<>();
}
