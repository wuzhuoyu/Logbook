package com.yuu.android.component.logbook.utils

import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @Description:
 * @Author: leisiyu
 * @CreateDate: 2021/12/29
 */
internal object LogbookUtils {

    /**
     * 获取设备ID(设备硬件编号)
     */
    fun getDeviceID():String{
        val c = Class.forName("android.os.SystemProperties")
        val get = c.getMethod("get", String::class.java)
        val deviceId = get.invoke(c, "ro.serialno") as String?
        return if (deviceId == null || deviceId.isBlank()) com.blankj.utilcode.util.DeviceUtils.getAndroidID() else deviceId
    }


    /**
     * 文件数据写入（如果文件夹和文件不存在，则先创建，再写入）
     * @param filePath
     * @param content
     * @param flag true:如果文件存在且存在内容，则内容换行追加；false:如果文件存在且存在内容，则内容替换
     */
    fun fileLinesWrite(content: String?,path:String) {
        val date = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
        val time = dateFormat.format(date)
        var fw: FileWriter? = null
        try {
            val file = File(path)
            //如果文件夹不存在，则创建文件夹
            if (!file.parentFile.exists()) {
                file.parentFile.mkdirs()
            }
            fw = if (!file.exists()) { //如果文件不存在，则创建文件,写入第一行内容
                file.createNewFile()
                FileWriter(file)
            } else {
                //如果文件存在,则追加或替换内容
                FileWriter(file, true)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val pw = fw?.let { PrintWriter(it) }
        pw?.println("${time}-----> $content")
        pw?.flush()
        try {
            fw?.flush()
            pw?.close()
            fw?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    /**
     * http post 请求
     *
     * */
    @Throws(Exception::class)
    fun post(
        postUrl: String?,
        params: String?,
        contentType: String="application/json",
        encoding: String = "UTF-8"
    ): String {
        try {
            val url = URL(postUrl)
            // 打开和URL之间的连接
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            // 设置通用的请求属性
            connection.setRequestProperty("Content-Type", contentType)
            connection.setRequestProperty("Connection", "Keep-Alive")
            connection.useCaches = false
            connection.doOutput = true
            connection.doInput = true

            // 得到请求的输出流对象
            val out = DataOutputStream(connection.outputStream)
            out.write(params?.toByteArray(charset(encoding)))
            out.flush()
            out.close()

            // 建立实际的连接
            connection.connect()
            // 定义 BufferedReader输入流来读取URL的响应
            var reader: BufferedReader? = null
            reader = BufferedReader(
                InputStreamReader(connection.inputStream, encoding)
            )
            var result = ""
            var getLine: String?
            while (reader.readLine().also { getLine = it } != null) {
                result += getLine
            }
            reader.close()
            return result
        }catch (e:Exception){
            return "error"
        }
    }
}