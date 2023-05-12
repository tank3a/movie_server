package dbclass.movie.dto.theater;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TheaterRegisterDTO {

    private Long theaterId;
    private String name;
    private String type;
    private int floor;
}
