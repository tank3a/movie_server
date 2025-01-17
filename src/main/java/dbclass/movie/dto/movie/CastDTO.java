package dbclass.movie.dto.movie;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CastDTO {

    private Long castId;
    private String name;
    private Date birthDate;
    private String nationality;
    private String info;
    private String uuid;
    private String fileName;
    private String fileUrl;
}
