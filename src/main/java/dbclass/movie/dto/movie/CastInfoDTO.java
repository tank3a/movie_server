package dbclass.movie.dto.movie;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CastInfoDTO {
    private Long castId;
    private String name;
    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")
    private String birthDate;
    private String nationality;
    private String info;
    private MultipartFile profileImage;
}
