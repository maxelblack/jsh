package cn.makiser.jsh.shell;

import cn.makiser.jsh.io.Printer;
import cn.makiser.jsh.plugin.Plugin;

public class Command {
    private final String name;
    private ShellRunnable exec;
    private Plugin plugin;

    public Command(String name) {
        this.name = name;
    }
    public Command(String name, ShellRunnable exec) {
        this.name = name;
        this.exec = exec;
    }

    //get&set
    public String getName() {
        return name;
    }
    public ShellRunnable getExec() {
        return exec;
    }
    public Plugin getPlugin() {
        return plugin;
    }
    public void setExec(ShellRunnable exec) {
        this.exec = exec;
    }

    protected void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }
}
