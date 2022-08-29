import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MovieStore {
    static ArrayList<Video> videos = new ArrayList<>();


    public void startApplication() {
        getMoviesFromAFile();
        displayAllMovies();
        MoviesCheckedOut moviesCheckedOut = new MoviesCheckedOut();
        moviesCheckedOut.checkOutRandomVideos();
        moviesCheckedOut.getAllCheckedOutVideos();
        moviesCheckedOut.storeAllOverDueVideos();
        moviesCheckedOut.getAllOverDueVideos();
    }

    private void getMoviesFromAFile() {
        try {
            Path path = Paths.get("src/main/resources/MovieList.txt");
            Files.readAllLines(path).forEach(e -> {
                videos.add(new Video(e));
            });
        } catch ( IOException e ) {
            throw new FileSystemNotFoundException("The file could not be found, check the file or adjust the location");
        }
    }

    private void displayAllMovies() {
        for ( Video video: videos ) {
            System.out.println(video.getTitle());
        }
    }
}
