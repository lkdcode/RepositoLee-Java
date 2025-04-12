package funtional2.infrastructure.repository;

import funtional2.infrastructure.entity.BananaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BananaJpaRepository extends JpaRepository<BananaJpaEntity, Long> {
}
