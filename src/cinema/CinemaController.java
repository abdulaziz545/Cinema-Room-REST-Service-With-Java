package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class CinemaController {
    @Autowired
    private Cinema cinema;

    @Autowired
    private CinemaStats cinemaStats;

    @GetMapping("/seats")
    public Cinema getCinemaInfo() {
        return cinema;
    }


    @PostMapping(value = "/purchase", consumes = "application/json")
    public ResponseEntity<?> bookSeat(@RequestBody Seats seat) {
        return cinema.purchaseSeat(seat);
    }


    @PostMapping(value = "/return", consumes = "application/json")
    public ResponseEntity<?> refundSeat(@RequestBody Token token) {
        return cinema.refundSeat(token);
    }


    @GetMapping(value = "/stats", produces = "application/json")
    public ResponseEntity<?> showStats(@RequestParam(value = "password", required = false) String password) {
        String secretKey = "super_secret";
        if (!secretKey.equals(password)) {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }


        return ResponseEntity.ok().body(cinemaStats);
    }

}
