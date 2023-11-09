package kz.iceberg.clients.service.graphql.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@JsonFormat(shape = JsonFormat.Shape.ANY)
@JsonPropertyOrder({"offset", "limit"})
public class PaginationEntry {
    private int offset;
    private int limit;
}
