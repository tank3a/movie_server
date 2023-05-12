package dbclass.movie.domain.theater;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "THEATER")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "theater_sequence")
    @SequenceGenerator(name = "theater_sequence", sequenceName = "theater_sequence", allocationSize = 1)
    @Column(name = "THEATER_ID")
    private Long theaterId;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "FLOOR", nullable = false)
    private int floor;
}
