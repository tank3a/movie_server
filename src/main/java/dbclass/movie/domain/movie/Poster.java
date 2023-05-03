package dbclass.movie.domain.movie;

import dbclass.movie.domain.Image;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "POSTER")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SuperBuilder
public class Poster extends Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poster_sequence")
    @SequenceGenerator(name = "poster_sequence", sequenceName = "poster_sequence", allocationSize = 1)
    @Column(name = "POSTER_ID")
    private Long posterId;
}
