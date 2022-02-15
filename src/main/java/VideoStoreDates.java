import java.time.LocalDate;

public class VideoStoreDates {

    private final LocalDate due = LocalDate.now().plusWeeks(1);
    private final LocalDate overDue = LocalDate.now().plusWeeks((int)(Math.random()*3));

    public LocalDate getDue() {
        return due;
    }

    public LocalDate getOverDue() {
        return overDue;
    }
}
