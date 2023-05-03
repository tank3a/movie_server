package dbclass.movie.dto.movie;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PosterDTO {

    private Long posterId;
    private String uuid;
    private String fileName;
    private String fileUrl;
}
