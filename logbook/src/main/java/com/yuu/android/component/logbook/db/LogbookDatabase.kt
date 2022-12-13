package com.yuu.android.component.logbook.db

import android.content.Context
import androidx.room.*
import com.yuu.android.component.logbook.db.converter.DateConverter


/**
 * @ClassName : LogbookRoom
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/11/25 下午12:20
 * @Record:
 */

@Database(entities = [LogbookRecordModel::class], version = 1,exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class LogbookDatabase: RoomDatabase() {

    internal abstract fun logbookDao(): LogbookDao

    companion object{
        @Volatile private var db: LogbookDatabase?=null

        fun getDatabase(): LogbookDatabase? = db

        fun buildDatabase(context: Context, dbName: String) {
            db = Room
                .databaseBuilder(context, LogbookDatabase::class.java, dbName)
                .build()
        }
    }


}