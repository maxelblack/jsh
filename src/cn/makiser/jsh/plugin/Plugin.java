package cn.makiser.jsh.plugin;

import cn.makiser.jsh.shell.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Plugin {
    private final String name;
    private final String info;
    private final String ver_name;
    private final Integer ver_code;

    private final List<Command> commands = new ArrayList<>();

    protected Plugin(String name, String info, String ver_name, Integer ver_code) {
        this.name = name;
        this.info = info;
        this.ver_name = ver_name;
        this.ver_code = ver_code;
    }

    //add
    public void addCommand(Command command) {
        this.commands.add(command);
    }
    public void addCommands(Command... commands) {
        this.commands.addAll(Arrays.asList(commands));
    }

    //get&set
    public String getName() {
        return name;
    }
    public String getInfo() {
        return info;
    }
    public String getVersionName() {
        return ver_name;
    }
    public Integer getVersionCode() {
        return ver_code;
    }
    public List<Command> getCommands() {
        return commands;
    }
}
