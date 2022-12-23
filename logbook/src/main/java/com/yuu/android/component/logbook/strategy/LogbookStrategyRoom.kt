package com.yuu.android.component.logbook.strategy

import android.app.Application
import com.blankj.utilcode.util.TimeUtils
import com.yuu.android.component.logbook.BuildConfig
import com.yuu.android.component.logbook.LogbookRequest
import com.yuu.android.component.logbook.LogbookResponse
import com.yuu.android.component.logbook.config.LogStorageLevel
import com.yuu.android.component.logbook.db.LogbookDatabase
import com.yuu.android.component.logbook.db.LogbookRecordModel
import com.yuu.android.component.logbook.model.CrashLogbookModel
import com.yuu.android.component.logbook.model.DefaultLogbookModel
import com.yuu.android.component.logbook.model.LogbookProtocol


/**
 * @ClassName : DefaultLogbookStrategy
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/12/8 下午12:26
 * @Record:
 */

class LogbookStrategyRoom(private val application: Application,private val dbName:String = "logbook") :
    LogbookStrategy {

    init {
        LogbookDatabase.buildDatabase(application,dbName)
    }

    override fun recordable(): Boolean = !BuildConfig.DEBUG

    override fun verify(request: LogbookRequest): LogbookProtocol? {
        if (request.priority.level < LogStorageLevel.VERBOSE.level) return null

        if (request.label == "ANR") {
            return CrashLogbookModel(
                crashMessage = request.throwable?.message ?: "App发生了崩溃，请及时处理！"
            ).apply {
                chain = request.chain
                logcat = request.logcat
                tag = request.tag
                label = request.label
                priority = request.priority.meaning
            }
        }

        return DefaultLogbookModel().apply {
            chain = request.chain
            logcat = request.logcat
            tag = request.tag
            label = request.label
            priority = request.priority.meaning
        }
    }

    override fun record(response: LogbookResponse) {
        val model = LogbookRecordModel(
            logType = response.request.label,
            logInfo = response.log,
            createTime = TimeUtils.getNowDate()
        )
        LogbookDatabase.getDatabase()?.logbookDao()?.insertLog(model)
    }
}