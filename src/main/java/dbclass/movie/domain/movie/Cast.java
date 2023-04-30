package dbclass.movie.domain.movie;

import dbclass.movie.domain.Image;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Table(name = "CAST")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SuperBuilder
public class Cast extends Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cast_sequence")
    @SequenceGenerator(name = "cast_sequence", sequenceName = "cast_sequence", allocationSize = 1)
    @Column(name = "CAST_ID")
    private Long cast_id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "BIRTHDATE", nullable = false)
    private Date birthDate;

    @Column(name = "NATIONALITY", nullable = false)
    private int nationality;

    @Column(name = "INFO", nullable = false)
    private String info;

}
