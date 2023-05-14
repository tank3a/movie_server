package dbclass.movie.dto.theater;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeatRegisterDTO {

    private String originalSeatId;
    private String seatLocation;
    private int price;
}
