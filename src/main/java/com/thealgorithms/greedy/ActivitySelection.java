import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Activity {
    int start, end;

    Activity(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class ActivitySelection {
    public static List<Activity> selectActivities(List<Activity> activities) {
        List<Activity> selectedActivities = new ArrayList<>();
        if (activities.isEmpty()) return selectedActivities;

        // Sort activities by their end times
        Collections.sort(activities, Comparator.comparingInt(a -> a.end));

        // Greedy selection of activities
        Activity prevActivity = activities.get(0);
        selectedActivities.add(prevActivity);

        for (int i = 1; i < activities.size(); i++) {
            if (activities.get(i).start >= prevActivity.end) {
                selectedActivities.add(activities.get(i));
                prevActivity = activities.get(i);
            }
        }

        return selectedActivities;
    }

    public static void main(String[] args) {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity(1, 4));
        activities.add(new Activity(3, 5));
        activities.add(new Activity(0, 6));
        activities.add(new Activity(5, 7));
        activities.add(new Activity(8, 9));
        activities.add(new Activity(5, 9));

        List<Activity> selectedActivities = selectActivities(activities);

        // Print selected activities
        for (Activity activity : selectedActivities) {
            System.out.println("Activity [" + activity.start + ", " + activity.end + "]");
        }
    }
}
