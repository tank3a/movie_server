package dbclass.movie.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageDTO {

    private String uuid;
    private String fileName;
    private String fileUrl;
}
