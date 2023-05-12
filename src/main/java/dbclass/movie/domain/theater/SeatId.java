package dbclass.movie.domain.theater;

import lombok.*;

@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatId {

    private String seatId;
    private Theater theater;
}
