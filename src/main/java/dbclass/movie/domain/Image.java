package dbclass.movie.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@AllArgsConstructor
public abstract class Image {

    @Column(name = "UUID", nullable = false)
    private String uuid;
    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;
    @Column(name = "FILE_URL", nullable = false)
    private String fileUrl;
}
