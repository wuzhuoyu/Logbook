package com.yuu.android.component.logbook.model

import com.blankj.utilcode.util.TimeUtils
import com.yuu.android.component.logbook.utils.LogbookUtils
import com.yuu.android.component.logbook.utils.toJson

/**
 * @ClassName : CrashLogbookModel
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/11/23 下午8:44
 * @Record:
 */

data class CrashLogbookModel(
    val crashMessage: String? = "App发生了崩溃，请及时处理！",
): AndroidLogModel(), LogbookProtocol {

    /**
     * 日志链
     * @see用于关联日志
     * */
    var chain:Long?=null

    // 日志信息
    var logcat: String?=null

    /**
     * 日志tag
     * @see用于日志组合搜索
     * */
    var tag:String?=null

    /**
     * 日志标志
     * @see用于日志组合搜索
     * */
    var label:String?=null

    /**
     * 日志等级
     * @see用于日志组合搜索
     * */
    var priority:String?=null

    val deviceTimestamp:String = TimeUtils.getNowString()
    val deviceID:String = LogbookUtils.getDeviceID()

    override fun pipeline(): String? = toJson<CrashLogbookModel>()
}
