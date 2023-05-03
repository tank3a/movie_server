package dbclass.movie.dto.movie;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieDTO {

    private Long movieId;
    private String title;
    private Date releaseDate;
    private int runningTime;
    private String info;
    private String countryCode;
    private String language;
    private PosterDTO poster;
    private CastInMovieDTO director;
    private RatingDTO rating;
}
