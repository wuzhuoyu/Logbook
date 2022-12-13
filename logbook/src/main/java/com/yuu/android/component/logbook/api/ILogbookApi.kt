package com.yuu.android.component.logbook.api

import com.yuu.android.component.logbook.Logbook
import com.yuu.android.component.logbook.config.LogbookConfig


/**
 * @ClassName : ILogbookApi
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/11/17 下午7:14
 * @Record:
 */

interface ILogbookApi {

    /** 初始化配置 */
    fun init(logbookConfig: LogbookConfig)

    /**
     * 日志特殊标记
     * @see 一次性
     * */
    fun label(label: String?): Logbook

    /**
     * 日志记录至文件
     * @see 一次性
     * */
    fun file(fileName:String?): Logbook

    /**
     * 日志是否记录
     * @see 一次性
     * */
    fun record(isRecord:Boolean):Logbook

}

