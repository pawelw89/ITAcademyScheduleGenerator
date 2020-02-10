package pl.itacademy.schedule;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import jdk.jfr.StackTrace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.junit.jupiter.api.Assertions.*;

class LessonGeneratorTest {

    private LessonGenerator lessonGenerator;

    @BeforeEach
    public void setup(){
        lessonGenerator = new LessonGenerator();
    }

    @Test
    public void generateSchedule_successfullyGeneratesProperSchedule() {
        LessonParameters lessonParameters = new LessonParameters.Builder(
                LocalTime.of(17, 0),
                LocalTime.of(18, 30),
                3,
                Set.of(MONDAY, WEDNESDAY),
                LocalDate.of(2020, 2, 1))
                .fileName("schedule.xlsx")
                .showHelp(false)
                .build();

        Lesson lesson1 = new Lesson(LocalDate.of(2020, 2, 3), LocalTime.of(17, 0), LocalTime.of(18,30));
        Lesson lesson2 = new Lesson(LocalDate.of(2020, 2, 5), LocalTime.of(17, 0), LocalTime.of(18,30));

        Schedule expectedSchedule = new Schedule(List.of(lesson1, lesson2), true);
        Schedule resultSchedule = lessonGenerator.generateSchedule(lessonParameters);

        assertThat(resultSchedule, equalTo(expectedSchedule));
    }

    @Test
    public void generateSchedule_unsuccessfulSchedule() {

        LessonParameters lessonParameters = new LessonParameters.Builder(
                LocalTime.of(17, 0),
                LocalTime.of(18, 30),
                4,
                Set.of(MONDAY, WEDNESDAY),
                LocalDate.of(2020, 2, 1))
                .fileName("schedule.xlsx")
                .showHelp(false)
                .build();

        Lesson lesson1 = new Lesson(LocalDate.of(2020, 2, 3), LocalTime.of(17, 0), LocalTime.of(18,30));
        Lesson lesson2 = new Lesson(LocalDate.of(2020, 2, 5), LocalTime.of(17, 0), LocalTime.of(18,30));
        Lesson lesson3 = new Lesson(LocalDate.of(2020, 2, 10), LocalTime.of(17, 0), LocalTime.of(18,00));


        Schedule expectedSchedule = new Schedule(List.of(lesson1, lesson2, lesson3), false);
        Schedule resultSchedule = lessonGenerator.generateSchedule(lessonParameters);

        assertThat(resultSchedule, equalTo(expectedSchedule));
    }
}