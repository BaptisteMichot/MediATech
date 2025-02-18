package mediatech.Model.BL;

import java.util.Date;

public class Fine {
    private int fineId;
    private int reservationId;
    private int overdueDays;
    private double dailyAmount;
    private double fineAmount;

    public Fine(int fineId, int reservationId, int overdueDays, double dailyAmount) {
        this.fineId = fineId;
        this.reservationId = reservationId;
        this.overdueDays = overdueDays;
        this.dailyAmount = dailyAmount;
        this.fineAmount = calculateFineAmount(overdueDays, dailyAmount);
    }

    public Fine() {}

    public int getFineId() {
        return fineId;
    }
    public void setFineId(int fineId) {
        this.fineId = fineId;
    }

    public int getReservationId() {
        return reservationId;
    }
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getOverdueDays() {
        return overdueDays;
    }
    public void setOverdueDays(int overdueDays) {
        this.overdueDays = overdueDays;
    }

    public double getDailyAmount() {
        return dailyAmount;
    }
    public void setDailyAmount(double dailyAmount) {
        this.dailyAmount = dailyAmount;
    }

    public double getFineAmount() {
        return fineAmount;
    }
    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }


    public int calculateNbOverdueDays(Date expirationDate) {
        Date now = new Date();
        if (now.before(expirationDate)) {
            return 0;
        } else {
            return (int) ((now.getTime() - expirationDate.getTime()) / (24 * 60 * 60 * 1000));
        }
    }

    public double calculateFineAmount(int overdueDays, double dailyAmount) {
        if (overdueDays <= 0 || dailyAmount <= 0) {
            return 0;
        }
        return dailyAmount * overdueDays;
    }

    public boolean applyFine(int reservationId, double amount) {
        if (reservationId <= 0 || amount < 0) {
            return false;
        }
        return true;
    }
}
