package com.arcticair.mssctoyservice.web.mapper;

import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
@Controller
public class DateMapper {
    public OffsetDateTime asOffset(Timestamp timestamp){
        if (timestamp != null){
            return OffsetDateTime.of(timestamp.toLocalDateTime().getYear(),
                    timestamp.toLocalDateTime().getMonthValue(),
                    timestamp.toLocalDateTime().getDayOfMonth(),
                    timestamp.toLocalDateTime().getHour(),
                    timestamp.toLocalDateTime().getMinute(),
                    timestamp.toLocalDateTime().getSecond(),
                    timestamp.toLocalDateTime().getNano(),
                    ZoneOffset.UTC
            );
        } else {
            return null;
        }
    }

    public Timestamp getTimestamp(OffsetDateTime offsetDateTime){
        if (offsetDateTime != null){
            return Timestamp.valueOf(offsetDateTime.toLocalDateTime());
        } else {
            return null;
        }
    }
}
