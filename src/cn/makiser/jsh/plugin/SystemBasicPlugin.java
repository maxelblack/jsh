package cn.makiser.jsh.plugin;

import cn.makiser.jsh.Main;
import cn.makiser.jsh.R;
import cn.makiser.jsh.shell.Command;
import cn.makiser.jsh.shell.JavaShell;
import cn.makiser.jsh.shell.ShellRunnable;
import cn.makiser.jsh.shell.Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public final class SystemBasicPlugin extends Plugin {
    public SystemBasicPlugin() {
        super("SystemBasicPlugin",
                "The basic instructions and running dependencies of JavaShell",
                R.s_strings.get("jsh-version"), 10);
        c("cd", (shell, parameter, doEcho) -> {
            String[] d = parameter.toString().split(" ", 2);
            if(d.length == 2) {
                File pf = new File(d[1]);
                if(pf.exists()) {
                    shell.chdir(d[1]);
                } else {
                    shell.getPrinter().println(R.strings.get("cd.not_found"));
                }
            } else {
                shell.getPrinter().println(R.strings.get("cd.not_found"));
            }
        });
        c("exec", (shell, parameter, doEcho) -> {
            exeCmd(parameter.getParameter(), shell);
        });
        c("exit", (shell, parameter, doEcho) -> {
            Main.lc_printer.println(R.s_strings.get("exit"));
            Main.exit(0);
        });
        c("ls", (shell, parameter, doEcho) -> {
            //TODO ls
        });
        c("pwd", (shell, parameter, doEcho) -> {
            File pf = new File(Main.posix.getcwd());
            shell.getPrinter().println(pf.getCanonicalPath());
        });
        //TODO run

        c("jsh-about", (shell, parameter, doEcho) -> {
            Main.lc_printer.println("JavaShell v1.0.1-Alpha\n" +
                    "这是一个想让Shell智能性和可扩展性增强的开源项目.\n" +
                    "详见GitHub: https://github.com/maxelblack/jsh");
        });
    }

    public void c(String name, ShellRunnable exec) {
        addCommand(new Command(name, exec));
    }

    //外部方法
    public static void exeCmd(String commandStr, JavaShell shell) throws IOException {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            Tools.throwexp(e);
        } finally {
            if (br != null)
            {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
