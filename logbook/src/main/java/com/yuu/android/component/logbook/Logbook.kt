package com.yuu.android.component.logbook

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.yuu.android.component.logbook.api.ILogbookApi
import com.yuu.android.component.logbook.config.LogbookConfig
import com.yuu.android.component.logbook.strategy.LogbookStrategy


/**
 * 日志操作类
 * */
object Logbook:Log(), ILogbookApi {

    private val onceLabel = ThreadLocal<String>()
    private val onceFileName = ThreadLocal<String>()
    private val onceRecord = ThreadLocal<Boolean>()
    private val dispatcher by lazy { LogbookDispatcher() }
    private var strategies: List<LogbookStrategy> ?=null

    override fun init(application: Application, logbookConfig: LogbookConfig) {
        // 初始化logcat样式

        Utils.init(application)
        initLogger(logbookConfig.loggerConfig)
        initLogbook(logbookConfig.strategies)
    }

    private fun initLogbook(strategies: MutableList<LogbookStrategy>?) {
        // 设置日志策略
        Logbook.strategies = strategies
        // 设置日志打印回调
        logcatCallback { log ->
            // 判断日志是否需要记录
            if (!getRecord()) return@logcatCallback
            // 创建记录对象

            val l = getLabel()
            val f = getFile()

            strategies?.forEach { s->
                // 如果不记录无需要加入队列
                if (!s.recordable()) return@forEach

                val request = LogbookRequest().apply {
                    label = l
                    file = f
                    priority = log.priority
                    tag = log.tag
                    logcat = log.logcat
                    throwable = log.throwable
                    strategy = s
                }
                dispatcher.enqueue(request)
            }
        }
    }

    override fun t(tag: String?): Logbook {
        tag?.let { onceTag.set(tag) }
        return this;
    }

    /** 用于记录特殊日志标签*/
    override fun label(label: String?): Logbook {
        if (!label.isNullOrEmpty()) onceLabel.set(label)
        return this
    }

    override fun file(fileName: String?): Logbook {
        if (!fileName.isNullOrEmpty())  onceFileName.set(fileName)
        return this
    }

    override fun record(isRecord: Boolean): Logbook {
        onceRecord.set(isRecord)
        return this
    }

    private fun getLabel(): String {
        val label = onceLabel.get()
        if (label != null) {
            onceLabel.remove()
            return label
        }
        return "DEFAULT_LOG"
    }

    private fun getFile(): String? {
        val fileName = onceFileName.get()
        if (fileName != null) {
            onceFileName.remove()
            return fileName
        }
        return null
    }

    private fun getRecord(): Boolean {
        val isR = onceRecord.get()
        isR?.let {
            if (!isR) {
                onceRecord.remove()
                return isR
            }
        }
        return true
    }

    internal fun dispatcher() = dispatcher

}