package com.yuu.android.component.logbook.api

import com.yuu.android.component.logbook.Log


/**
 * @InterfaceName : ILogApi
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/11/22 下午7:16
 * @Record:
 */

interface ILogApi {

    /** 设置单次日志标记*/
    fun t(tag:String?): Log

    /** 调试日志*/
    fun d(message: String?, vararg args: Any?)

    /** 信息日志*/
    fun i(message: String?, vararg args: Any?)

    /** 警告日志*/
    fun w(message: String?, vararg args: Any?)

    /** 错误日志*/
    fun e(message: String?,vararg args: Any?)

    /** 错误日志*/
    fun e(message: String?, throwable: Throwable?,vararg args: Any?)

    /** 详细日志*/
    fun v(message: String?, vararg args: Any?)

    /** json日志*/
    fun json(json:String?)

    /** xml日志*/
    fun xml(xml:String?)
}