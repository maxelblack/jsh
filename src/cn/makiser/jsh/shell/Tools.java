package cn.makiser.jsh.shell;

import cn.makiser.jsh.Main;
import cn.makiser.jsh.R;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Tools {
    public static int choice(String choices) {
        String s = choices.toUpperCase();
        Main.lc_printer.print('(');
        for(int i = 0; i <= choices.length() - 2; i++) {
            Main.lc_printer.print(s.charAt(i) + "/");
        }
        Main.lc_printer.print(s.charAt(choices.length() - 1));
        Main.lc_printer.print("?)");
        String c = Main.lc_scanner.getLine().toUpperCase();
        return s.indexOf(c);
    }
    public static void throwexp(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        Main.lc_printer.print("\033[91m" +
                R.strings.get("shell.java_exception") + "\n");
        Main.lc_printer.print(sw.toString() + "\033[0m");
    }
}
