package kz.iceberg.clients.service.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link kz.iceberg.clients.service.entity.ClientPhonesEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientPhonesEntityDto implements Serializable {
    private long id;
    private String phone;
    private boolean main;
    private boolean whatsapp;
    private boolean viber;
}