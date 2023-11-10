package kz.iceberg.clients.service.repository;

import kz.iceberg.clients.service.entity.TimelineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;

public interface MergeRepository extends JpaRepository<TimelineEntity, Long> {
}
