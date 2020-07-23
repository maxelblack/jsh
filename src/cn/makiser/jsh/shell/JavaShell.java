package cn.makiser.jsh.shell;

import cn.makiser.jsh.Main;
import cn.makiser.jsh.R;
import cn.makiser.jsh.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaShell {
    public String print_format = "$login@$host: $folder$_ ";

    private final Map<String, Command> commandMap = new HashMap<>();
    private String login, host;
    private File pwd;
    private ActivePrinter printer;

    public void run() throws Exception {
        loadCommands(Main.plugins);
        pwd = new File(Main.posix.getcwd());
        login = Main.posix.getlogin();
        if(login == null) {
            login = "null";
        }
        host = Main.posix.gethostname();
        while(true) {
            Main.lc_printer.print(getPrint());
            String commandLine = Main.lc_scanner.getLine();
            exec(commandLine, true);
        }
    }

    public void exec(String commandLine, boolean doEcho) throws Exception {
        String c = commandLine.split(" ")[0];
        Command command = commandMap.get(c);
        if(command == null) {
            if(doEcho && !c.isEmpty()) Main.lc_printer.println("shell: " +
                    R.strings.get("shell.cmd_not_found"));
        } else {
            Parameter parameter = new Parameter(commandLine);
            printer = new ActivePrinter(Main.lc_printer, command);
            command.getRunnable().run(this, parameter, doEcho);
        }
    }
    public static final int PWD_NOT_FOUND = 1;
    public int chdir(String pwd) throws IOException {
        int rc = 0;
        if(new File(pwd).exists()) {
            Main.posix.chdir(pwd);
            System.setProperty("user.dir", pwd);
            this.pwd = new File(Main.posix.getcwd());
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
        s = s.replace("$folder", new File(pwd.getCanonicalPath()).getName());
        s = s.replace("$pwd", pwd.getCanonicalPath());
        s = s.replace("$login", login);
        s = s.replace("$host", host);
        if(login.equals("root")) {
            s = s.replace("$_", "#");
        } else {
            s = s.replace("$_", "$");
        }
        return s;
    }

    //get&set
    public ActivePrinter getPrinter() {
        return printer;
    }
}
