package pl.itacademy.schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.time.temporal.ChronoUnit.MINUTES;

public class LessonGenerator {
    public Schedule generateSchedule(LessonParameters lessonParameters) {
        long completeMinutes = 0;
        long lessonLength = MINUTES.between(lessonParameters.getBeginTime(), lessonParameters.getEndTime());
        long requiredMinutes = lessonParameters.getNumberOfHours() * 60;
        LocalDate currentDate = lessonParameters.getStartDate();
        Set<DayOfWeek> lessonDays = lessonParameters.getLessonDays();
        List<Lesson> lessons = new ArrayList<>();

        do {
            currentDate = findNextApplicableDay(currentDate, lessonDays);
            Lesson lesson = new Lesson(currentDate, lessonParameters.getBeginTime(), lessonParameters.getEndTime());
            lessons.add(lesson);
            completeMinutes = completeMinutes + lessonLength;
            currentDate = currentDate.plusDays(1);
        }
        while (requiredMinutes > completeMinutes);

        if (requiredMinutes!=completeMinutes) {
            Lesson lastLesson = lessons.get(lessons.size()-1);
            lastLesson.setEndTime(lastLesson.getEndTime().minusMinutes(completeMinutes-requiredMinutes));
        }

        boolean isSuccessful = requiredMinutes == completeMinutes;

        return new Schedule(lessons, isSuccessful);
    }

    private LocalDate findNextApplicableDay(LocalDate startDate, Set<DayOfWeek> lessonDays) {
        while (!lessonDays.contains(startDate.getDayOfWeek())) {
            startDate = startDate.plusDays(1);
        }
        return startDate;
    }
}