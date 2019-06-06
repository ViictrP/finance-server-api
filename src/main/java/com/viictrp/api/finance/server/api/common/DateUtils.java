package com.viictrp.api.finance.server.api.common;

import com.viictrp.api.finance.server.api.domain.enums.MesType;
import org.joda.time.DateTime;

import java.time.LocalDate;
import java.util.Calendar;

public class DateUtils {

    private DateUtils() {

    }

    public static MesType getCurrentMonthName() {
        return MesType.customValueOfWithName(DateTime.now().toString("MMMM"));
    }

    public static MesType getMonthName(DateTime data) {
        return MesType.customValueOfWithName(data.toString("MMMM"));
    }

    public static boolean isTodayBeforeThan(Integer day) {
        return day > LocalDate.now().getDayOfMonth();
    }

    public static boolean isTodayAfterThan(Integer day) {
        return day < LocalDate.now().getDayOfMonth();
    }

    public static boolean isLastDayOfMonth() {
        return LocalDate.now().getDayOfMonth() == Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
