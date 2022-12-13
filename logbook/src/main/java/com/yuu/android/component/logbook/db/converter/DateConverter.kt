package com.yuu.android.component.logbook.db.converter

import androidx.room.TypeConverter
import java.util.*


/**
 * @ClassName : DateConverter
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/12/6 下午8:36
 * @Record:
 */

class DateConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}