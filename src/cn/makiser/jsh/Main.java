package cn.makiser.jsh;

import cn.makiser.jsh.io.ConsolePrinter;
import cn.makiser.jsh.io.ConsoleScanner;
import cn.makiser.jsh.io.Printer;
import cn.makiser.jsh.io.Scanner;
import cn.makiser.jsh.plugin.Plugin;
import cn.makiser.jsh.plugin.PluginLoader;
import cn.makiser.jsh.plugin.SystemBasicPlugin;
import cn.makiser.jsh.shell.JavaShell;
import jnr.posix.POSIX;
import jnr.posix.POSIXFactory;
import jnr.posix.util.DefaultPOSIXHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final POSIX posix = POSIXFactory
            .getPOSIX(new DefaultPOSIXHandler(), true);

    public final static List<Plugin> plugins = new ArrayList<>();
    public static Printer lc_printer = new ConsolePrinter();
    public static Scanner lc_scanner = new ConsoleScanner();
    private static File workspace = null;

    public static void main(String[] args) {
        Main.lc_printer.println("JavaShell - Pd2's Shell");
        try {
            R.freshAll();
            Main.lc_printer.println(
                    "VERSION: " + R.s_strings.get("jsh-version") + "\n" +
                    "Copyright (c) 2020, Makiser_Tech.");
            workspace = new File(args[0]);
            if(!workspace.exists()) {
                workspace.mkdirs();
            }
            //加载插件
            File path = new File(workspace.getAbsolutePath() + "/plugins");
            if(!path.exists()) {
                path.mkdir();
            }
            File[] jars = path.listFiles();
            assert jars != null;
            if(jars.length != 0) {
                for (File jar : jars) {
                    PluginLoader loader = new PluginLoader(jar);
                    Plugin plugin = loader.load();
                    plugins.add(plugin);
                }
                String s = R.strings.get("shell.plugin_load")
                        .replaceAll("\\$num", String.valueOf(jars.length));
                Main.lc_printer.println(s);
            }
            //加载系统组件
            Plugin systemBasicPlugin = new SystemBasicPlugin();
            plugins.add(systemBasicPlugin);
            //启动Shell
            JavaShell shell = new JavaShell();
            shell.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println('\n');
        }
    }

    //get&set
    public static File getWorkspace() {
        return workspace;
    }

    public static void exit(int status) {
        System.exit(status);
    }
}
