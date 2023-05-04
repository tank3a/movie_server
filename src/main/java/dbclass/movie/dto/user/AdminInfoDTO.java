package dbclass.movie.dto.user;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminInfoDTO {

    private String name;
    private String loginId;
    private String password;
}
