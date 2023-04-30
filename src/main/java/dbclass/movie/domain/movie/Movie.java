package dbclass.movie.domain.movie;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Table(name = "MOVIE")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_sequence")
    @SequenceGenerator(name = "movie_sequence", sequenceName = "movie_sequence", allocationSize = 1)
    @Column(name = "MOVIE_ID")
    private Long movie_id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "RELEASE_DATE", nullable = false)
    private Date release_date;

    @Column(name = "RUNNING_TIME", nullable = false)
    private int running_time;

    @Column(name = "INFO", nullable = false)
    private String info;

    @Column(name = "COUNTRY", nullable = false)
    private String country_code;

    @Column(name = "LANGUAGE")
    private String language;

    @OneToOne
    @JoinColumn(name = "POSTER", nullable = false)
    private Poster poster;

    @OneToOne
    @JoinColumn(name = "DIRECTOR", nullable = false)
    private Cast director;

    @OneToOne
    @JoinColumn(name = "RATING", nullable = false)
    private Rating rating;


}
