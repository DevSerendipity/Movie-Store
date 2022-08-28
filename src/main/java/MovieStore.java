import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private final ArrayList<Video> videos = new ArrayList<>();
    private final Map<String, VideoStoreDates> overDueVideos = new HashMap<>();
    private final Map<String, LocalDate> checkedOutVideos = new HashMap<>();

    public void startApplication() {
        getMoviesFromAFile();
        randomCheckOutVideos();
        getAllCheckedOutVideos();
        storeAllOverDueVideos();
        getAllOverDueVideos();
    }

    private void getMoviesFromAFile() {
        try {
            Path path = Paths.get("src/main/resources/MovieList.txt");
            Files.readAllLines(path).forEach(System.out::println);
        } catch ( IOException e ) {
            throw new FileSystemNotFoundException("The file could not be found, check the file or adjust the location");
        }
    }

    private void randomCheckOutVideos() {
        int randomVideosCheckedOut = (int) (Math.random() * videos.size());
        VideoStoreDates videoStoreDates = new VideoStoreDates();
        for ( int i = 0; i < randomVideosCheckedOut; i++ ) {
            int randomVide = (int) (Math.random() * videos.size());
            checkedOutVideos.put(videos.get(randomVide).getTitle(), videoStoreDates.getDue());
        }
    }

    private void getAllCheckedOutVideos() {
        for ( Map.Entry<String, LocalDate> entry: checkedOutVideos.entrySet() ) {
            System.out.println("Videos that are checked out '" + entry.getKey() + "' and due: " + entry.getValue());
        }
    }

    private void storeAllOverDueVideos() {
        VideoStoreDates videoStoreDates = new VideoStoreDates();
        for ( int i = 0; i < checkedOutVideos.size(); i++ ) {
            int randomVideo = (int) (Math.random() * checkedOutVideos.size());
            if ( videoStoreDates.getOverDue().isAfter(videoStoreDates.getDue()) ) {
                overDueVideos.put(videos.get(randomVideo).getTitle(), videoStoreDates);
            }
        }
    }

    private void getAllOverDueVideos() {
        for ( Map.Entry<String, VideoStoreDates> entry: overDueVideos.entrySet() ) {
            System.out.println(
                    "Videos that are overdue '" + entry.getKey() + "' Date due to be given back '" + entry.getValue()
                                                                                                          .getDue()
                            + "' date brought back '" + entry.getValue().getOverDue() + "'");
        }
    }
}
