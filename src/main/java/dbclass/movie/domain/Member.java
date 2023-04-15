package dbclass.movie.domain;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_sequence")
    @SequenceGenerator(name = "member_sequence", sequenceName = "member_sequence", allocationSize = 1)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 4)
    private String name;
}
