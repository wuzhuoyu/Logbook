package com.yuu.android.component.logbook.model

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.LanguageUtils
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.ProcessUtils
import com.blankj.utilcode.util.RomUtils
import java.util.*


/**
 * @ClassName : AndroidLogModel
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/11/23 下午8:44
 * @Record:
 */

abstract class AndroidLogModel {
    // 设备系统版本
    val sdkVersionName: String = DeviceUtils.getSDKVersionName()
    // 设备系统版本码
    val sdkVersionCode: Int = DeviceUtils.getSDKVersionCode()
    // 设备AndroidID
    val androidID: String = DeviceUtils.getAndroidID()
    // 设备 MAC 地址
    val macAddress: String = DeviceUtils.getMacAddress()
    // 设备厂商
    val manufacturer: String = DeviceUtils.getManufacturer()
    // 设备型号
    val deviceModel: String = DeviceUtils.getModel()
    // 判断是否是平板
    val isTablet: Boolean = DeviceUtils.isTablet()
    //  开发者选项是否打开
    val isDevelopmentSettingsEnabled: Boolean = DeviceUtils.isDevelopmentSettingsEnabled()
    // 获取唯一设备 ID
    val uniqueDeviceId: String = DeviceUtils.getUniqueDeviceId()
    // App 包名
    val appPackageName: String = AppUtils.getAppPackageName()
    // App 版本名称
    val appVersionName: String = AppUtils.getAppVersionName()
    // App 版本码
    val appVersionCode: Int = AppUtils.getAppVersionCode()
    // 获取设置的语言
    val systemLanguage: Locale = LanguageUtils.getSystemLanguage()
    // 获取当前网络类型
    val networkType: NetworkUtils.NetworkType = NetworkUtils.getNetworkType()
    // 获取 IP 地址
    val ipAddress: String = NetworkUtils.getIPAddress(true)
    // 获取当前进程名称
    val currentProcessName: String = ProcessUtils.getCurrentProcessName()
    // 获取 ROM 信息
    val romInfo: RomUtils.RomInfo =RomUtils.getRomInfo()
}