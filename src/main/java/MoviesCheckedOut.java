/*- Noun.
    - One reason for change
    - should describe the reponsibility
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MoviesCheckedOut {

    private final Map<String, VideoStoreDates> overDueVideos = new HashMap<>();
    private final Map<String, LocalDate> checkedOutVideos = new HashMap<>();


    void randomCheckOutVideos() {
        int randomVideosCheckedOut = (int) (Math.random() * MovieStore.videos.size());
        VideoStoreDates videoStoreDates = new VideoStoreDates();
        for ( int i = 0; i < randomVideosCheckedOut; i++ ) {
            int randomVide = (int) (Math.random() * MovieStore.videos.size());
            checkedOutVideos.put(MovieStore.videos.get(randomVide).getTitle(), videoStoreDates.getDue());
        }
    }

    void getAllCheckedOutVideos() {
        for ( Map.Entry<String, LocalDate> entry: checkedOutVideos.entrySet() ) {
            System.out.println("Videos that are checked out '" + entry.getKey() + "' and due: " + entry.getValue());
        }
    }

     void storeAllOverDueVideos() {
        VideoStoreDates videoStoreDates = new VideoStoreDates();
        for ( int i = 0; i < checkedOutVideos.size(); i++ ) {
            int randomVideo = (int) (Math.random() * checkedOutVideos.size());
            if ( videoStoreDates.getOverDue().isAfter(videoStoreDates.getDue()) ) {
                overDueVideos.put(MovieStore.videos.get(randomVideo).getTitle(), videoStoreDates);
            }
        }
    }

     void getAllOverDueVideos() {
        for ( Map.Entry<String, VideoStoreDates> entry: overDueVideos.entrySet() ) {
            System.out.println(
                    "Videos that are overdue '" + entry.getKey() + "' Date due to be given back '" + entry.getValue()
                                                                                                          .getDue()
                            + "' date brought back '" + entry.getValue().getOverDue() + "'");
        }
    }

}
