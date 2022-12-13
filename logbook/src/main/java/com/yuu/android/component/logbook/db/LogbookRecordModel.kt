package com.yuu.android.component.logbook.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "table_logbook")
data class LogbookRecordModel(
    @PrimaryKey(autoGenerate = true) var guid:Int = 0,
    @ColumnInfo(name = "log_type") var logType: String?,
    @ColumnInfo(name = "log_info") var logInfo:String?,
    @ColumnInfo(name = "create_time") var createTime: Date?
){
    @Ignore
    constructor() : this(guid = 0,logType ="",logInfo ="",createTime = null)
}