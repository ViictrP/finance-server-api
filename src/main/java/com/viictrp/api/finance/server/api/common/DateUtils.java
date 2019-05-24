package com.viictrp.api.finance.server.api.common;

import com.viictrp.api.finance.server.api.domain.enums.MesType;
import org.joda.time.DateTime;

public class DateUtils {

    private DateUtils() {

    }

    public static MesType getMonthName() {
        return MesType.customValueOfWithName(DateTime.now().toString("MMMM"));
    }

}
