package kz.iceberg.clients.service;

import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.repository.ClientRepository;
import kz.iceberg.clients.service.wrapper.FilterWrapper;
import kz.iceberg.clients.service.wrapper.enums.Ascension;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JPATests {
    @Autowired
    private ClientRepository clientRepository;
    private static final Logger LOG = LoggerFactory.getLogger(JPATests.class);

    @Test
    public void whenCalledSave_thenCorrectNumberOfUsers(@Autowired DataSource dataSource) {
        //clientRepository.save(new ClientEntity());
        try {
            dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<ClientEntity> clients = (List<ClientEntity>) clientRepository.findAllById(List.of(1L, 2L));

        assertThat(clients.size()).isGreaterThan(1);
    }

    @Test
    public void test() {
        int a = 1;
        assertThat(a).isEqualTo(1);
    }
}
