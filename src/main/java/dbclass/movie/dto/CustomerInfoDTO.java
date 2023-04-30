package dbclass.movie.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerInfoDTO {

    private String name;
    private String loginId;
    private String password;
    private String nickname;
    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message = "생일 형식은 yyyy-MM-dd로 지정해야 합니다.")
    private String birthdate;
    private int gender;
    private String phoneNo;
    @Email
    private String email;
}
