package funtional2.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "TB_APPLE")
@NoArgsConstructor(access = PROTECTED)
public class AppleJpaEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "SOME_VALUE1")
    private String someValue1;

    @Column(name = "SOME_VALUE2")
    private Integer someValue2;

    @Column(name = "SOME_VALUE3")
    private LocalDateTime someValue3;

    @Builder
    public AppleJpaEntity(Long id, String someValue1, Integer someValue2, LocalDateTime someValue3) {
        this.id = id;
        this.someValue1 = someValue1;
        this.someValue2 = someValue2;
        this.someValue3 = someValue3;
    }

    public static void main(String[] args) {

    }
}