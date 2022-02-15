import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MovieStore {
    private final ArrayList<Video> videos = new ArrayList<>();
    private final Map<String, VideoStoreDates> overDueVideos = new HashMap<>();
    private final Map<String, LocalDate> checkedOutVideos = new HashMap<>();

    public void start() {
        getValuesFromFile();
        getAllVideos();
        randomCheckOutVideos();
        getAllCheckedOutVideos();
        storeAllOverDueVideos();
        getAllOverDueVideos();
    }

    private void storeAllOverDueVideos() {
        VideoStoreDates videoStoreDates = new VideoStoreDates();
        for (int i = 0; i < checkedOutVideos.size(); i++) {
            int randomVideo = (int) (Math.random() * checkedOutVideos.size());
            if (videoStoreDates.getOverDue().isAfter(videoStoreDates.getDue())) {
                overDueVideos.put(videos.get(randomVideo).getTitle(), videoStoreDates);
            }
        }
    }

    private void getValuesFromFile() {
        try {
            BufferedReader bufferedReader = getBufferedReader();
            addAllVideos(bufferedReader);
        } catch (IOException e) {
            throw new FileSystemNotFoundException("The file could not be found, check the file or adjust the location");
        }
    }

    private BufferedReader getBufferedReader() throws FileNotFoundException {
        File file = new File("src/main/resources/MovieList.txt");
        FileReader fileReader = new FileReader(file);
        return new BufferedReader(fileReader);
    }

    private void addAllVideos(BufferedReader bufferedReader) throws IOException {
        String line;
        int MAX_LINE = 22;
        for (int i = 0; i < MAX_LINE; i++) {
            line = bufferedReader.readLine().toLowerCase(Locale.ROOT);
            videos.add(new Video(line, LocalDate.now()));
        }
    }

    private void getAllOverDueVideos() {
        for (Map.Entry<String, VideoStoreDates> entry : overDueVideos.entrySet()) {
            System.out.println("Videos that are overdue '" + entry.getKey() + "' Date due to be given back '" + entry.getValue().getDue() + "' date brought back '" + entry.getValue().getOverDue() + "'");
        }
    }

    private void randomCheckOutVideos() {
        int randomVideosCheckedOut = (int) (Math.random()*videos.size());
        VideoStoreDates videoStoreDates = new VideoStoreDates();
        for (int i = 0; i < randomVideosCheckedOut; i++) {
            int randomVide = (int) (Math.random() * videos.size());
            checkedOutVideos.put(videos.get(randomVide).getTitle(), videoStoreDates.getDue());
        }
    }

    private void getAllCheckedOutVideos() {
        for (Map.Entry<String, LocalDate> entry : checkedOutVideos.entrySet()) {
            System.out.println("Videos that are checked out '" + entry.getKey() + "' and due: " + entry.getValue());
        }
    }

    private void getAllVideos() {
        System.out.println("Videos available");
        for (Video video : videos) {
            System.out.println(video.getTitle());
        }
    }
}
