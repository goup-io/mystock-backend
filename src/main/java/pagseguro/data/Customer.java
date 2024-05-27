package pagseguro.data;

import lombok.NonNull;
import pagseguro.Util;

import java.util.LinkedList;
import java.util.List;

/**
 * Cliente para quem está sendo feito uma venda.
 * @author Manoel Campos da Silva Filho
 * @param tax_id CPF/CNPJ do cliente.
 * @see pagseguro.service.PixOrder
 */
public record Customer(@NonNull String name, @NonNull String email, @NonNull String tax_id, List<Phone> phones) {
    public Customer(String name, String email, String tax_id) {
        this(name, email, tax_id, new LinkedList<>());
    }

    public Customer(@NonNull String name, @NonNull String email, @NonNull String tax_id, List<Phone> phones) {
        this.name = name;
        this.email = email;
        this.tax_id = tax_id;
        this.phones = Util.getList(phones);
    }
}
