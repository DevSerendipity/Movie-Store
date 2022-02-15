import java.time.LocalDate;

public class VideoStoreDates {
    private final int DUE_DATE = 1;
    private final int OVERDUE_DATE = 3;
    private final LocalDate due = LocalDate.now().plusWeeks(DUE_DATE);
    private final LocalDate overDue = LocalDate.now().plusWeeks((int)(Math.random()*OVERDUE_DATE));

    public LocalDate getDue() {
        return due;
    }

    public LocalDate getOverDue() {
        return overDue;
    }
}
