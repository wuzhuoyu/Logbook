package com.yuu.android.component.logbook.utils

import com.blankj.utilcode.util.GsonUtils


/**
 * @ClassName : Extentions
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/12/4 下午5:58
 * @Record:
 */

internal inline fun <reified T> Any.toJson():String?{
    return GsonUtils.toJson(this)
}

internal inline fun <reified T> String.fromJson():T?{
   return GsonUtils.fromJson<T>(this,T::class.java)
}

