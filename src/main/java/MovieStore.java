import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/*- Noun.
        - One reason for change
        - should describe the responsibility
        - small amount of
- more, means could be splitted
        - Law of demeter
- The Law of Demeter says that a method _f_ of a class _C_ should only call the methods of these:
- C
        - an objected created by F
- an object hel in an instance variable of C
- an object passed as an argument to f
        - abstraction
        - SRP
        - feature by extension
 */

public class MovieStore {
     static ArrayList<Video> videos = new ArrayList<>();


    public void startApplication() {
        getMoviesFromAFile();
        MoviesCheckedOut moviesCheckedOut = new MoviesCheckedOut();
        moviesCheckedOut.randomCheckOutVideos();
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
            for ( Video video : videos ) {
                System.out.println(video.getTitle());
            }
        } catch ( IOException e ) {
            throw new FileSystemNotFoundException("The file could not be found, check the file or adjust the location");
        }
    }


}
