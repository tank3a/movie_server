package dbclass.movie.domain.movie;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "RATING")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_sequence")
    @SequenceGenerator(name = "rating_sequence", sequenceName = "rating_sequence", allocationSize = 1)
    @Column(name = "RATING_ID")
    private int rating_id;

    @Column(name = "NAME", nullable = false)
    private String name;
}
