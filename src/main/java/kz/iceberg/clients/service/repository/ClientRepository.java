package kz.iceberg.clients.service.repository;

import jakarta.transaction.Transactional;
import kz.iceberg.clients.service.entity.ClientEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    /**
     * <h2>This JPA expression will be converted to something like this</h2><br>
     * <code>
     * select<br>
     * *<br>
     * from<br>
     * sc_iceberg.client c1_0<br>
     * left join sc_iceberg.client_emails e1_0 on c1_0.id = e1_0.client<br>
     * left join sc_iceberg.client_addresses a1_0 on c1_0.id = a1_0.client<br>
     * left join sc_iceberg.client_phones p1_0 on c1_0.id = p1_0.client<br>
     * where<br>
     * upper(c1_0.name) like upper('%Matthew%') escape '\'<br>
     * or upper(e1_0.email) like upper(' % Matthew % ') escape ' \ '<br>
     * or upper(a1_0.address) like upper(' % Matthew % ') escape ' \ '<br>
     * or upper(p1_0.phone) like upper(' % Matthew % ') escape ' \ '<br>
     * order by<br>
     * c1_0.name<br>
     * offset<br>
     * 0 limit 5</code>
     *
     * @param name              will search for name
     * @param emails_email      will search for email
     * @param addresses_address will search for addresses
     * @param phones_phone      will search for phones
     * @param pageable          Pageable instance for sorting and paging
     * @return <ClientEntity>List of ClientEntity</ClientEntity>
     */
    @Transactional
    List<ClientEntity> findByNameContainingIgnoreCaseOrEmailsEmailContainingIgnoreCaseOrAddressesAddressContainingIgnoreCaseOrPhonesPhoneContainingIgnoreCase(
            String name,
            String emails_email,
            String addresses_address,
            String phones_phone,
            Pageable pageable);
}
