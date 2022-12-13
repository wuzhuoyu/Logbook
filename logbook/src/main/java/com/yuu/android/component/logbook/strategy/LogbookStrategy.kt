package com.yuu.android.component.logbook.strategy

import com.yuu.android.component.logbook.LogbookRequest
import com.yuu.android.component.logbook.LogbookResponse
import com.yuu.android.component.logbook.model.LogbookProtocol


/**
 * @ClassName : LogbookStrategy
 * @Description: 日志记录策略
 * @Author: wuzhuoyu
 * @Date: 2022/12/8 下午12:14
 * @Record:
 */

interface LogbookStrategy {

    /** 是否记录*/
    fun recordable():Boolean

    /** 校验*/
    fun verify(request: LogbookRequest): LogbookProtocol?

    /** 记录*/
    fun record(response: LogbookResponse)

}