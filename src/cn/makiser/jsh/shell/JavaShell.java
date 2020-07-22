package cn.makiser.jsh.shell;

import cn.makiser.jsh.Main;
import cn.makiser.jsh.R;
import cn.makiser.jsh.plugin.Plugin;
import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaShell {
    public String print_format = "$login@$host: $pwd$ ";

    private final Map<String, Command> commandMap = new HashMap<>();
    private String login, host;
    private File pwd;
    private ActivePrinter printer;

    public void run() throws Exception {
        loadCommands(Main.plugins);
        pwd = new File(Main.posix.getcwd());
        login = Main.posix.getlogin();
        host = Main.posix.gethostname();
        while(true) {
            Main.lc_printer.print(getPrint());
            String commandLine = Main.lc_scanner.getLine();
            exec(commandLine, true);
        }
    }

    public void exec(@NotNull String commandLine, boolean doEcho) {
        Command command = commandMap.get(commandLine.split(" ")[0]);
        if(command == null) {
            Main.lc_printer.println(R.strings.get("shell.cmd_not_found"));
        } else {
            printer = new ActivePrinter(Main.lc_printer, command);
            command.getExec().run(this, commandLine, doEcho);
        }
    }
    public static final int PWD_NOT_FOUND = 1;
    public int chdir(File pwd) {
        int rc = 0;
        if(pwd.exists()) {
            this.pwd = pwd;
            Main.posix.chdir(pwd.getPath());
        } else {
            rc = PWD_NOT_FOUND;
        }
        return rc;
    }
    public void loadCommands(List<Plugin> plugins) {
        for(Plugin plugin : plugins) {
            List<Command> commands = plugin.getCommands();
            for(Command c : commands) {
                String cl = c.getName();
                if(commandMap.containsKey(cl)) {
                    Main.lc_printer.print(R.s_strings.get("warn") +
                            R.s_strings.get("at") + " COMMAND '" + cl +"':\n"+
                            R.strings.get("shell.cmd_conflict1"));
                    int ch = Tools.choice("yn");
                    if(ch != 0) {
                        Main.lc_printer.println(R.s_strings.get("stop") + ".");
                        Main.exit(1);
                    }
                    Main.lc_printer.println(R.strings.get("shell.cmd_conflict2"));
                }
                commandMap.put(c.getName(), c);
            }
        }
    }

    //功能get
    public String getPrint() throws IOException {
        String s = print_format;
        s = s.replace("$folder", pwd.getName());
        s = s.replace("$pwd", pwd.getCanonicalPath());
        s = s.replace("$login", login);
        s = s.replace("$host", host);
        return s;
    }

    //get&set
    public ActivePrinter getPrinter() {
        return printer;
    }
}
