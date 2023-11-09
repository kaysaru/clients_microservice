package kz.iceberg.clients.service.graphql.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kz.iceberg.clients.service.wrapper.enums.Ascension;
import lombok.*;

@Data
@JsonFormat(shape = JsonFormat.Shape.ANY)
@JsonPropertyOrder({"columnName", "order"})
public class SortEntry {
    String columnName;
    Ascension order;
}
