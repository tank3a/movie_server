package dbclass.movie.dto.movie;

import dbclass.movie.domain.movie.Cast;
import dbclass.movie.domain.movie.Movie;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleDTO {

    private Movie movie;
    private Cast cast;
    private String role;
    private boolean starring;
}
