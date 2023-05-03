package dbclass.movie.dto.user;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerInfoToClientDTO {

    private String name;
    private String loginId;
    private String nickname;
    private Date birthdate;
    private int gender;
    private String phoneNo;
    private String email;
}
