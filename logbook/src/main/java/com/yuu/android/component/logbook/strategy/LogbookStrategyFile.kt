package com.yuu.android.component.logbook.strategy

import com.yuu.android.component.logbook.LogbookRequest
import com.yuu.android.component.logbook.LogbookResponse
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

class LogbookStrategyFile(private val path: String) : LogbookStrategy {

    override fun recordable(): Boolean = true

    override fun verify(request: LogbookRequest): LogbookProtocol? {
        if (request.file == null) return null

        if (request.label == "ANR") {
            return CrashLogbookModel(
                errorMessage = request.throwable?.message ?: "App发生了崩溃，请及时处理！"
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
        LogbookUtils.fileLinesWrite(response.log, "$path/${response.request.file}.logbook")
    }
}