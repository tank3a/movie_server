package dbclass.movie.domain.movie;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleId implements Serializable {
    private Movie movie;
    private Cast cast;
}
