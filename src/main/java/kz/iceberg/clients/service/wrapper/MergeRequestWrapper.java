package kz.iceberg.clients.service.wrapper;

import lombok.Data;

import java.util.List;

@Data
public class MergeRequestWrapper {
    private Long primary;
    private List<Long> secondaries;
}