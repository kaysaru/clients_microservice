package kz.iceberg.clients.service.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link kz.iceberg.clients.service.entity.ClientTagEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientTagEntityDto implements Serializable {
    private long id;
    private boolean rich;
    private boolean good;
    private boolean jurface;
    private boolean charity;
    private boolean insolvent;
}