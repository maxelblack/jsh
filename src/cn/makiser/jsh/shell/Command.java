package cn.makiser.jsh.shell;

import cn.makiser.jsh.plugin.Plugin;

public class Command {
    private final String name;
    private ShellRunnable runnable;
    private Plugin plugin;

    public Command(String name) {
        this.name = name;
    }
    public Command(String name, ShellRunnable runnable) {
        this.name = name;
        this.runnable = runnable;
    }

    //get&set
    public String getName() {
        return name;
    }
    public ShellRunnable getRunnable() {
        return runnable;
    }
    public Plugin getPlugin() {
        return plugin;
    }
    public void setRunnable(ShellRunnable runnable) {
        this.runnable = runnable;
    }

    protected void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }
}
