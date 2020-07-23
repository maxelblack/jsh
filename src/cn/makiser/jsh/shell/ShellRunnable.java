package cn.makiser.jsh.shell;

import java.io.IOException;

public interface ShellRunnable {
    void run(JavaShell shell, Parameter parameter, boolean doEcho) throws Exception;
}
