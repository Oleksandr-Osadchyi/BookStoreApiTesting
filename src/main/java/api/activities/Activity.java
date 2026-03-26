package api.activities;

import lombok.*;
import utils.DateTimeUtil;
import utils.FakerUtil;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    private int id;
    private String title;
    private String dueDate;
    private boolean completed;

    public Activity createActivityModel(int id, String title, String dueDate, boolean completed) {
        return Activity.builder()
                .id(id)
                .title(title)
                .dueDate(dueDate)
                .completed(completed)
                .build();
    }

    public Activity createRandomActivityModel() {
        return Activity.builder()
                .id(FakerUtil.randomNumber(1000, 100000))
                .title(FakerUtil.randomTitle())
                .dueDate(DateTimeUtil.formatDateToString(LocalDateTime.now(), DateTimeUtil.FULL_ZONED_UTC_DATE_TIME_FORMAT))
                .completed(FakerUtil.randomBoolean())
                .build();
    }
}