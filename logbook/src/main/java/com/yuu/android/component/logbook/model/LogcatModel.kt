package com.yuu.android.component.logbook.model

import com.yuu.android.component.logbook.config.LogStorageLevel
import com.orhanobut.logger.Logger


data class LogcatModel(
    val priority: LogStorageLevel,
    val tag: String,
    val logcat: String,
    val throwable: Throwable?
){
   companion object{
       @JvmStatic
       fun priorityConvertor(p: Int): LogStorageLevel {
           return when(p){
               Logger.VERBOSE-> LogStorageLevel.VERBOSE
               Logger.DEBUG -> LogStorageLevel.DEBUG
               Logger.INFO -> LogStorageLevel.INFO
               Logger.WARN-> LogStorageLevel.WARN
               Logger.ERROR -> LogStorageLevel.ERROR
               else -> LogStorageLevel.NULL
           }
       }
   }
}
