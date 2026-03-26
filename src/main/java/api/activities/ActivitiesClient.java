package api.activities;

import api.engine.BookStoreObject;
import api.engine.BookStoreObjectApiClient;
import api.engine.ResponseSpecifications;

import java.util.List;

public class ActivitiesClient {

    private static final BookStoreObjectApiClient bookStoreObjectApiService
            = new BookStoreObjectApiClient(BookStoreObject.ACTIVITIES);

    public Activity createActivity(Activity activity) {
        return bookStoreObjectApiService
                .postObject(activity)
                .validate(ResponseSpecifications.responseCode200Spec())
                .getRecord(Activity.class);
    }

    public List<Activity> getActivities() {
        return bookStoreObjectApiService
                .getObjects()
                .validate(ResponseSpecifications.responseCode200Spec())
                .getRecords(Activity.class);
    }

    public Activity getActivity(int activityId) {
        return bookStoreObjectApiService
                .getObject(activityId)
                .validate(ResponseSpecifications.responseCode200Spec())
                .getRecord(Activity.class);
    }

    public void updateActivity(int activityId, Activity activity) {
        bookStoreObjectApiService
                .updateObject(activityId, activity)
                .validate(ResponseSpecifications.responseCode200Spec());
    }

    public void deleteActivity(int activityId) {
        bookStoreObjectApiService
                .deleteObject(activityId)
                .validate(ResponseSpecifications.responseCode200Spec());
    }
}
