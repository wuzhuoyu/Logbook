package com.yuu.android.component.logbook.api

import com.yuu.android.component.logbook.config.LogbookConfig
import com.yuu.android.component.logbook.config.LoggerConfig
import com.yuu.android.component.logbook.strategy.LogbookStrategy


/**
 * @InterfaceName : ILogbookConfigApi
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/11/17 下午9:32
 * @Record:
 */

interface ILogbookConfigApi {

    /** 设置Logger日志输出配置*/
    fun setLoggerConfig(loggerConfig: LoggerConfig): LogbookConfig.Builder

    /** 设置日志记录策略 */
    fun addLogbookStrategy(strategy: LogbookStrategy): LogbookConfig.Builder

    /** 构建日志配置对象*/
    fun build(): LogbookConfig

}