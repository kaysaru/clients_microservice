package kz.iceberg.clients.service.service;

import jakarta.transaction.Transactional;
import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.entity.TimelineEntity;
import kz.iceberg.clients.service.entity.dto.ClientEntityDto;
import kz.iceberg.clients.service.entity.dto.ClientLabelEntityDto;
import kz.iceberg.clients.service.entity.dto.ClientTagEntityDto;
import kz.iceberg.clients.service.repository.ClientRepository;
import kz.iceberg.clients.service.repository.MergeRepository;
import kz.iceberg.clients.service.wrapper.enums.TimelineEvent;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MergeService {
    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final MergeRepository mergeRepository;
    private final ClientRepository clientRepository;

    MergeService(final MergeRepository mergeRepository, final ClientRepository clientRepository) {
        this.mergeRepository = mergeRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public List<TimelineEntity> list() {
        return this.mergeRepository.findAll();
    }

    @Transactional
    public boolean fetchAndMerge(Long targetId, List<Long> sources) {
        var mapper = new ClientEntityMapperImpl();

        ClientEntity target = this.clientRepository.findById(targetId).orElseThrow();
        List<ClientEntity> sourceEntities = this.clientRepository.findAllById(sources);

        var targetDto = mapper.toDto(target);
        List<ClientEntityDto> sourceEntitiesDto = new ArrayList<>();
        for (ClientEntity s : sourceEntities) {
            var a = mapper.toDto(s);
            sourceEntitiesDto.add(a);
        }

        try {
            mapper.partialUpdate(merge(targetDto, sourceEntitiesDto), target);
        } catch (Exception e) {
            LOGGER.error("something went wrong", e);
            return false;
        }
        generateTimelines(target, sourceEntities);
        clientRepository.save(target);
        clientRepository.flush();
        return true;
    }

    private void generateTimelines(ClientEntity primaryUser, List<ClientEntity> secondaryUserList) {
        for (ClientEntity entity : secondaryUserList) {
            var targetClientTimeline = new TimelineEntity();
            targetClientTimeline.setClient(primaryUser.getId());
            targetClientTimeline.setAuthor(100000); //TODO replace with actual userId
            targetClientTimeline.setDate(LocalTime.now().toSecondOfDay());
            targetClientTimeline.setObject(Math.toIntExact(entity.getId()));
            targetClientTimeline.setEvent(TimelineEvent.MERGE);

            var sourceClientTimeline = new TimelineEntity();
            sourceClientTimeline.setClient(entity.getId());
            sourceClientTimeline.setAuthor(100000); //TODO replace with actual userId
            sourceClientTimeline.setDate(LocalTime.now().toSecondOfDay());
            sourceClientTimeline.setObject(Math.toIntExact(primaryUser.getId()));
            sourceClientTimeline.setEvent(TimelineEvent.MERGE);


            mergeRepository.save(targetClientTimeline);
            mergeRepository.save(sourceClientTimeline);
        }
    }

    /**
     * In-place merge, it will detach array elements from their parents and attach them to target
     */
    private ClientEntityDto merge(ClientEntityDto target, List<ClientEntityDto> sourceClients) {
//        if (target.getMore() == null && sourceClients.stream().anyMatch(e -> e.getMore() != null)) {
//            target.setMore(new ClientMoreEntity());
//        } //TODO uncomment if things get wrong
        try {
            for (ClientEntityDto source : sourceClients) {
                target.setName(target.getName() + " / " + source.getName());

                target.getEmails().addAll(source.getEmails());
                target.getAddresses().addAll(source.getAddresses());
                target.getPhones().addAll(source.getPhones());

                if (source.getMore() != null) {
                    target.getMore().setNotes(target.getMore().getNotes() + "\n\n" + source.getMore().getNotes());
                    target.getMore().setRate(
                            BigDecimal.valueOf((target.getMore().getRate() + source.getMore().getRate()) / 2)
                                    .setScale(3, RoundingMode.HALF_UP)
                                    .doubleValue()
                    );

                    ClientTagEntityDto targetTags = target.getMore().getTags();
                    ClientTagEntityDto sourceTags = source.getMore().getTags();
                    if (sourceTags != null && targetTags != null) {
                        targetTags.setCharity(targetTags.isCharity() || sourceTags.isCharity());
                        targetTags.setGood(targetTags.isGood() || sourceTags.isGood());
                        targetTags.setInsolvent(targetTags.isInsolvent() || sourceTags.isInsolvent());
                        targetTags.setJurface(targetTags.isJurface() || sourceTags.isJurface());
                        targetTags.setRich(targetTags.isRich() || sourceTags.isRich());
                    }

                    ClientLabelEntityDto targetLabels = target.getMore().getLabels();
                    ClientLabelEntityDto sourceLabels = source.getMore().getLabels();
                    if (sourceLabels != null) {
                        targetLabels.setBad(targetLabels.isBad() || sourceLabels.isBad());
                        targetLabels.setBan(targetLabels.isBan() || sourceLabels.isBan());
                        targetLabels.setSupplier(targetLabels.isSupplier() || sourceLabels.isSupplier());
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error during merging into id " + target.getId(), e);
            throw new RuntimeException(e);
        }
        LOGGER.info(target.toString());
        return target;
    }
}
