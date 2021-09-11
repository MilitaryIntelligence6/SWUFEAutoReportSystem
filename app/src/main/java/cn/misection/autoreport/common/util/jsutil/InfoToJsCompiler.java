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
public class InfoToJsCompiler {

    private ReportInfo info;

    private List<String> scriptList;

    public InfoToJsCompiler(ReportInfo info) {
        this.info = info;
        init();
    }

    private void init() {
        scriptList = new ArrayList<>();
        scriptList.add(documentSetElementByIdIntSelectedIndex("szxq", String.valueOf(info.getCampus().ordinal())));
        scriptList.add(documentSetElementByIdStringValue("sbDate", info.getStartTime()));
        scriptList.add(documentSetElementByIdStringValue("edDate", info.getEndTime()));
        scriptList.add(documentSetElementByIdStringValue("qjmdd", info.getDestination()));
        scriptList.add(documentSetElementByIdStringValue("qjxc", info.getTransportation()));
        scriptList.add(documentSetElementByIdStringValue("qjyy", info.getReason()));
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

    public List<String> getScriptList() {
        return scriptList;
    }
}
