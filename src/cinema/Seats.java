package cinema;

import java.util.Objects;


public class Seats {
    private Integer row;
    private Integer column;
    private Integer price;

   Seats() {

   }
    public Seats (Integer row, Integer column) {
        this.row = row;
        this.column = column;
        this.price = (row <= 4) ? 10: 8;
    }


    public void setColumn(Integer col) {
        this.column = col;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public void setPrice(Integer price) { this.price = price; }

    public Integer getPrice() { return price; }

    public Integer getRow() {
        return this.row;
    }

    public Integer getColumn() {
        return this.column;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Seats)) return false;

        Seats seat = (Seats) obj;
        return Objects.equals(this.row, seat.row) && Objects.equals(this.column, seat.column);

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + row.hashCode();
        result = 31 * result + column.hashCode();
        return result;
    }
}

