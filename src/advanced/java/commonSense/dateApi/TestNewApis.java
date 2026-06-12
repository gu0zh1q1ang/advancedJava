package advanced.java.commonSense.dateApi;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class TestNewApis {
    public static void main(String[] args) {
        // 定义一个日期或者时间
        LocalDate date = LocalDate.of(2026, 6, 1);
        System.out.println(date);

        Period between = Period.between(date, LocalDate.now());
        System.out.println(between.getYears());
        System.out.println(between.getMonths());
        System.out.println(between.getDays());
        between.get(ChronoUnit.YEARS);

        LocalDateTime nextLevel = LocalDateTime.of(2026, 6, 12, 9, 55, 0);


        LocalTime  time = LocalTime.of(16,55,0);
        Duration between1 = Duration.between(LocalDateTime.now(), time);
        System.out.println(between1.get(ChronoUnit.SECONDS));
    }
}
