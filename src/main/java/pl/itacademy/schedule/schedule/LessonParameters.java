package pl.itacademy.schedule.schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

public class LessonParameters {
    private LocalTime beginTime;
    private LocalTime endTime;
    private int numberOfHours;
    private LocalDate startDate;
    private String fileName;
    private boolean showHelp;
    private Set<DayOfWeek> lessonDays;

    private LessonParameters(LocalTime beginTime, LocalTime endTime, int numberOfHours, LocalDate startDate, String fileName, boolean showHelp, Set<DayOfWeek> lessonDays) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.numberOfHours = numberOfHours;
        this.startDate = startDate;
        this.fileName = fileName;
        this.showHelp = showHelp;
        this.lessonDays = lessonDays;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public Set<DayOfWeek> getLessonDays() {
        return lessonDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonParameters that = (LessonParameters) o;
        return numberOfHours == that.numberOfHours &&
                showHelp == that.showHelp &&
                beginTime.equals(that.beginTime) &&
                endTime.equals(that.endTime) &&
                startDate.equals(that.startDate) &&
                fileName.equals(that.fileName) &&
                lessonDays.equals(that.lessonDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beginTime, endTime, numberOfHours, startDate, fileName, showHelp, lessonDays);
    }

    @Override
    public String toString() {
        return "LessonParameters{" +
                "beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", numberOfHours=" + numberOfHours +
                ", startDate=" + startDate +
                ", fileName='" + fileName + '\'' +
                ", showHelp=" + showHelp +
                ", lessonDays=" + lessonDays +
                '}';
    }

    public static class Builder {
        private LocalTime beginTime;
        private LocalTime endTime;
        private int numberOfHour;
        private String fileName;
        private boolean showHelp;
        private Set<DayOfWeek> lessonDays;
        private LocalDate startDate;

        public Builder(LocalTime beginTime, LocalTime endTime, int numberOfHour, Set<DayOfWeek> lessonDays, LocalDate startDate) {
            this.beginTime = beginTime;
            this.endTime = endTime;
            this.numberOfHour = numberOfHour;
            this.lessonDays = lessonDays;
            this.startDate = startDate;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder showHelp(boolean showHelp) {
            this.showHelp = showHelp;
            return this;
        }

        public LessonParameters build() {
            return new LessonParameters(beginTime, endTime, numberOfHour, startDate, fileName, showHelp, lessonDays);
        }
    }
}
