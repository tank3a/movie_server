package dbclass.movie.domain.movie;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ROLE")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@IdClass(RoleId.class)
@Builder
public class Role {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAST_ID")
    private Cast cast;

    @Column(name = "ROLE", nullable = false)
    private String role;

    @Column(name = "STARRING", nullable = false)
    private boolean starring;
}
