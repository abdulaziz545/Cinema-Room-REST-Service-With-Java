package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class Cinema {
    private Integer rows = 9;
    private Integer columns = 9;
    private List<Seats> seats = new ArrayList<>();

    @JsonIgnore
    private final CinemaStats cinemaStats;

    @JsonIgnore
    private ConcurrentMap<UUID, Seats> bookedSeats = new ConcurrentHashMap<>();

    @Autowired
    public Cinema(CinemaStats cinemaStats) {
        this.cinemaStats = cinemaStats;

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= columns; c++) {
                seats.add(new Seats(r, c));
            }
        }
        cinemaStats.setAvailable(seats.size());
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public List<Seats> getSeats() {
        return this.seats;
    }

    public void setSeats(List<Seats> seats) {
        this.seats = seats;
    }

    public void removeFromAvailableSeats(Seats seat) {
        seats.remove(seat);
    }

    public void addToAvailableSeats(Seats seat) { seats.add(seat); }

    public ConcurrentMap<UUID, Seats> getBookedSeats() {
        return bookedSeats;
    }

    public boolean notIsValidRange(Integer n) {
        return !(n >= 1 && n <= 9);
    }

    private void buyTicket(Seats seat) {
        seat.setPrice(seat.getRow() <= 4 ? 10 : 8);
        cinemaStats.incrementPurchased();
        cinemaStats.incrementIncome(seat.getPrice());
        removeFromAvailableSeats(seat);
        cinemaStats.setAvailable(getSeats().size());
    }

    private Seats refundTicket(UUID uuid) {
        cinemaStats.decrementPurchased();
        Seats s = bookedSeats.remove(uuid);
        cinemaStats.decrementIncome(s.getPrice());
        addToAvailableSeats(s);
        cinemaStats.setAvailable(getSeats().size());
        return s;
    }


    public ResponseEntity<?> purchaseSeat(Seats seat) {
        Integer row = seat.getRow();
        Integer column = seat.getColumn();
        if(row == null || column == null || notIsValidRange(row) || notIsValidRange(column)) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "The number of a row or a column is out of bounds!"
            ));
        }
        if (!seats.contains(seat)) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "The ticket has been already purchased!"));
        }
        Token token = new Token();
        buyTicket(seat);
        bookedSeats.putIfAbsent(token.getToken(), seat);
        return ResponseEntity.ok(Map.of("token", token.getToken(),
                "ticket", seat));
    }

    public ResponseEntity<?> refundSeat(Token token) {
        UUID uuid = token.getToken();
        if (uuid == null || !bookedSeats.containsKey(uuid)) {
            return ResponseEntity.badRequest().body((Map.of("error", "Wrong token!")));
        }

        Seats s = refundTicket(uuid);
        return ResponseEntity.ok(Map.of("ticket", s));
    }
}