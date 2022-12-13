package com.yuu.android.component.logbook

import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


/**
 * @ClassName : LogbookDispatcher
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/11/28 下午4:29
 * @Record:
 */

class LogbookDispatcher {

    private val executorService: ExecutorService =
        ThreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.SECONDS, LinkedBlockingQueue())

    private val pendingLogbookRequest:ConcurrentLinkedQueue<LogbookRequest> = ConcurrentLinkedQueue()
    private var runningLogbookRequest: LogbookRequest?=null

    // 加入队列
    fun enqueue(request: LogbookRequest) {
        synchronized(this){
            pendingLogbookRequest.add(request)
            promoteAndExecute()
        }
    }

    // 出队和执行每一条请求
    private fun promoteAndExecute() {
        synchronized (this) {
            if (runningLogbookRequest == null && pendingLogbookRequest.size>0){
                runningLogbookRequest = pendingLogbookRequest.poll()
                runningLogbookRequest?.executeOn(executorService)
            }
        }
    }

    fun finished() {
        synchronized(this) {
            runningLogbookRequest = null
            promoteAndExecute()
        }
    }
}