package cinema;

import org.springframework.stereotype.Component;

@Component
public class CinemaStats {
    private Integer income = 0;
    private Integer available;
    private Integer purchased = 0;

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getPurchased() {
        return purchased;
    }

    public void setPurchased(Integer purchased) {
        this.purchased = purchased;
    }

    public void incrementPurchased() {
        this.purchased++;
    }

    public void decrementPurchased() {
        this.purchased--;
    }

    public void incrementIncome(Integer income) {
        this.income += income;
    }

    public void decrementIncome(Integer income) {
        this.income -= income;
    }
}
