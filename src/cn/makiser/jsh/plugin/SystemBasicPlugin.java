package cn.makiser.jsh.plugin;

import cn.makiser.jsh.Main;
import cn.makiser.jsh.R;
import cn.makiser.jsh.shell.Command;
import cn.makiser.jsh.shell.JavaShell;
import cn.makiser.jsh.shell.ShellRunnable;

import java.io.File;

public final class SystemBasicPlugin extends Plugin {
    public SystemBasicPlugin() {
        super("SystemBasicPlugin",
                "The basic instructions and running dependencies of JavaShell",
                R.s_strings.get("jsh-version"), 10);
        c("cd", (shell, commandLine, doEcho) -> {
            String[] d = commandLine.split(" ", 2);
            if(d.length == 2) {
                File pf = new File(d[1]);
                if(pf.exists()) {
                    shell.chdir(pf);
                } else {
                    shell.getPrinter().println(R.strings.get("cd.not_found"));
                }
            } else {

            }
        });
        c("exit", (shell, commandLine, doEcho) -> {
            Main.lc_printer.println(R.s_strings.get("exit"));
            Main.exit(0);
        });
        c("ls", (shell, commandLine, doEcho) -> {

        });
        c("jsh-about", (shell, commandLine, doEcho) -> {
            Main.lc_printer.println("JavaShell v1.0.1-Alpha\n" +
                    "这是一个想让Shell智能性和可扩展性增强的开源项目.\n" +
                    "详见GitHub: https://github.com/maxelblack/jsh");
        });
    }

    public void c(String name, ShellRunnable exec) {
        addCommand(new Command(name, exec));
    }
}
