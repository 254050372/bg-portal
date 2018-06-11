package com.boot.core.common.util;/**
 * @description
 * @autor xbwu on 2018/5/28.
 */

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * jdk8新版时间处理工具类
 * 参考：https://blog.csdn.net/chenxun_2010/article/details/72539981
 * @author xbwu
 * @create 2018-05-28
 *
 *  java。time包中的是类是不可变且线程安全的。新的时间及日期API位于java.time中，下面是一些关键类

    ●Instant——它代表的是时间戳

    ●LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。

    ●LocalTime——它代表的是不含日期的时间

    ●LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。

    ●ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。
 **/
public class DateUtils {

    /**
     * 获取当前年月日
     * @return
     */
    private void getNowDate(){
        LocalDate nowDate=LocalDate.now();
        nowDate.getYear();
        nowDate.getMonthValue();
        nowDate.getDayOfMonth();
    }

    /**
     * 获取当前年月日(格式化：yyyy-mm-dd hh24:mi:ss)
     * @return
     */
    private static LocalDate getNowDateFormat(){
        return LocalDate.now();
    }

    /**
     * 获取指定的日期
     * @return
     */
    private static LocalDate getAssignDate(int year,int month,int day){
        return LocalDate.of(year,month,day);
    }

    /**
     * 日期对比
     * @return
     */
    private void dateCompare(LocalDate date1,LocalDate date2){
        date1.isBefore(date2);
        date1.isEqual(date2);
        date1.isAfter(date2);
    }

    /**
     * 时间增加或减少
     */
    private void addOrSubTime(){
        Instant now=Instant.now();
        //加一天，其他时间工具类也可以如此操作，api都形似 plus,如果值为负数，则为减少，形似使用api:now.minus
        Instant nowadd=now.plus(-1, ChronoUnit.DAYS);
//        Instant nowsub=now.minus(1, ChronoUnit.DAYS);
//        Instant nowadd=now.plus(1, ChronoUnit.MONTHS);
//        Instant nowadd=now.plus(1, ChronoUnit.YEARS);

        System.out.println(now);
        System.out.println(nowadd);
    }
    /**
     * 表示固定的日期api：YearMonth
     */
    private void yearMonth(){
        // 而信用卡过期时间等,可以使用YearMonth
        YearMonth ym=YearMonth.now();
        //返回对应月份总共天数
        ym.lengthOfMonth();
        //判断是否闰年
        ym.isLeapYear();
    }

    /**
     * 时间相差数
     */
    private void interval(){
        LocalDate d1=LocalDate.of(2018,5,28);
        LocalDate d2=LocalDate.of(2017,5,28);
        //相差天数
        System.out.println(Math.abs(d1.toEpochDay()-d2.toEpochDay()));
        //相差月数
        System.out.println(Math.abs(Period.between(d1,d2).getYears()*12+Period.between(d1,d2).getMonths()));
    }

    /**
     * 新版时间格式化
     */
    private void formatDate(){
        //格式化新版时间，需要一个在格式化工具处设置时区，否则无法转换，Instant和LocalDateTime默认没有时区信息
        DateTimeFormatter df =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                        .withLocale( Locale.getDefault())
                        .withZone( ZoneId.systemDefault());
        Instant now = Instant.now();
        String format = df.format(now);
        System.out.println(format);
        //也可以在时间本身上设置时区
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        //Instant now = Instant.now();
        //now.atZone(ZoneId.systemDefault());
        String format2 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format2);
    }

    /**
     * Instant和Date互相转换
     */
    private void conversion(){
        Date date=new Date();
        Instant is=date.toInstant();
        Date date2=Date.from(is);
    }
    private static void main(String[] args) {
        
    }
}
