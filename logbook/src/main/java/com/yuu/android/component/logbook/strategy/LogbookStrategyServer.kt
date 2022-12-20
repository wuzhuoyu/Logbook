package com.yuu.android.component.logbook.strategy

import com.yuu.android.component.logbook.LogbookRequest
import com.yuu.android.component.logbook.LogbookResponse
import com.yuu.android.component.logbook.config.LogStorageLevel
import com.yuu.android.component.logbook.model.CrashLogbookModel
import com.yuu.android.component.logbook.model.DefaultLogbookModel
import com.yuu.android.component.logbook.model.LogbookProtocol
import com.yuu.android.component.logbook.utils.LogbookUtils


/**
 * @ClassName : DefaultLogbookStrategy
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/12/8 下午12:26
 * @Record:
 */

class LogbookStrategyServer(private val api:String) : LogbookStrategy {

    override fun recordable(): Boolean = true

    override fun verify(request: LogbookRequest): LogbookProtocol? {
        if (request.priority.level < LogStorageLevel.DEBUG.level) return null

        if (request.label == "ANR") {
            return CrashLogbookModel(
                crashMessage = request.throwable?.message
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
        LogbookUtils.post(postUrl = api,response.log)
    }
}