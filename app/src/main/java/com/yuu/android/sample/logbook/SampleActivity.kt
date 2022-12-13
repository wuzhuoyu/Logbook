package com.yuu.android.sample.logbook

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.yuu.android.component.logbook.Logbook
import com.yuu.android.component.logbook.config.LoggerConfig

class SampleActivity : AppCompatActivity() {

    var il = true
    var count = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        var lc = LoggerConfig()
        val xmlDate = """<province id="01" name="北京">  
    <city id="0101" name="北京">  
      <county id="010101" name="北京" weatherCode="101010100"/>  
      <county id="010102" name="海淀" weatherCode="101010200"/>  
      <county id="010103" name="朝阳" weatherCode="101010300"/>  
      <county id="010110" name="石景山" weatherCode="101011000"/>  
    </city>  
  </province>  """


        findViewById<Button>(R.id.btn_log).setOnClickListener {
//            Logbook.t("wuzhuoyu").d("T开头",lc.tag)
//            Logbook.v("v开头")
//            Logbook.d("D开头", lc)
//            Logbook.i("I开头", lc)
//            Logbook.w("W开头", lc)
//            Logbook.e("E开头", null, lc)
//            Logbook.json(GsonUtil.toJson<LoggerConfig>(lc))
//            Logbook.xml(xmlDate)

//            Logbook.label("大哥1").t("dad").d("问就是帅")
//            Logbook.w("W开头", lc)
//            Logbook.label("大哥2").d("问就是帅")
//
            Logbook.t("我是tag").file("我是file").label("我是label").d("我是debug日志")
            Logbook.t("我是ANR日志的tag").file("我是ANR日志的file").label("ANR")
                .e("假设我是ANR日志", Exception("这里是一个异常信息！"))


//            (1..19).forEach {
//
//            }

//            while (true){
//                val model = LoggerConfig(methodOffset = count)
//                Logbook.label("$count").d(model.toJson<LoggerConfig>()?:"")
//                count++
//            }
//            val model = LoggerConfig()
//            Logbook.label("$count").unRecord().file("wuzhuoyu").d(model.toJson<LogbookModel>()?:"")

//            Logbook.label("$count").file("wuzhuoyu").d("需要记录")
//            Logbook.label("$count").record(false).file("wuzhuoyu").d("不需要记录")


        }

        findViewById<Button>(R.id.btn_log_to_file).setOnClickListener {
        }
        findViewById<Button>(R.id.btn_log_to_room).setOnClickListener { }
        findViewById<Button>(R.id.btn_log_file_to_server).setOnClickListener { }
        findViewById<Button>(R.id.btn_log_room_to_server).setOnClickListener {
        }
        findViewById<Button>(R.id.btn_crash_log_to_server).setOnClickListener {
//            SystemClock.sleep(1000*30)
            //bug
            val list = ArrayList<Int>()
            val d = list[5]
        }

    }
}