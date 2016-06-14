package com.tangzq.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.WEEKS;

/**
 * Author tangzq.
 */
public class LocalDateTest {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static void main(String[] args){
        LocalDate from=LocalDate.parse("2016-06-12",formatter);
        LocalDate to=LocalDate.parse("2016-05-30",formatter);
        //取得指定日期范围之间有多少天
        //方法1
        long daysBetween = DAYS.between(from, to);
        System.out.println(daysBetween);

        //方法2
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now();
        long days = Period.between(startDate, endDate).getDays();
        System.out.println(days);
        //方法3
        long days2 = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println(days2);

        //取得指定日期范围之间有多少周
        long weeks = WEEKS.between(from,to);
        System.out.println(weeks);

        //取得指定日期范围之间有多少月
        long months=MONTHS.between(from,to);
        System.out.println(months);

        //取得指定日期属于某周的第几天
        System.out.println("周的第几天："+from.getDayOfWeek().getValue());
        //取得指定日期属于某月的第几天
        System.out.println("月的第几天："+from.getDayOfMonth());

        //取得指定日期所在某一周的星期一和星期天的日期
        LocalDate now = LocalDate.now();
        LocalDate first = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate last = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(first+","+last);

        //取得指定的两个日期范围内周一所在的日期
        LocalDate froma=LocalDate.parse("2016-05-01",formatter);
        LocalDate tob=LocalDate.parse("2016-06-30",formatter);
        LocalDate tempDate=froma;
        while(tempDate.isBefore(tob)){
            /**
             LocalDate mondayDate = tempDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
             System.out.println("每周一的日期："+mondayDate);
             System.out.println(tempDate.getDayOfWeek().name());
             System.out.println(DayOfWeek.MONDAY.name());
             **/
            if(tempDate.getDayOfWeek().name().equals(DayOfWeek.MONDAY.name())){
                System.out.println("每周一的日期："+tempDate);
            }
            tempDate=tempDate.plusDays(1);
        }
        while (!tempDate.isAfter(tob)){
            /**
             LocalDate mondayDate = tempDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
             System.out.println("每周一的日期："+mondayDate);
             **/
            if(tempDate.getDayOfWeek().name().equals(DayOfWeek.MONDAY.name())){
                System.out.println("每周一的日期："+tempDate);
            }
            tempDate=tempDate.plusDays(1);
            tempDate=tempDate.plusDays(1);
        }

        System.out.println(getMondayDate(froma,tob));
    }

    /**
     * 取得指定的两个日期范围内周一所在的日期封装方法
     * @param from
     * @param to
     * @return
     */
    private static List<LocalDate> getMondayDate(LocalDate from, LocalDate to){
        List<LocalDate> mondayDateList=new ArrayList();
        LocalDate tempDate=from;
        while(tempDate.isBefore(to)){
            if(tempDate.getDayOfWeek().name().equals(DayOfWeek.MONDAY.name())){
                mondayDateList.add(tempDate);
            }
            tempDate=tempDate.plusDays(1);
        }
        while (!tempDate.isAfter(to)){
            if(tempDate.getDayOfWeek().name().equals(DayOfWeek.MONDAY.name())){
                mondayDateList.add(tempDate);
            }
            tempDate=tempDate.plusDays(1);
            tempDate=tempDate.plusDays(1);
        }
        return mondayDateList;
    }
}
