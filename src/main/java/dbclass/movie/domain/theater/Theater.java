package dbclass.movie.domain.theater;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "THEATER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<Seat> seats = new ArrayList<>();

    @Builder
    public Theater(Long theaterId, String name, String type, int floor) {
        this.theaterId = theaterId;
        this.name = name;
        this.type = type;
        this.floor = floor;
    }
}
