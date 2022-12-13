package com.yuu.android.component.logbook.config



/**
 * @ClassName : LogStorageLevel
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/11/17 下午7:14
 * @Record:
 */
enum class LogStorageLevel(var level: Int,var meaning:String) {
    NULL(-1,"NULL"),
    VERBOSE(2,"VERBOSE"),
    DEBUG(3,"DEBUG"),
    INFO(4,"INFO"),
    WARN(5,"WARN"),
    ERROR(6,"ERROR"),
}
