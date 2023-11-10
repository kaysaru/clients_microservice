package kz.iceberg.clients.service.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;

/**
 * DTO for {@link kz.iceberg.clients.service.entity.ClientEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientEntityDto implements Serializable {
    private Long id;
    private String name;
    private boolean active;
    private Collection<ClientAddressesEntityDto> addresses;
    private Collection<ClientEmailsEntityDto> emails;
    private ClientMoreEntityDto more;
    private Collection<ClientPhonesEntityDto> phones;
}