package cn.makiser.jsh.shell;

public interface ShellRunnable {
    void run(JavaShell shell, String commandLine, boolean doEcho);
}
