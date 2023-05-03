package dbclass.movie.dto.movie;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GenreDTO {

    private Long genreId;
    private String name;
}
