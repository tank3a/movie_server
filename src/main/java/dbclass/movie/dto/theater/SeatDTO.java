package dbclass.movie.dto.theater;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeatDTO {

    private String seatId;
    private Long theaterId;
    private char row;
    private int column;
    private int price;
}
