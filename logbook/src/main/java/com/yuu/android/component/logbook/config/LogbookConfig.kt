package com.yuu.android.component.logbook.config

import com.yuu.android.component.logbook.api.ILogbookConfigApi
import com.yuu.android.component.logbook.strategy.LogbookStrategy


/**
 * @ClassName : LogbookConfig
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/11/17 下午9:33
 * @Record:
 */

class LogbookConfig private constructor() {

    lateinit var loggerConfig: LoggerConfig
    var strategies: MutableList<LogbookStrategy> ?=null

    class Builder: ILogbookConfigApi {

        private var loggerConfig: LoggerConfig = LoggerConfig()
        private var strategies: MutableList<LogbookStrategy> = mutableListOf()

        override fun setLoggerConfig(loggerConfig: LoggerConfig): Builder {
            this.loggerConfig = loggerConfig
            return this
        }

        override fun addLogbookStrategy(strategy: LogbookStrategy): Builder {
            this.strategies.add(strategy)
            return this
        }

        override fun build(): LogbookConfig =
            LogbookConfig().apply {
                loggerConfig = this@Builder.loggerConfig
                strategies = this@Builder.strategies
            }
    }

}