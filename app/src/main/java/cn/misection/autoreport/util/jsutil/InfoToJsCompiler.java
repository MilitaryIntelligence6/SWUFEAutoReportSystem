package cn.misection.autoreport.util.jsutil;

import android.util.Log;

import cn.misection.autoreport.BuildConfig;
import cn.misection.autoreport.entity.ReportInfo;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName InfoToJsCompiler
 * @Description TODO
 * @CreateTime 2021年09月07日 17:58:00
 */
public class InfoToJsCompiler {

    private ReportInfo info;

    private String script;

    public InfoToJsCompiler(ReportInfo info) {
        this.info = info;
        init();
    }

    private void init() {
        script = JavaScriptUtil.decoratedWithJsUrl(
                new StringBuilder()
                        .append(documentSetElementByIdIntSelectedIndex("szxq", String.valueOf(info.getCampus().ordinal())))
                        .append(documentSetElementByIdStringValue("sbDate", info.getStartTime()))
                        .append(documentSetElementByIdStringValue("edDate", info.getEndTime()))
                        .append(documentSetElementByIdStringValue("qjmdd", info.getDestination()))
                        .append(documentSetElementByIdStringValue("qjxc", info.getTransportation()))
                        .append(documentSetElementByIdStringValue("qjyy", info.getReason()))
                        .toString()
        );
        if (BuildConfig.DEBUG) {
            Log.e(getClass().getName(), "script == " + script);
        }
    }

    private String documentSetElementByIdIntSelectedIndex(String id, String value) {
        return String.format(
                "document.getElementById(\"%s\").selectedIndex = %s;\n",
                id, value);
    }


    private String documentSetElementByIdStringValue(String id, String value) {
        return String.format(
                "document.getElementById(\"%s\").value = \"%s\";\n",
                id, value);
    }

    public String getScript() {
        return script;
    }
}
