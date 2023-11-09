package kz.iceberg.clients.service.wrapper;

import kz.iceberg.clients.service.graphql.input.PaginationEntry;
import kz.iceberg.clients.service.graphql.input.SortEntry;
import lombok.*;
import kz.iceberg.clients.service.wrapper.enums.Subject;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilterWrapper {
    private List<Integer> ids;
    private PaginationEntry pagination;
    private String search;
    private SortEntry sort;
    private Subject company;

    public String tableNameAdapter(String columnName) {
        return switch (columnName) {
            case "phone" -> "phones_phone";
            case "contact" -> "more_company_name";
            case "email" -> "emails_email";
            case "address" -> "addresses_address";
            case "notes" -> "more_notes";
            default -> "name";
        };
    }
}
