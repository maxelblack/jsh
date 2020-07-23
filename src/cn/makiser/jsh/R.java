package cn.makiser.jsh;

import java.util.HashMap;
import java.util.Map;

public class R {
    public static final Map<String, String> strings = new HashMap<>();
    public static final Map<String, String> s_strings = new HashMap<>();

    public static void freshAll() {
        s_strings.put("jsh-version", "Alpha 1.0.1");
        strings.put("shell.cmd_conflict1", "插件指令发生冲突, 是否继续加载?\n" +
                "若继续, 已载入的指令将被即将载入的覆盖.");
        strings.put("shell.cmd_conflict2", "您可以用指令 'jsh-info plugin.command' " +
                "输出各插件注册指令, 查看在何处发生冲突.");
        strings.put("shell.cmd_not_found", "未找到该指令, 请检查输入是否有误(区分大小写).");
        strings.put("shell.plugin_load", "已载入 $num 个插件");
        strings.put("shell.java_exception", "Java内部的程序异常:");
        strings.put("cd.not_found", "目录不存在");
        s_strings.put("at", "在");
        s_strings.put("and", "和");
        s_strings.put("info", "[信息] ");
        s_strings.put("warn", "[警告] ");
        s_strings.put("err", "[错误] ");
        s_strings.put("stop", "终止");
        s_strings.put("exit", "退出");
    }
}
