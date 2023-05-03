package dbclass.movie.dto.movie;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RatingDTO {

    private int ratingId;
    private String name;
    private int minAge;
}
