package com.yuu.android.sample.logbook

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.yuu.android.component.logbook.Logbook
import java.io.PrintWriter
import java.io.StringWriter
import java.io.Writer
import kotlin.system.exitProcess


object ExceptionHelper : Thread.UncaughtExceptionHandler {
    @Volatile
    private var mDefaultHandler: Thread.UncaughtExceptionHandler? = null

    /**
     * 初始化默认异常捕获
     */
    fun init() {
        // 获取默认异常处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        // 将当前类设为默认异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        if (handleException(t,e)) {
            // 已经处理,APP重启
            restartApp()
        } else {
            // 如果不处理,则调用系统默认处理异常,弹出系统强制关闭的对话框
            if (mDefaultHandler != null) {
                mDefaultHandler!!.uncaughtException(t, e)
            }
        }
    }

    private fun handleException(t: Thread, e: Throwable?): Boolean {
        if (e == null) {
            return false
        }
        val writer: Writer = StringWriter()
        val pw = PrintWriter(writer)
        e.printStackTrace(pw)
        pw.close()
        val result: String = writer.toString()
        // 打印出错误日志
        Logbook.t("crash").label("ANR").e("当前线程$t",e)
        return true
    }

    /**
     * 1s后让APP重启
     */
    private fun restartApp() {
        val intent: Intent? =
            LogbookApplication.getApplication().packageManager.getLaunchIntentForPackage(
                LogbookApplication.getContext().packageName
            )
        val restartIntent: PendingIntent =
            PendingIntent.getActivity(LogbookApplication.getContext(), 0, intent, 0)
        val mgr: AlarmManager =
            LogbookApplication.getContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // 1秒钟后重启应用
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent)
        exitProcess(0)
    }

}