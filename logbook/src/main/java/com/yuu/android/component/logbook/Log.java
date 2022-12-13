package com.yuu.android.component.logbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ObjectUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.yuu.android.component.logbook.api.ILogApi;
import com.yuu.android.component.logbook.config.LoggerConfig;
import com.yuu.android.component.logbook.model.LogcatModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Objects;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @ClassName : Log
 * @Description:
 * @Author: wuzhuoyu
 * @Date: 2022/12/13 下午2:56
 * @Record:
 */

public abstract class Log implements ILogApi {
    private String defaultTag = "Logbook";

    protected ThreadLocal<String> onceTag = new ThreadLocal<String>();

    private LogCallback callback;

    protected void initLogger(LoggerConfig loggerConfig){
        defaultTag = loggerConfig.getTag();
        FormatStrategy formatStrategy  = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(loggerConfig.isShowThreadInfo()) //（可选）是否显示线程信息。 默认值为true
                .methodCount(loggerConfig.getMethodCount()) // （可选）要显示的方法行数。 默认2
                .methodOffset(loggerConfig.getMethodOffset()) // （可选）设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
                .tag(loggerConfig.getTag()) //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    protected void logcatCallback(LogCallback logCallback){
        callback = logCallback;
    }

    @NonNull
    @Override
    public Log t(@Nullable String tag) {
        if (!Objects.isNull(tag)){
            onceTag.set(tag);
        }
        return this;
    }

    @Override
    public void d(@Nullable String message, @Nullable Object... args) {
        log(Logger.DEBUG, getTag(), createMessage(message, args), null);
    }

    @Override
    public void i(@Nullable String message, @Nullable Object... args) {
        log(Logger.INFO, getTag(), createMessage(message, args), null);
    }

    @Override
    public void w(@Nullable String message, @Nullable Object... args) {
        log(Logger.WARN, getTag(), createMessage(message, args), null);
    }

    @Override
    public void e(@Nullable String message, @Nullable Object... args) {
        log(Logger.ERROR, getTag(), createMessage(message, args), null);
    }

    @Override
    public void e(@Nullable String message, @Nullable Throwable throwable, @Nullable Object... args) {
        log(Logger.ERROR, getTag(), createMessage(message, args), throwable);
    }

    @Override
    public void v(@Nullable String message, @Nullable Object... args) {
        log(Logger.VERBOSE,getTag(),createMessage(message, args),null);
    }

    @Override
    public void json(@Nullable String json) {
        if (ObjectUtils.isEmpty(json)) {
            d("Empty/Null json content");
            return;
        }
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String message = jsonObject.toString(2);
                d(message);
                return;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(2);
                d(message);
                return;
            }
            e("Invalid Json");
        } catch (JSONException e) {
            e("Invalid Json");
        }

    }

    @Override
    public void xml(@Nullable String xml) {
        if (ObjectUtils.isEmpty(xml)) {
            d("Empty/Null xml content");
            return;
        }
        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            d(xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
        } catch (TransformerException e) {
            e("Invalid xml");
        }
    }

    private void log(Integer priority,String tag,String message,@Nullable Throwable throwable){
        Logger.log(priority,tag,message,throwable);
        callback.invoke(new LogcatModel(LogcatModel.priorityConvertor(priority),tag,message,throwable));
    }

    private String getTag(){
        String tag = onceTag.get();
        if (ObjectUtils.isNotEmpty(tag)){
            onceTag.remove();
            return tag;
        }
        return defaultTag;
    }

    @NonNull private String createMessage(@NonNull String message, @Nullable Object... args) {
        return args == null || args.length == 0 ? message : String.format(message, args);
    }

    protected interface LogCallback {
        void invoke(LogcatModel logModel);
    }
}
