package kz.iceberg.clients.service.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.iceberg.clients.service.entity.ClientMoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;

/**
 * DTO for {@link ClientMoreEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientMoreEntityDto implements Serializable {
    private long id;
    private String notes;
    private double rate;
    private String identifier;
    private Collection<ClientLabelEntityDto> labels;
    private ClientCompanyEntityDto company;
    private Collection<ClientTagEntityDto> tags;
}