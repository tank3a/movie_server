package dbclass.movie.domain.theater;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "SEAT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@IdClass(SeatId.class)
public class Seat {

    @Id
    @Column(name = "SEAT_ID")
    private String seatId;

    @Id
    @JoinColumn(name = "THEATER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Theater theater;

    @Column(name = "ROW_NUM", nullable = false)
    private Character row;

    @Column(name = "COL_NUM", nullable = false)
    private int column;

    @Column(name = "PRICE", nullable = false)
    private int price;

    @Builder
    public Seat(Theater theater, char row, int column, int price) {
        this.column = column;
        this.row = row;
        this.theater = theater;
        this.price = price;

        this.seatId = row + Integer.toString(column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column && theater.equals(seat.theater);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theater, row, column);
    }
}
