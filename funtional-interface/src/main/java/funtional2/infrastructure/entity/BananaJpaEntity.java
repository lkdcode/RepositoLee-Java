package funtional2.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "TB_BANANA")
@NoArgsConstructor(access = PROTECTED)
public class BananaJpaEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "SOME_VALUE1")
    private String someValue1;

    @Column(name = "SOME_VALUE2", precision = 19, scale = 5)
    private BigDecimal someValue2;

    @Column(name = "SOME_VALUE3")
    private LocalDate someValue3;

    @Builder
    public BananaJpaEntity(Long id, String someValue1, BigDecimal someValue2, LocalDate someValue3) {
        this.id = id;
        this.someValue1 = someValue1;
        this.someValue2 = someValue2;
        this.someValue3 = someValue3;
    }
}