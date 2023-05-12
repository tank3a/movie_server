package dbclass.movie.service;

import dbclass.movie.domain.theater.Seat;
import dbclass.movie.domain.theater.SeatId;
import dbclass.movie.domain.theater.Theater;
import dbclass.movie.dto.theater.*;
import dbclass.movie.exceptionHandler.DataExistsException;
import dbclass.movie.exceptionHandler.DataNotExistsException;
import dbclass.movie.repository.SeatRepository;
import dbclass.movie.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class TheaterService {

    private final TheaterRepository theaterRepository;
    private final SeatRepository seatRepository;

    @Transactional
    public TheaterDTO register(TheaterRegisterDTO registerDTO) {
        if(theaterRepository.existsByName(registerDTO.getName())) {
            throw new DataExistsException("이미 존재하는 상영관입니다.", "theater");
        }
        Theater theater = Theater.builder()
                .type(registerDTO.getType())
                .floor(registerDTO.getFloor())
                .name(registerDTO.getName())
                .build();
        theater = theaterRepository.save(theater);
        return TheaterDTO.builder()
                .theaterId(theater.getTheaterId())
                .floor(theater.getFloor())
                .name(theater.getName())
                .type(theater.getType())
                .build();
    }

    @Transactional
    public void registerSeat(Long theaterId, List<SeatRegisterDTO> seatToRegister) {
        Theater theater = theaterRepository.findById(theaterId).orElseThrow(() -> new DataNotExistsException("존재하지 않는 상영관ID입니다.", "Theater"));

        List<Seat> seats = new ArrayList<>();

        ListIterator<SeatRegisterDTO> listIterator = seatToRegister.listIterator();
        while(listIterator.hasNext()) {
            SeatRegisterDTO registerDTO = listIterator.next();
            char row = registerDTO.getSeatLocation().charAt(0);
            int col = Integer.parseInt(registerDTO.getSeatLocation().substring(1));
            Seat seat = Seat.builder()
                    .theater(theater)
                    .row(row)
                    .column(col)
                    .price(registerDTO.getPrice())
                    .build();

            seats.add(seat);
        }

        seatRepository.saveAll(seats.stream().distinct().collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public TheaterDTO getTheater(Long theaterId) {
        Theater theater = theaterRepository.findById(theaterId).orElseThrow(() -> new DataNotExistsException("존재하지 않는 상영관입니다.", "Theater"));
        return TheaterDTO.builder()
                .theaterId(theater.getTheaterId())
                .type(theater.getType())
                .name(theater.getName())
                .floor(theater.getFloor())
                .build();
    }

    @Transactional(readOnly = true)
    public List<SeatDTO> getSeats(Long theaterId) {
        Theater theater = theaterRepository.findById(theaterId).orElseThrow(() -> new DataNotExistsException("존재하지 않는 상영관입니다.", "Theater"));

        List<Seat> seats = seatRepository.findAllByTheater(theater);

        return seats.stream().map(seat -> seatToSeatDTOMapper(seat)).collect(Collectors.toList());
    }

    private SeatDTO seatToSeatDTOMapper(Seat seat) {
        return SeatDTO.builder()
                .seatId(seat.getSeatId())
                .theaterId(seat.getTheater().getTheaterId())
                .price(seat.getPrice())
                .column(seat.getColumn())
                .row(seat.getRow())
                .build();
    }

    @Transactional
    public TheaterDTO modifyTheater(TheaterRegisterDTO registerDTO) {
        if(!theaterRepository.existsById(registerDTO.getTheaterId())) {
            throw new DataNotExistsException("존재하지 않는 상영관ID입니다.", "Theater");
        }

        if(theaterRepository.existsByName(registerDTO.getName())) {
            throw new DataExistsException("이미 존재하는 상영관이름입니다.", "Theater");
        }
        Theater theater = Theater.builder()
                .theaterId(registerDTO.getTheaterId())
                .name(registerDTO.getName())
                .floor(registerDTO.getFloor())
                .type(registerDTO.getType())
                .build();

        theaterRepository.save(theater);

        return TheaterDTO.builder()
                .type(theater.getType())
                .theaterId(theater.getTheaterId())
                .floor(theater.getFloor())
                .name(theater.getName())
                .build();

    }

    @Transactional
    public void modifySeat(Long theaterId, SeatRegisterDTO seatToModify) {
        Theater theater = theaterRepository.findById(theaterId).orElseThrow(() -> new DataNotExistsException("존재하지 않는 상영관 ID입니다.", "Theater"));

        char row = seatToModify.getSeatLocation().charAt(0);
        int col = Integer.parseInt(seatToModify.getSeatLocation().substring(1));
        Seat seat = Seat.builder()
                .theater(theater)
                .row(row)
                .column(col)
                .price(seatToModify.getPrice())
                .build();

        seatRepository.save(seat);
    }

    @Transactional
    public void deleteTheater(Long theaterId) {
        theaterRepository.deleteById(theaterId);
    }

    @Transactional
    public void deleteSeat(Long theaterId, SeatDeleteDTO seatToDelete) {
        Theater theater = theaterRepository.findById(theaterId).orElseThrow(() -> new DataNotExistsException("존재하지 않는 상영관 ID입니다.", "Theater"));

        SeatId seatId = SeatId.builder()
                .theater(theater)
                .seatId(seatToDelete.getColumn() + Integer.toString(seatToDelete.getRow()))
                .build();

        seatRepository.deleteById(seatId);
    }

}
