package com.yuu.android.sample.logbook

import android.app.Application
import android.content.Context
import com.yuu.android.component.logbook.Logbook
import com.yuu.android.component.logbook.config.LogbookConfig
import com.yuu.android.component.logbook.config.LoggerConfig
import com.yuu.android.component.logbook.strategy.LogbookStrategyFile
import com.yuu.android.component.logbook.strategy.LogbookStrategyRoom
import com.yuu.android.component.logbook.strategy.LogbookStrategyServer


/**
 * @ClassName : LogbookApplication
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/11/17 下午5:04
 * @Record:
 */

class LogbookApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ExceptionHelper.init()

        val logbookConfig = LogbookConfig.Builder()
            .setLoggerConfig(LoggerConfig())
            .addLogbookStrategy(LogbookStrategyFile(externalCacheDir?.path?:""))
            .addLogbookStrategy(LogbookStrategyRoom(this))
            .addLogbookStrategy(LogbookStrategyServer("http://192.168.100.100:4422"))
            .build()

        Logbook.init(this,logbookConfig)
    }

    companion object{
        var _context: Application? = null

        fun getContext(): Context {
            return _context!!
        }

        fun getApplication(): Application {
            return _context!!
        }
    }

}