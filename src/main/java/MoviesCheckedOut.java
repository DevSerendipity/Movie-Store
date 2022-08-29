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
import java.util.HashMap;
import java.util.Map;

public class MoviesCheckedOut {

    private final Map<String, VideoStoreDates> overDueVideos = new HashMap<>();
    private final Map<String, LocalDate> checkedOutVideos = new HashMap<>();

    VideoStoreDates videoCheckoutDates = new VideoStoreDates();


    void checkOutRandomVideos() {
        int random = (int) (Math.random() * MovieStore.videos.size());
        for ( int i = 0; i < random; i++ ) {
            int randomVideo = (int) (Math.random() * MovieStore.videos.size());
            checkedOutVideos.put(MovieStore.videos.get(randomVideo).getTitle(), videoCheckoutDates.getDue());
        }
    }

    void getAllCheckedOutVideos() {
        for ( Map.Entry<String, LocalDate> entry: checkedOutVideos.entrySet() ) {
            System.out.println("Videos that are checked out '" + entry.getKey() + "' and due: " + entry.getValue());
        }
    }

    void storeAllOverDueVideos() {
        for ( int i = 0; i < checkedOutVideos.size(); i++ ) {
            int randomVideo = (int) (Math.random() * checkedOutVideos.size());
            if ( videoCheckoutDates.getOverDue().isAfter(videoCheckoutDates.getDue()) ) {
                overDueVideos.put(MovieStore.videos.get(randomVideo).getTitle(), videoCheckoutDates);
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
