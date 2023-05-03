package dbclass.movie.domain.movie;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "GENRE")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_sequence")
    @SequenceGenerator(name = "genre_sequence", sequenceName = "genre_sequence", allocationSize = 1)
    @Column(name = "GENRE_ID")
    private Long genreId;

    private String name;
}
