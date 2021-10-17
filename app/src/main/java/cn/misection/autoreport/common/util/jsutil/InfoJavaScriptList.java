package cn.misection.autoreport.common.util.jsutil;

import java.util.ArrayList;
import java.util.List;

import cn.misection.autoreport.report.entity.ReportInfo;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName InfoToJsCompiler
 * @Description TODO
 * @CreateTime 2021年09月07日 17:58:00
 */
public class InfoJavaScriptList extends ArrayList<String>{

    private ReportInfo info;

    public InfoJavaScriptList(ReportInfo info) {
        this.info = info;
        init();
    }

    private void init() {
        add(documentSetElementByIdIntSelectedIndex("szxq", String.valueOf(info.getCampus().ordinal())));
        add(documentSetElementByIdStringValue("sbDate", info.getStartTime()));
        add(documentSetElementByIdStringValue("edDate", info.getEndTime()));
        add(documentSetElementByIdStringValue("qjmdd", info.getDestination()));
        add(documentSetElementByIdStringValue("qjxc", info.getTransportation()));
        add(documentSetElementByIdStringValue("qjyy", info.getReason()));
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
}
