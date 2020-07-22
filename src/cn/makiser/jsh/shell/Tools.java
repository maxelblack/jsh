package cn.makiser.jsh.shell;

import cn.makiser.jsh.Main;
import com.sun.istack.internal.NotNull;

public class Tools {
    public static int choice(@NotNull String choices) {
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
}
