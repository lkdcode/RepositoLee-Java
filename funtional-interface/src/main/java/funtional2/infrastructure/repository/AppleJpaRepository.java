package funtional2.infrastructure.repository;

import funtional2.infrastructure.entity.AppleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppleJpaRepository extends JpaRepository<AppleJpaEntity, Long> {
}
