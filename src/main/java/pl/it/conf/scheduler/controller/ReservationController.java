package pl.it.conf.scheduler.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.it.conf.scheduler.dto.ReservationDto;
import pl.it.conf.scheduler.payload.request.UserArg;
import pl.it.conf.scheduler.payload.response.SimpleResponse;
import pl.it.conf.scheduler.service.ReservationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<SimpleResponse> makeReservation(@RequestParam @Valid Long lectureId,
                                                          @RequestBody @Valid UserArg userArg) {
        return ResponseEntity.accepted().body(reservationService.makeReservation(lectureId, userArg));
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<ReservationDto>> displayReservations(@RequestParam @Valid String login) {
        return ResponseEntity.ok(reservationService.listReservations(login));
    }

    @DeleteMapping("/cancel")
    @ResponseBody
    public ResponseEntity<SimpleResponse> cancelReservation(@RequestParam @Valid Long reservationId,
                                                            @RequestBody @Valid UserArg userArg) {
        return ResponseEntity.accepted().body(reservationService.cancelReservation(userArg, reservationId));
    }

}
