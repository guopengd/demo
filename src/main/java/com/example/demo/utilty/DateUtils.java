package com.example.demo.utilty;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 日期计算工具类
 *
 * @author pengdong.guo
 * @date 2019/9/27
 */
public class DateUtils {

    private static final long MAX_TIME_STAMP = 10000_000_000L;
    private static final long MIN_TIME_STAMP = 1000_000_000L;

    public static final DateTimeFormatter DATE_EMPTY_PATTERN = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    public static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter MINUTE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DAY_PATTERN = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").parseDefaulting(ChronoField.HOUR_OF_DAY, 0).toFormatter();

    /***
     * 分别获取年月日的值
     */
    public static <L, M, R> Triple<Integer, Integer, Integer> getYearMonthDay(Date date) {
        return ImmutableTriple.of(parseDateToLocal(date).getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
    }

    /**
     * 按指定格式格式化日期
     */
    public static String formatDate(Date date, DateTimeFormatter pattern) {
        return parseDateToLocal(date).format(pattern);
    }

    /**
     * 根据时间格式获取日期（包含时分秒）
     * <p>
     * 请使用内部定义DateTimeFormatter
     * 如有其他需求，谨慎使用
     * </P>
     */
    public static Date parseDate(String date, DateTimeFormatter pattern) throws Exception {
        return parseLocalToDate(LocalDateTime.parse(date, pattern));
    }

    /**
     * date转换成localDateTime
     * </p>
     */
    public static LocalDateTime parseDateToLocal(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * localDateTime转换成date
     * </p>
     */
    public static Date parseLocalToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定日期的最小时间段
     */
    public static Date getMinDateTime(Date date) {
        return parseLocalToDate(parseDateToLocal(date).with(LocalTime.MIN));
    }

    /**
     * 获取指定日期的最大时间段
     */
    public static Date getMaxDateTime(Date date) {
        return parseLocalToDate(parseDateToLocal(date).with(LocalTime.MAX));
    }

    /**
     * 根据时间戳获取日期（自动判断秒还是毫秒时间戳）
     */
    private static Instant transToInstant(long time) {
        if (time < MAX_TIME_STAMP && time > MIN_TIME_STAMP) {
            return Instant.ofEpochSecond(time);
        }
        if (time < MAX_TIME_STAMP * 1000 && time > MIN_TIME_STAMP * 1000) {
            return Instant.ofEpochMilli(time);
        }
        throw new IllegalArgumentException("illegal time value");
    }

    /**
     * 获取两个时间段之间的天数
     */
    public static long getBetweenDays(long start, long end) {
        return ChronoUnit.DAYS.between(LocalDateTime.ofInstant(transToInstant(start), ZoneId.systemDefault()),
                LocalDateTime.ofInstant(transToInstant(end), ZoneId.systemDefault()));
    }

    /**
     * 获取两个时间段之间的天数
     */
    public static long getBetweenDays(String start, String end) {
        return ChronoUnit.DAYS.between(LocalDate.parse(start), LocalDate.parse(end));
    }

    /**
     * 给定日期减去秒数
     */
    public static Date minusSeconds(LocalDateTime time, long seconds) {
        return parseLocalToDate(time.minusSeconds(seconds));
    }

    /**
     * 给定日期增加秒数
     */
    public static Date plusSeconds(LocalDateTime time, long seconds) {
        return parseLocalToDate(time.plusSeconds(seconds));
    }

    /**
     * 给定日期减去分钟数
     */
    public static Date minusMinutes(LocalDateTime time, long minutes) {
        return parseLocalToDate(time.minusMinutes(minutes));
    }

    /**
     * 给定日期增加分钟数
     */
    public static Date plusMinutes(LocalDateTime time, long minutes) {
        return parseLocalToDate(time.plusMinutes(minutes));
    }

    /**
     * 给定日期减去小时数
     */
    public static Date minusHours(LocalDateTime time, long hours) {
        return parseLocalToDate(time.minusHours(hours));
    }

    /**
     * 给定日期增加小时数
     */
    public static Date plusHours(LocalDateTime time, long hours) {
        return parseLocalToDate(time.plusHours(hours));
    }

    /**
     * 给定日期减去天数
     */
    public static Date minusDays(LocalDateTime time, long days) {
        return parseLocalToDate(time.minusDays(days));
    }

    /**
     * 给定日期增加天数
     */
    public static Date plusDays(LocalDateTime time, long days) {
        return parseLocalToDate(time.plusDays(days));
    }

    /**
     * 给定日期减去月数
     */
    public static Date minusMonths(LocalDateTime time, long months) {
        return parseLocalToDate(time.minusMonths(months));
    }

    /**
     * 给定日期增加月数
     */
    public static Date plusMonths(LocalDateTime time, long months) {
        return parseLocalToDate(time.plusMonths(months));
    }

    /**
     * 获取n天之前至今的时间段
     */
    public static Pair<Long, Long> getDaysAgoTime(Integer days) {
        LocalDate localDate = LocalDate.now().minusDays(days);
        long begin = localDate.atTime(0, 0, 0).toEpochSecond(ZoneOffset.ofHours(8));
        long end = localDate.atTime(23, 59, 59).toEpochSecond(ZoneOffset.ofHours(8));
        return ImmutablePair.of(begin, end);
    }

}
