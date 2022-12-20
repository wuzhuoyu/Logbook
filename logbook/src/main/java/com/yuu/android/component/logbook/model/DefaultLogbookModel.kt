package com.yuu.android.component.logbook.model

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.TimeUtils
import com.yuu.android.component.logbook.utils.LogbookUtils
import com.yuu.android.component.logbook.utils.toJson


/**
 * @ClassName : DefaultLogModel
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/12/8 下午6:24
 * @Record:
 */

open class DefaultLogbookModel: LogbookProtocol {
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

    /**
     * 日志错误
     * */
    var throwable:String?=null

    val appVersionName:String = AppUtils.getAppVersionName()
    val deviceModel:String = DeviceUtils.getModel()
    val deviceTimestamp:String = TimeUtils.getNowString()
    val deviceID:String = LogbookUtils.getDeviceID()


    override fun pipeline(): String? = toJson<DefaultLogbookModel>()
}