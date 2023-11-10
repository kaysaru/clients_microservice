package kz.iceberg.clients.service.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link kz.iceberg.clients.service.entity.ClientLabelEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientLabelEntityDto implements Serializable {
    private long id;
    private boolean bad;
    private boolean supplier;
    private boolean ban;
}