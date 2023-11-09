package kz.iceberg.clients.service.wrapper;

import com.google.common.collect.ImmutableTable;
import lombok.*;
import kz.iceberg.clients.service.wrapper.enums.Subject;
import kz.iceberg.clients.service.wrapper.enums.Ascension;

import java.util.List;
import java.util.Map.Entry;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilterWrapper {
    private List<Integer> ids;
    private Entry<Integer, Integer> pagination;
    private String search;
    private Entry<String, Ascension> sort;
    private Subject company;

    public String tableNameAdapter(String columnName) {
        return switch (columnName) {
            case "name" -> "client_name";
            case "phone" -> "numbers_phone";
            case "contact" -> "company_name";
            case "email" -> "emails_email";
            case "address" -> "addresses_address";
            case "notes" -> "more_notes";
            default -> "123";
        };
    }
}
