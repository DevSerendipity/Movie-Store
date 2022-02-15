import java.time.LocalDate;

public class Video {
    private final String title;

    public String getTitle() {
        return title;
    }

    public Video(String name, LocalDate due) {
        this.title = name;
    }
}
