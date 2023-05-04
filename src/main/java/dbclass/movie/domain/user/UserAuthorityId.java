package dbclass.movie.domain.user;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthorityId implements Serializable {
    private String loginId;
    private String authority;
}
