package com.yuu.android.component.logbook.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuu.android.component.logbook.db.LogbookRecordModel


/**
 * @InterfaceName : LogbookDao
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/11/25 下午12:17
 * @Record:
 */

@Dao
internal interface LogbookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLog(logbookRoomModel: LogbookRecordModel)

    @Query("DELETE FROM TABLE_LOGBOOK where guid=:guid")
    fun deleteLogByGuid(guid: Int)

    @Query("SELECT * FROM TABLE_LOGBOOK LIMIT 10")
    fun getLogByPageSize():MutableList<LogbookRecordModel>
}