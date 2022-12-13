package com.yuu.android.component.logbook.config


/**
 * @ClassName : LoggerModel
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/11/23 上午9:03
 * @Record:
 */

data class LoggerConfig(
    var tag: String = "Logbook",
    var isShowThreadInfo: Boolean = true,
    var methodCount: Int = 2,
    var methodOffset: Int = 0
)