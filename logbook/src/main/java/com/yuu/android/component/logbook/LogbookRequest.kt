package com.yuu.android.component.logbook

import com.yuu.android.component.logbook.config.LogStorageLevel
import com.yuu.android.component.logbook.strategy.LogbookStrategy
import java.io.InterruptedIOException
import java.util.concurrent.ExecutorService
import java.util.concurrent.RejectedExecutionException


/**
 * @ClassName : LogbookRequest
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/12/1 下午3:16
 * @Record:
 */

class LogbookRequest : Runnable {

    var chain:Long?=null
    var label:String?=null
    var file:String?=null
    var priority: LogStorageLevel = LogStorageLevel.NULL
    var tag: String?=null
    var logcat: String?=null
    var throwable: Throwable?=null
    var strategy: LogbookStrategy?=null

    override fun run() {
        strategy?.verify(this)?.pipeline()?.let {
            strategy!!.record(LogbookResponse(it,this))
        }
    }

    fun executeOn(executorService: ExecutorService) {
        try {
            executorService.execute(this)
        } catch (e: RejectedExecutionException) {
            val ioException = InterruptedIOException("executor rejected")
            ioException.initCause(e)
            println(ioException.message)
        } finally {
            Logbook.dispatcher().finished()
        }
    }
}